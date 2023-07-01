package Gold5;

import java.io.*;
import java.util.*;

public class G2294 { // 동전 2
    public static void main(String[] args) throws IOException {
        // 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] coin = new int[n];
        for(int i = 0; i < n; i++) coin[i] = Integer.parseInt(br.readLine());

        int[] dp = new int[k + 1]; // dp[k] : k원을 만들 수 있는 최소의 경우의 수
        for(int i = 1; i <= k; i++) dp[i] = 100001;

        for(int i = 0; i < n; i++)
            for (int j = coin[i]; j <= k; j++)
                dp[j] = Math.min(dp[j], dp[j - coin[i]] + 1);

        if(dp[k] >= 100001) System.out.println(-1);
        else System.out.println(dp[k]);
    }
}