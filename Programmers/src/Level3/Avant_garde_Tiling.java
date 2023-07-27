package Level3;

// DP 동적계획법

public class Avant_garde_Tiling { // 아방가르드 타일링
    public int solution(int n) {
        long[] dp = new long[100002]; // dp[n] : n이 주어졌을 때, 가능한 경우의 수
        long[] dp2 = new long[100002]; // Σ dp[n - 4 - 3k]
        dp[0] = 1; dp[1] = 1; dp[2] = 3; dp[3] = 10; dp[4] = 23; dp[5] = 62; dp[6] = 170;
        dp2[0] = 1; dp2[1] = 1; dp2[2] = 3; dp2[3] = 11; dp2[4] = 24; dp2[5] = 65; dp2[6] = 181;

        int mod = 1000000007;

        for(int i = 7; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] * 2 + dp[i - 3] * 5;
            dp[i] += dp2[i - 4] * 2 + dp2[i - 5] * 2 + dp2[i - 6] * 4;
            dp[i] %= mod;

            dp2[i] = dp2[i - 3] + dp[i];
            dp2[i] %= mod;
        }

        return (int) dp[n];
    }
}
