package Gold3;

import java.io.*;
import java.util.*;

public class G7579 { // 앱
    static int N, M, maxCost = 0;
    static int[] m, c;
    static int[][] dp; // dp[i][j] : A_1에서 A_i까지 앱에서 j의 비용으로 확보할 수 있는 최대의 바이트 수

    private static void input() throws IOException { // 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        m = new int[N + 1]; // 사용 중인 메모리의 바이트 수
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) m[i] = Integer.parseInt(st.nextToken());

        c = new int[N + 1]; // 앱을 비활성화 했을 경우의 비용
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            c[i] = Integer.parseInt(st.nextToken());
            maxCost += c[i]; // 최대 비용 업데이트
        }
        dp = new int[N + 1][maxCost + 1];
    }

    private static int knapsack() { // 배낭 문제 이용
        int cost; // 비용
        for(int i = 1; i <= N; i++) {
            for(cost = 0; cost <= maxCost; cost++) {
                if(cost >= c[i])
                    dp[i][cost] = Math.max(dp[i][cost], dp[i - 1][cost - c[i]] + m[i]);
                dp[i][cost] = Math.max(dp[i][cost], dp[i - 1][cost]);
            }
        }

        // 최소 비용 구하기
        for(cost = 0; cost <= maxCost; cost++)
            if(dp[N][cost] >= M) break; // 필요한 메모리 : M 바이트
        return cost;
    }

    public static void main(String[] args) throws IOException {
        input();
        System.out.println(knapsack()); // dp의 배낭문제 이용
    }
}