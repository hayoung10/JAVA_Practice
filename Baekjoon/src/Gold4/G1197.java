package Gold4;

import java.io.*;
import java.util.*;

public class G1197 { // 최소 스패닝 트리
    static int V, E;
    static Edge[] edges;
    static int[] root;
    static ArrayList<Node>[] adjList; // 인접리스트

    private static int find_root(int a) {
        if(root[a] == a) return a;
        return root[a] = find_root(root[a]);
    }

    private static void union_root(int a, int b) {
        a = find_root(a);
        b = find_root(b);
        if(a != b) root[a] = b;
    }

    private static int kruskal() { // 크루스칼 알고리즘 이용
        int answer = 0;
        int cnt = 0;
        Arrays.sort(edges);
        for(Edge e : edges) {
            if(find_root(e.from) == find_root(e.to)) continue; // 사이클이 발생한 경우

            union_root(e.from, e.to); // root 관계 갱신
            answer += e.weight;
            if(cnt++ == V - 1) return answer; // 정점 개수 V에 대해 (V - 1)개의 간선을 찾은 경우 종료
        }
        return answer;
    }

    private static int prim() { // 프림 알고리즘 이용
        int answer = 0;
        int cnt = 0;
        boolean[] visited = new boolean[V + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0));
        while(!pq.isEmpty()) {
            Node cur = pq.remove();
            if(visited[cur.to]) continue; // 이미 방문한 노드인 경우

            visited[cur.to] = true;
            answer += cur.weight;
            if(cnt++ == V) return answer;
            for(Node node : adjList[cur.to]) // 목적지 노드와 연결된 노드들을 모두 큐에 삽입
                if(!visited[node.to]) pq.add(node);
        }
        return answer;
    }

    private static void solution1() throws IOException { // 크루스칼 알고리즘을 이용한 방법
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken()); E = Integer.parseInt(st.nextToken());
        edges = new Edge[E];
        root = new int[V + 1];
        for(int i = 0; i <= V; i++) root[i] = i;
        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            edges[i] = new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        System.out.println(kruskal());
    }

    private static void solution2() throws IOException { // 프림 알고리즘을 이용한 방법
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken()); E = Integer.parseInt(st.nextToken());
        adjList = new ArrayList[V + 1];
        for(int i = 0; i <= V; i++) adjList[i] = new ArrayList<>();
        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            adjList[from].add(new Node(to, weight));
            adjList[to].add(new Node(from, weight));
        }
        System.out.println(prim());
    }

    public static void main(String[] args) throws IOException {
//        solution1();
        solution2();
    }

    static class Edge implements Comparable<Edge> { // 간선
        int from, to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    static class Node implements Comparable<Node> { // 정점
        int to;
        int weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }
}