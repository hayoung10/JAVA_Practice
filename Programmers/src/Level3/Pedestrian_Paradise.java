package Level3;

// DP 동적계획법

public class Pedestrian_Paradise { // 보행자 천국
    int MOD = 20170805;

    public int solution(int m, int n, int[][] cityMap) {
        int answer = 0;

        int[][] right = new int[m + 1][n + 1]; // 왼쪽에서 오른쪽 방향으로 온 경우
        int[][] down = new int[m + 1][n + 1]; // 위쪽에서 아래쪽 방향으로 내려온 경우

        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {{
                if(i == 1 && j == 1) {
                    right[i][j] = 1;
                    down[i][j] = 1;
                } else if(cityMap[i - 1][j - 1] == 0) { // 자동차가 자유롭게 지나갈 수 있는 경우
                    right[i][j] = (right[i][j - 1] + down[i - 1][j]) % MOD;
                    down[i][j] = (right[i][j - 1] + down[i - 1][j]) % MOD;
                } else if(cityMap[i - 1][j - 1] == 1) { // 자동차 통행이 금지된 경우
                    right[i][j] = 0;
                    down[i][j] = 0;
                } else {
                    right[i][j] = right[i][j - 1];
                    down[i][j] = down[i - 1][j];
                }
            }}
        }

        return right[m][n]; // return right[m][n] or down[m][n]
    }
}
