package Gold2;

import java.io.*;
import java.util.*;

public class G1103 { // 게임
    static int N, M, maxMove;
    static char[][] map;
    static boolean[][] visited;
    static int[][] dp;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static boolean isCycle = false;

    private static void dfs(int x, int y, int move) {
        maxMove = Math.max(maxMove, move);
        dp[x][y] = move;

        for(int i = 0; i < 4; i++) {
            int nx = x + (map[x][y] - '0') * dx[i];
            int ny = y + (map[x][y] - '0') * dy[i];

            if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue; // 보드의 바깥으로 나간 경우
            if(map[nx][ny] == 'H') continue; // 구멍에 빠진 경우

            if(visited[nx][ny]) { // 이미 방문한 경우
                isCycle = true;
                return;
            }
            if(dp[nx][ny] > move) continue;

            visited[x][y] = true;
            dfs(nx, ny, move + 1);
            visited[x][y] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 초기화
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new boolean[N][M];
        dp = new int[N][M];

        for(int i = 0; i < N; i++)
            map[i] = br.readLine().toCharArray();

        visited[0][0] = true;
        dfs(0, 0, 1);

        if(isCycle) System.out.println(-1);
        else System.out.println(maxMove);
    }
}
