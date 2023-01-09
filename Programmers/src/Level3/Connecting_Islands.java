package Level3;

import java.util.*;

// Greedy 탐욕법

public class Connecting_Islands { // 섬 연결하기
    // (1) Kruskal Algorithm
    private int[] parent;

    public int solution1(int n, int[][] costs) {
        int answer = 0;

        Arrays.sort(costs, (int[] a, int[] b) -> a[2] - b[2]); // 비용을 기준으로 오름차순 정렬

        // 초기화
        parent = new int[n];
        for(int i = 0; i < n; i++)
            parent[i] = i;

        for(int[] nodes : costs) {
            int fromParent = findParent(nodes[0]);
            int toParent = findParent(nodes[1]);

            if(fromParent == toParent) // 같은 그래프인 경우 선택 X
                continue;

            answer += nodes[2]; // 비용 추가
            parent[toParent] = fromParent; // 간선 연결 -> 같은 그래프
        }

        return answer;
    }

    private int findParent(int node) { // 부모 노드 탐색
        if(parent[node] == node)
            return node;
        return parent[node] = findParent(parent[node]);
    }
}