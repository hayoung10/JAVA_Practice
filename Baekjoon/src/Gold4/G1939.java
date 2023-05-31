package Gold4;

import java.io.*;
import java.util.*;

public class G1939 { // 중량제한
    static ArrayList<Node>[] graph;
    static int N, start, end, ans;

    private static void binarySearch(int left, int right) {
        while(left <= right) {
            int mid = (left + right) / 2;

            if(bfs(mid)) {
                left = mid + 1; // 더 무거운 중량으로
                ans = mid;
            } else {
                right = mid - 1; // 더 가벼운 중량으로
            }
        }
    }

    private static boolean bfs(int weight) { // 해당 weight(중량)로 방문 가능한지 확인
        boolean[] visited = new boolean[N + 1];
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(start, 0));

        while(!q.isEmpty()) {
            Node cur = q.remove();

            if(cur.vertex == end) return true;

            for(Node next : graph[cur.vertex]) {
                if(next.weight >= weight && !visited[next.vertex]) {
                    visited[next.vertex] = true;
                    q.add(next);
                }
            }
        }

        return false;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 초기화
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++)
            graph[i] = new ArrayList<>();

        int left = Integer.MAX_VALUE; // 최소 중량값
        int right = 0; // 최대 중량값
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph[v1].add(new Node(v2, w));
            graph[v2].add(new Node(v1, w));
            left = Math.min(left, w);
            right = Math.max(right, w);
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken()); // 시작 섬
        end = Integer.parseInt(st.nextToken()); // 도착 섬

        binarySearch(left, right);
        System.out.println(ans);
    }

    public static class Node {
        int vertex, weight;

        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }
}
