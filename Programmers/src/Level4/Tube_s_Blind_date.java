package Level4;

// DP 동적계획법

public class Tube_s_Blind_date { // 튜브의 소개팅
    public int[] solution(int m, int n, int s, int[][] time_map) {
        int moveDistance = 0;
        int sumOfTalkTime = 0;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        long[][][] DP = new long[51][51][2510];

        // 초기화
        for(int x = 0; x < 51; x++)
            for(int y = 0; y < 51; y++)
                for(int d = 0; d < 2510; d++)
                    DP[x][y][d] = Integer.MAX_VALUE;

        DP[0][0][0] = 0;
        for(int d = 0; d < 2500; d++) {
            for(int x = 0; x < m ; x++) {
                for(int y = 0; y < n; y++) {
                    if(time_map[x][y] == -1) continue;

                    for(int i = 0; i < 4; i++) {
                        int nx = x + dx[i];
                        int ny = y + dy[i];
                        if(nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
                        if(DP[x][y][d] + time_map[x][y] <= s)
                            DP[nx][ny][d + 1] = Math.min(DP[nx][ny][d + 1], DP[x][y][d] + time_map[x][y]);
                    }
                }
            }
        }

        for(int i = 0; i < 2500; i++) {
            if(DP[m - 1][n - 1][i] < Integer.MAX_VALUE) {
                moveDistance = i;
                sumOfTalkTime = (int)DP[m - 1][n - 1][i];
                break;
            }
        }

        return new int[]{moveDistance, sumOfTalkTime};
    }
}
