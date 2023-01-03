package Level4;

// DP 동적계획법

public class Four_Arithmetic_Operations {
    private int[][][] dp = new int[201][201][2];

    public int solution(String arr[]) {
        int n = arr.length / 2 + 1; // 숫자의 개수

        // 초기화
        for(int i = 0; i < n + 1; i++) {
            for(int j = 0; j < n + 1; j++) {
                dp[i][j][0] = Integer.MIN_VALUE; // [i, j] 구간의 연산의 최댓값
                dp[i][j][1] = Integer.MAX_VALUE; // [i, j] 구간의 연산의 최솟값
            }
        }

        for(int step = 0; step < n; step++) { // step : i와 j의 간격
            for(int i = 0; i < n - step; i++) {
                int j = step + i;

                if(step == 0) {
                    dp[i][j][0] = Integer.parseInt(arr[i * 2]);
                    dp[i][j][1] = Integer.parseInt(arr[i * 2]);
                    continue;
                }

                for(int k = i; k < j; k++) {
                    if(arr[k * 2 + 1].equals("-")) {
                        dp[i][j][0] = Math.max(dp[i][k][0] - dp[k + 1][j][1], dp[i][j][0]); // 최댓값 - 최솟값
                        dp[i][j][1] = Math.min(dp[i][k][1] - dp[k + 1][j][0], dp[i][j][1]); // 최솟값 - 최대값
                    } else {
                        dp[i][j][0] = Math.max(dp[i][k][0] + dp[k + 1][j][0], dp[i][j][0]); // 최댓값 + 최댓값
                        dp[i][j][1] = Math.min(dp[i][k][1] + dp[k + 1][j][1], dp[i][j][1]); // 최솟값 + 최솟값
                    }
                }

            }
        }

        return dp[0][n - 1][0];
    }
}
