package Level3;

import java.util.*;

// DP 동적계획법, BFS 너비 우선 탐색

public class RaceTrack_Construction { // 경주로 건설
    public int solution(int[][] board) {
        int answer = Integer.MAX_VALUE;

        int[] xDir = {-1, 0, 0, 1};
        int[] yDir = {0, -1, 1, 0};
        int N = board.length;

        int[][][] dp = new int[N][N][4]; // cost dp (4 : 방향)
        for(int i = 0; i < N; i++) // 초기화
            for(int j = 0; j < N; j++)
                for(int k = 0; k < 4; k++)
                    dp[i][j][k] = Integer.MAX_VALUE;

        dp[0][0][0] = dp[0][0][1] = dp[0][0][2] = dp[0][0][3] = 0;

        Queue<Pos> q = new LinkedList<>();
        q.add(new Pos(0, 0, 0));

        while(!q.isEmpty()) {
            Pos point = q.remove();

            for(int i = 0; i < 4; i++) {
                int nx = point.x + xDir[i]; // next x coordinate
                int ny = point.y + yDir[i]; // next y coordinate

                if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if(board[nx][ny] == 1) continue;

                // next cost
                int nCost = dp[point.x][point.y][point.dir];
                if((point.x == 0 && point.y == 0) || i == point.dir) // 같은 방향 (직선도로)
                    nCost += 100;
                else // 다른 방향 (직선도로 + 코너)
                    nCost += 100 + 500;

                if(dp[nx][ny][i] >= nCost) {
                    dp[nx][ny][i] = nCost;
                    q.add(new Pos(nx, ny, i));

                    if(nx == N - 1 && ny == N - 1)
                        answer = Math.min(answer, nCost);
                }
            }
        }

        return answer;
    }

    private class Pos { // Position 위치
        int x, y, dir; // dir = direction

        public Pos(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
}
