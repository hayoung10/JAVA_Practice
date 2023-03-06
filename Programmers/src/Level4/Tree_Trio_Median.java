package Level4;

import java.util.*;

// BFS 너비 우선 탐색

public class Tree_Trio_Median { // 트리 트리오 중간값
    ArrayList<Integer>[] tree;

    public int solution(int n, int[][] edges) {
        int answer = 0;

        tree = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++)
            tree[i] = new ArrayList<>();

        for(int i = 0; i < edges.length; i++) {
            tree[edges[i][0]].add(edges[i][1]);
            tree[edges[i][1]].add(edges[i][0]);
        }

        // 1에서 가장 먼 노드 찾기
        int[] dist = bfs(1, n);
        int node = 0, maxDepth = 0, cnt = 0;
        for(int i = 2; i <= n; i++) {
            if(maxDepth <= dist[i]) {
                node = i;
                maxDepth = dist[i];
            }
        }

        // 중간값 찾기
        for(int i = 0; i < 2; i++) {
            dist = bfs(node, n);
            node = 0; maxDepth = 0;
            for(int j = 0; j <= n; j++) {
                if(maxDepth <= dist[j]) {
                    node = j;
                    maxDepth = dist[j];
                }
            }
            cnt = 0;
            for(int d : dist)
                if(maxDepth == d) cnt++;
            if(cnt > 1) return maxDepth; // 가장 먼 노드가 2개 이상인 경우
        }

        return maxDepth - 1; // 가장 먼 노드가 1개 이하인 경우
    }

    private int[] bfs(int start, int n) {
        boolean[] visited = new boolean[n + 1];
        int[] dist = new int[n + 1]; // distance
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;

        while(!q.isEmpty()) {
            int num = q.remove();

            for(int i : tree[num]) {
                if(i == num || visited[i]) continue;
                visited[i] = true;
                q.add(i);
                dist[i] = dist[num] + 1;
            }
        }

        return dist;
    }
}
