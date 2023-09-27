package Gold3;

import java.io.*;
import java.util.*;

public class G1520 { // 내리막 길
    static int M, N;
    static int[][] map, dp;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    private static int dfs(int x, int y) {
        if(x == M - 1 && y == N - 1) return 1; // 목표 지점에 도달한 경우

        if(dp[x][y] != -1) return dp[x][y]; // 방문한 경우

        int ret = 0;
        for(int i = 0; i < 4; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;

            // 범위에서 벗어났거나 내리막길이 아닌 경우
            if(nx < 0 || ny < 0 || nx >= M || ny >= N || map[nx][ny] >= map[x][y]) continue;

            ret += dfs(nx, ny);
        }
        return dp[x][y] = ret;
    }

    public static void main(String[] args) throws IOException {
        // 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken()); N = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        dp = new int[M][N];
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }

        System.out.println(dfs(0, 0));
    }
}