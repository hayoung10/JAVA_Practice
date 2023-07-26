package Level3;

// Dijkstra 다익스트라

import java.util.*;

public class Return_to_Force { // 부대복귀
    private List<List<Integer>> graph;
    private int[] dp;

    private void dijkstra(int destination) { // 다익스트라 이용
        Queue<Integer> q = new LinkedList<>();
        dp[destination] = 0; // 도착지가 가장 최솟값 0
        q.add(destination);

        while(!q.isEmpty()) {
            int cur = q.remove();

            for(int i = 0; i < graph.get(cur).size(); i++) {
                int next = graph.get(cur).get(i);
                if(dp[next] != -1) continue;

                dp[next] = dp[cur] + 1;
                q.add(next);
            }
        }
    }

    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        // 초기화
        graph = new ArrayList<>();
        for(int i = 0; i <= n; i++) graph.add(new ArrayList());

        for(int i = 0; i < roads.length; i++) {
            graph.get(roads[i][0]).add(roads[i][1]);
            graph.get(roads[i][1]).add(roads[i][0]);
        }
        dp = new int[n + 1];
        Arrays.fill(dp, -1);

        dijkstra(destination);
        int[] answer = new int[sources.length];
        for(int i = 0; i < sources.length; i++) answer[i] = dp[sources[i]];

        return answer;
    }
}