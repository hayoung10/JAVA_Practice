package Level3;

import java.util.*;

// Graph 그래프

public class Farthest_Node { // 가장 먼 노드
    public int solution(int n, int[][] edge) {
        int answer = 0, maxDepth = 0; // maxDepth : 가장 먼 깊이

        // 초기화
        boolean[] visited = new boolean[n + 1];
        boolean[][] graph = new boolean[n + 1][n + 1];
        int[] depth = new int[n + 1];

        Arrays.sort(edge, (o1, o2) -> {
            return o1[0] == o2[0] ? Integer.compare(o1[1], o2[1]) : Integer.compare(o1[0], o2[0]);
                });

        for(int i = 0; i < edge.length; i++) {
            graph[edge[i][0]][edge[i][1]] = true;
            graph[edge[i][1]][edge[i][0]] = true;
        }

        // 가장 멀리 떨어진 노드까지의 깊이 구하기
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1); // start node : 1
        visited[1] = true;

        while(!queue.isEmpty()) {
            int cur = queue.remove();

            for(int i = 2; i <= n; i++) {
                if(graph[cur][i] && !visited[i]) { // graph[cur][i] == true, visited == false
                    queue.add(i);
                    visited[i] = true;
                    depth[i] = depth[cur] + 1;

                    maxDepth = Math.max(depth[i], maxDepth);
                }
            }
        }

        for(int i = 1; i <= n; i++)
            if(depth[i] == maxDepth) // 가장 멀리 떨어진 노드 수 구하기
                answer++;

        return answer;
    }
}
