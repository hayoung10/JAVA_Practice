package Gold4;

import java.io.*;
import java.util.*;

public class G1261 { // 알고스팟
    static int N, M;
    static int[][] maze;
    static boolean[][] visited;
    
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    private static int bfs(int x, int y) {
        PriorityQueue<Node> pq = new PriorityQueue<>(); // 부순 벽의 개수가 최소가 되도록 오름차순으로 정렬
        pq.add(new Node(x, y, 0));
        visited[x][y] = true;
        
        while(!pq.isEmpty()) {
            Node node = pq.remove();
            
            if(node.x == N - 1 && node.y == M - 1)
                return node.wallCnt;
            
            for(int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];
                
                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue; // 미로의 밖으로 이동한 경우
                if(visited[nx][ny]) continue;

                if(maze[nx][ny] == 0)
                    pq.add(new Node(nx, ny, node.wallCnt));
                else
                    pq.add(new Node(nx, ny, node.wallCnt + 1));
                visited[nx][ny] = true;
            }
        }

        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 초기화
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        maze = new int[N][M];
        visited = new boolean[N][M];

        for(int i = 0; i < N; i++)
            maze[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();

        System.out.println(bfs(0, 0));
    }

    static class Node implements Comparable<Node> {
        int x, y;
        int wallCnt; // 부순 벽의 개수

        public Node(int x, int y, int wallCnt) {
            this.x = x;
            this.y = y;
            this.wallCnt = wallCnt;
        }

        @Override
        public int compareTo(Node o) {
            return this.wallCnt - o.wallCnt;
        }
    }
}
