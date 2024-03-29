package Gold3;

import java.io.*;
import java.util.*;

public class G1580 { // 위치 바꾸기
    static int N, M;
    static String[] board = new String[20];
    static int ax, ay, bx, by; // A와 B의 시작지점
    static int[][][][] dp = new int[20][20][20][20]; // dp[ax][ay][bx][by] : A가 (ax, ay), B가 (bx, by)로 움직였을 때, 턴의 최솟값
    static boolean[][][][] visited = new boolean[20][20][20][20];
    static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1, 0};
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1, 0};

    private static void bfs() { // BFS 이용
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{ax, ay, bx, by});
        visited[ax][ay][bx][by] = true;

        while(!q.isEmpty()) {
            int[] cur = q.remove();
            for(int i = 0; i < 9; i++) {
                int nax = cur[0] + dx[i];
                int nay = cur[1] + dy[i];
                if(nax < 0 || nay < 0 || nax >= N || nay >= M) continue;

                for(int j = 0; j < 9; j++) {
                    int nbx = cur[2] + dx[j];
                    int nby = cur[3] + dy[j];
                    if(nbx < 0 || nby < 0 || nbx >= N || nby >= M) continue;

                    if(board[nax].charAt(nay) == 'X' || board[nbx].charAt(nby) == 'X') continue; // 해당 위치가 벽인 경우
                    if(nax == cur[2] && nay == cur[3] && nbx == cur[0] && nby == cur[1]) continue; // 서로 교차하는 경우
                    if(nax == nbx && nay == nby || visited[nax][nay][nbx][nby]) continue; // 턴의 마지막에 같은 곳에 있는 경우
                    visited[nax][nay][nbx][nby] = true;
                    q.add(new int[]{nax, nay, nbx, nby});
                    dp[nax][nay][nbx][nby] = dp[cur[0]][cur[1]][cur[2]][cur[3]] + 1;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());
        for(int i = 0; i < N; i++) {
            board[i] = br.readLine();

            for(int j = 0; j < M; j++) {
                if(board[i].charAt(j) == 'A') { ax = i; ay = j; }
                if(board[i].charAt(j) == 'B') { bx = i; by = j; }
            }
        }
        bfs();
        if(visited[bx][by][ax][ay]) System.out.println(dp[bx][by][ax][ay]);
        else System.out.println(-1); // 서로의 위치를 바꿀 수 없는 경우
    }
}
