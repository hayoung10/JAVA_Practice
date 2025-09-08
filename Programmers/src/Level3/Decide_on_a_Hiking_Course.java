package Level3;

import java.util.*;

// Dijkstra 다익스트라

public class Decide_on_a_Hiking_Course { // 등산코스 정하기
    List<Node>[] graph;
    boolean[] isGate, isSummit;

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        // 초기화
        graph = new ArrayList[n + 1];
        for(int i = 0; i < n + 1; i++)
            graph[i] = new ArrayList<>();

        isGate = new boolean[n + 1]; // 출입구 표시
        isSummit = new boolean[n + 1]; // 산봉우리 표시
        for(int g : gates) isGate[g] = true;
        for(int s : summits) isSummit[s] = true;

        // 그래프 만들기
        makeGraph(paths);

        // 다익스트라 실행
        return dijkstra(n);
    }

    private int[] dijkstra(int n) {
        final int INF = Integer.MAX_VALUE;
        int[] intensity = new int[n + 1];
        Arrays.fill(intensity, INF);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        for(int i = 1; i <= n; i++) {
            if(isGate[i]) {
                pq.add(new Node(i, 0));
                intensity[i] = 0;
            }
        }

        while(!pq.isEmpty()) {
            Node cur = pq.poll();

            if(cur.w > intensity[cur.e]) continue;
            if(isSummit[cur.e]) continue; // 산봉우리 도착

            for(Node next : graph[cur.e]) {
                int nextIntensity = Math.max(cur.w, next.w);
                if(intensity[next.e] > nextIntensity) {
                    intensity[next.e] = nextIntensity;
                    pq.add(new Node(next.e, nextIntensity));
                }
            }
        }

        // 정답 구하기
        int retSummit = -1; // 산봉우리의 번호
        int retIntensity = INF; // intensity의 최소값
        for(int i = 1; i <= n; i++) {
            if(!isSummit[i]) continue;
            if(intensity[i] < retIntensity) {
                retSummit = i;
                retIntensity = intensity[i];
            }
        }

        return new int[] { retSummit, retIntensity };
    }

    private void makeGraph(int[][] paths) { // 그래프 만들기
        for(int i = 0; i < paths.length; i++) {
            int s = paths[i][0];
            int e = paths[i][1];
            int w = paths[i][2];

            if(isGate[s] || isSummit[e]) {
                graph[s].add(new Node(e, w)); // s -> e
            } else if(isGate[e] || isSummit[s]) {
                graph[e].add(new Node(s, w)); // e -> s
            } else { // 일반 등산로로 양방향
                graph[s].add(new Node(e, w));
                graph[e].add(new Node(s, w));
            }
        }
    }

    private class Node implements Comparable<Node> {
        int e, w;

        Node(int e, int w) {
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            if(this.w != o.w) return this.w - o.w;
            return this.e - o.e;
        }
    }
}