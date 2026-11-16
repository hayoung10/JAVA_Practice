package Level3;

import java.util.*;

// DFS 깊이 우선 탐색

public class Odd_Even_Tree { // 홀짝 트리
    Set<Integer> visited = new HashSet<>();
    Map<Integer, List<Integer>> graph;

    int forward = 0, reverse = 0; // 홀짝/역홀짝 노드 수

    public int[] solution(int[] nodes, int[][] edges) {
        int[] answer = new int[]{0, 0};

        graph = new HashMap<>();
        for(int node : nodes) {
            graph.put(node, new ArrayList<>());
        }
        for(int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        for(int node : nodes) {
            if(visited.contains(node)) continue;

            forward = 0;
            reverse = 0;

            dfs(node);

            if(forward == 1) answer[1]++; // 홀짝노드가 1개이면 역홀짝트리 가능
            if(reverse == 1) answer[0]++; // 역홀짝 노드가 1개이면 홀짝트리 가능
        }

        return answer;
    }

    private void dfs(int cur) {
        if(visited.contains(cur)) return;
        visited.add(cur);

        // 루트 노드가 아닌 노드의 홀짝/역홀짝 확인
        if(cur % 2 == (graph.get(cur).size() - 1) % 2) forward++;
        else reverse++;

        for(int next : graph.get(cur)) {
            dfs(next);
        }
    }
}
