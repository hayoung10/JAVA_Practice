package Level3;

import java.util.function.Function;

// DP 동적계획법
// 이진 탐색

public class Treasure_Hunt { // 보물 찾기
    public int solution(int[] depth, int money, Function<Integer, Integer> excavate) {
        int n = depth.length;
        int[][] dp = new int[n + 2][n + 2]; // dp[l][r]: l~r 구간에서 보물을 찾는 데 필요한 최소 최악 비용
        int[][] opt = new int[n + 2][n + 2]; // opt[l][r]: l~r 구간에서 가장 먼저 파야하는 최적 위치

        for(int len = 1; len <= n; len++) { // 구간 길이
            for(int l = 1; l + len - 1 <= n; l++) {
                int r = l + len - 1;
                dp[l][r] = Integer.MAX_VALUE;
                for(int i = l; i <= r; i++) { // i번 위치에서 팔 때
                    int cost = depth[i - 1] + Math.max(dp[l][i - 1], dp[i + 1][r]);
                    if(cost < dp[l][r]) {
                        dp[l][r] = cost;
                        opt[l][r] = i;
                    }
                }
            }
        }

        // 보물 탐색
        int l = 1;
        int r = n;
        while(true) {
            int i = opt[l][r];
            int ret = excavate.apply(i);

            if(ret == 0) return i;
            if(ret < 0) r = i - 1; // 보물이 왼쪽에 있음
            else l = i + 1; // 보물이 오른쪽에 있음
        }
    }
}