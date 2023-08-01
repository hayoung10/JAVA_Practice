package Level3;

// DP 동적계획법

public class Optimal_Matrix_Multiplication { // 최적의 행렬 곱셈
    public int solution(int[][] matrix_sizes) {
        int len = matrix_sizes.length;
        int[][] dp = new int[len][len]; // dp[i][j] = i번째 행렬부터 j번째 형렬까지 최소 연산 횟수

        for(int n = 0; n < len; n++) {
            for(int i = 0; i < len - n; i++) {
                int j = i + n;

                if(i == j) continue; // dp[i][j] = 0

                dp[i][j] = Integer.MAX_VALUE;
                for(int k = i; k < j; k++)
                    dp[i][j] = Math.min(dp[i][j],
                            dp[i][k] + dp[k + 1][j] + matrix_sizes[i][0] * matrix_sizes[k][1] * matrix_sizes[j][1]);
            }
        }

        return dp[0][len - 1];
    }
}
