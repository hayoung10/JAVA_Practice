package Level3;

import java.util.*;

// DFS 깊이 우선 탐색

public class Make_All_Zeros { // 모두 0으로 만들기
    long answer;
    long[] weight; // 가중치
    boolean[] visited;
    ArrayList<Integer>[] graph; // 각 정점에 연결된 edge들

    public long solution(int[] a, int[][] edges) {
        weight = new long[a.length];
        visited = new boolean[a.length];
        graph = new ArrayList[a.length];

        long sum = 0;
        for(int i = 0; i < a.length; i++) {
            sum += a[i];
            weight[i] = a[i];
            graph[i] = new ArrayList<>();
        }
        if(sum != 0) return -1; // 불가능

        for(int i = 0; i < edges.length; i++) {
            graph[edges[i][0]].add(edges[i][1]);
            graph[edges[i][1]].add(edges[i][0]);
        }
        dfs(0);


        return answer;
    }

    private long dfs(int v) {
        visited[v] = true;

        for(int i = 0; i < graph[v].size(); i++) {
            int next = graph[v].get(i);
            if(!visited[next])
                weight[v] += dfs(next);
        }
        answer += Math.abs(weight[v]);

        return weight[v];
    }
}
