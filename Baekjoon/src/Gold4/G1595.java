package Gold4;

import java.io.*;
import java.util.*;

public class G1595 { // 북쪽나라의 도로
    static List<Node>[] roads = new ArrayList[10001];
    static boolean[] visited = new boolean[10001];
    static long maxDist = 0;

    private static void dfs(int loc, long dist) {
        visited[loc] = true;
        for(Node next : roads[loc]) {
            if(!visited[next.to]) dfs(next.to, dist + next.dist);
            if(visited[next.to]) maxDist = Math.max(maxDist, dist);
        }
        visited[loc] = false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for(int i = 0; i < 10001; i++) roads[i] = new ArrayList<>();
        try {
            String input;
            while(!(input = br.readLine()).isEmpty()) {
                st = new StringTokenizer(input);
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                roads[a].add(new Node(b, c));
                roads[b].add(new Node(a, c));
            }
        } catch (Exception e) { }

        for(int i = 0; i < 10001; i++) dfs(i, 0);
        System.out.println(maxDist);
    }

    static class Node {
        int to, dist;

        public Node(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }
    }
}