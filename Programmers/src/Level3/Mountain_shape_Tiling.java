package Level3;

// DP 동적계획법

public class Mountain_shape_Tiling { // 산 모양 타일링
    int mod = 10007;

    public int solution(int n, int[] tops) {
        // dp[k][0] : 가장 오른쪽 아래 타일이 삼각형으로 끝나는 경우
        // dp[k][1] : 가장 오른쪽 아래 타일이 '\' 모양의 마름모로 끝나는 경우
        int[][] dp = new int[n + 1][2];

        dp[0][0] = 1;
        for(int i = 0; i < n; i++) {
            if(tops[i] == 0) dp[i + 1][0] = (dp[i][0] * 2 + dp[i][1]) % mod;
            else dp[i + 1][0] = (dp[i][0] * 3 + dp[i][1] * 2) % mod;

            dp[i + 1][1] = (dp[i][0] + dp[i][1]) % mod;
        }

        return (dp[n][0] + dp[n][1]) % mod;
    }
}
