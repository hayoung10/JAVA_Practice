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

    // (2) Prim Algorithm
    private static boolean[] visited = new boolean[101];
    private Vector<Vector<Pair>> edges = new Vector<>();
    private PriorityQueue<Pair> pq = new PriorityQueue<>();
    private int total = 0;

    public int solution2(int n, int[][] costs) {
        for(int i = 0; i < n; i++)
            edges.add(new Vector<>()); //

        for(int i = 0; i < costs.length; i++) {
            edges.get(costs[i][0]).add(new Pair(costs[i][1], costs[i][2]));
            edges.get(costs[i][1]).add(new Pair(costs[i][0], costs[i][2]));
        }
        Prim(0);

        return total;
    }

    private void Prim(Integer start) {
        visited[start] = true;
        for(int i = 0; i < edges.get(start).size(); i++) {
            if(!visited[edges.get(start).get(i).node])
                pq.add(edges.get(start).get(i));
        }

        while(!pq.isEmpty()) {
            Pair check = pq.peek();
            pq.remove();
            if(!visited[check.node]) {
                total += check.cost;
                Prim(check.node);
                return;
            }
        }
    }

    class Pair implements Comparable<Pair> {
        int node, cost;

        public Pair(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compareTo(Pair p) {
            return Integer.compare(this.cost, p.cost);
        }
    }
}