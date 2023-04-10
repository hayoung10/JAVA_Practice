package Level3;

// DP 동적계획법

public class Change { // 거스름돈
    public int solution(int n, int[] money) {
        int[] DP = new int[100001];
        DP[0] = 1; // 0을 표현한 경우의 수는 1가지

        for(int i = 0; i < money.length; i++)
            for(int j = money[i]; j <= n; j++)
                DP[j] += DP[j - money[i]];

        return DP[n] % 1000000007;
    }
}
