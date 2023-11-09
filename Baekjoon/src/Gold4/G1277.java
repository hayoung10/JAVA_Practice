package Gold4;

import java.io.*;
import java.util.*;

public class G1277 { // 발전소 설치
    static ArrayList<Pair>[] graph;

    private static double getDistance(int x1, int y1, int x2, int y2) { // 좌표 (x1, y1)의 발전소와 좌표 (x2, y2)의 발전소 간의 거리 구하기
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    private static int dijkstra(int N) { // Dijkstra 이용
        double[] minDist = new double[N + 1];
        Arrays.fill(minDist, Double.MAX_VALUE);

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(1, 0));
        while(!pq.isEmpty()) {
            Pair cur = pq.remove();
            if(minDist[cur.node] < cur.distance) continue;

            for(Pair next : graph[cur.node]) {
                if(minDist[next.node] > cur.distance + next.distance) {
                    minDist[next.node] = cur.distance + next.distance;
                    pq.add(new Pair(next.node, minDist[next.node]));
                }
            }
        }

        return (int) (minDist[N] * 1000);
    }

    public static void main(String[] args) throws IOException {
        // 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 발전소의 수
        int W = Integer.parseInt(st.nextToken()); // 전선의 수
        int[][] loc = new int[N + 1][2];
        graph = new ArrayList[N + 1];
        for(int i = 0; i <= N; i++) graph[i] = new ArrayList<>();
        double M = Double.parseDouble(br.readLine()); // 제한 길이
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            loc[i][0] = Integer.parseInt(st.nextToken());
            loc[i][1] = Integer.parseInt(st.nextToken());
            for(int j = 1; j < i ; j++) {
                double d = getDistance(loc[i][0], loc[i][1], loc[j][0], loc[j][1]);
                if(d <= M) {
                    graph[i].add(new Pair(j, d));
                    graph[j].add(new Pair(i, d));
                }
            }
        }
        for(int i = 0; i < W; i++) { // 이미 이어진 발전소들
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            graph[s].add(new Pair(e, 0));
            graph[e].add(new Pair(s, 0));
        }

        System.out.println(dijkstra(N));
    }

    static class Pair implements Comparable<Pair> {
        int node; // 해당 노드(발전소)와 연결된 노드(발전소)
        double distance;

        public Pair(int node, double distance) {
            this.node = node;
            this.distance = distance;
        }

        @Override
        public int compareTo(Pair o) {
            return Double.compare(this.distance, o.distance);
        }
    }
}