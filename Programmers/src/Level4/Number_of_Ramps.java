package Level4;

// DP 동적계획법

public class Number_of_Ramps { // 경사로의 개수
    int mod = 1000000007;
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    int n, m;

    public int solution(int[][] grid, int[] d, int k) {
        n = grid.length;
        m = grid[0].length;

        long[][][] dp = new long[d.length + 1][n * m][n * m]; // dp[a][b][c] : b에서 시작해 경사로 d[a]를 통과하여 c에 도작하는 경우의 수
        for(int i = 0; i < n * m; i++) dp[0][i][i] = 1; // 자기 자신으로 가는 경로 1

        for(int dl = 0; dl < d.length; dl++) {
            for(int cur = 0; cur < n * m; cur++) {
                int x = cur / m;
                int y = cur % m;

                for(int dir = 0; dir < 4; dir++) {
                    int nx = x + dx[dir];
                    int ny = y + dy[dir];
                    if(nx < 0 || ny < 0 || nx >= n || ny >= m || grid[nx][ny] - grid[x][y] != d[dl]) continue;

                    for(int s = 0; s < n * m; s++) {
                        dp[dl + 1][s][nx * m + ny] += dp[dl][s][cur];
                        dp[dl + 1][s][nx * m + ny] %= mod;
                    }
                }
            }
        }

        long[][] M = dp[d.length];
        long[][] P = matPow(M, k); // M^k

        // 경로의 수
        long answer = 0;
        for(int i = 0; i < n * m; i++) {
            for(int j = 0; j < n * m; j++) {
                answer += P[i][j];
                answer %= mod;
            }
        }

        return (int) answer;
    }

    private long[][] matPow(long[][] mat, int k) { // 행렬 거듭제곱 (분할정복)
        if(k == 1) return mat;

        long[][] half = matPow(mat, k / 2);
        long[][] res = matMul(half, half); // 제곱

        if(k % 2 == 1) res = matMul(res, mat); // 홀수면 한 번 더 곱하기

        return res;
    }

    private long[][] matMul(long[][] A, long[][] B) { // 행렬곱
        long[][] C = new long[n * m][n * m];

        for(int i = 0; i < n * m; i++) {
            for(int j = 0; j < n * m; j++) {
                for(int l = 0; l < n * m; l++) {
                    C[i][j] += (A[i][l] * B[l][j]);
                    C[i][j] %= mod;
                }
            }
        }
        return C;
    }
}