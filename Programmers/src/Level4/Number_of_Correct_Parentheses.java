package Level4;

public class Number_of_Correct_Parentheses {
    // (1) DP 동적계획법
    public int solution(int n) {
        int dp[] = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for(int i = 2; i <= n; i++) {
            for(int j = 1; j <= i; j++) {
                dp[i] += dp[i - j] * dp[j - 1];
            }
        }

        return dp[n];
    }

    // (2) DFS 깊이 우선 탐색
    int cnt = 0;

    public int solution2(int n) {
        dfs(n * 2, 1);

        return cnt;
    }

    public void dfs(int node, int sum) {
        if (sum < 0) return;

        if (node == 1) {
            if (sum == 0) {
                cnt++;
            }
            return;
        }

        dfs(node - 1, sum + 1); // '(' : 1
        dfs(node - 1, sum - 1); // ')' : -1
    }
}
