package Gold4;

import java.io.*;
import java.util.*;

public class G1922 { // 네트워크 연결
    static int N, M;
    static int[] parent;
    static PriorityQueue<Node> pq = new PriorityQueue<>();

    private static void unionParent(int v1, int v2) { // 연결
        int p1 = findParent(v1);
        int p2 = findParent(v2);

        if(p1 != p2)
            parent[p1] = p2;
    }

    private static int findParent(int v) {
        if(parent[v] == v) return v;
        return parent[v] = findParent(parent[v]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        parent = new int[N + 1];
        for(int i = 1; i <= N; i++) parent[i] = i;

        StringTokenizer st;
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            pq.add(new Node(a, b, c));
        }

        int answer = 0;
        while(!pq.isEmpty()) {
            Node node = pq.remove();

            if(findParent(node.v1) != findParent(node.v2)) { // 연결되지 않은 경우 연결
                unionParent(node.v1, node.v2);
                answer += node.w;
            }
        }

        System.out.println(answer);
    }

    static class Node implements Comparable<Node> {
        int v1, v2;
        int w; // weight 비용

        public Node(int v1, int v2, int w) {
            this.v1 = v1;
            this.v2 = v2;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return this.w - o.w;
        }
    }
}