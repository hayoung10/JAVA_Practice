package Gold4;

import java.io.*;
import java.util.*;

public class G1595 { // 북쪽나라의 도로
    static List<Node>[] roads = new ArrayList[10001];
    static boolean[] visited = new boolean[10001];
    static int city;
    static long maxDist = 0;

    private static void dfs(int loc, long dist) {
        visited[loc] = true;
        for(Node next : roads[loc]) {
            if(visited[next.to]) continue;

            long nextDist = dist + next.dist;
            if(maxDist < nextDist) {
                maxDist = nextDist;
                city = next.to;
            }
            dfs(next.to, nextDist);
        }
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

        dfs(1, 0);

        Arrays.fill(visited, false);
        maxDist = 0;
        dfs(city, 0);
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