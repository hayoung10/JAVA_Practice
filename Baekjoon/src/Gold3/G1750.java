package Gold3;

import java.io.*;
import java.util.*;

public class G1750 { // 서로소의 개수
    final static int mod = 10000003;

    private static int gcd(int a, int b) { // 최대공약수 구하기
        if(a % b == 0) return b;
        return gcd(b, a % b);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] dp = new int[101][100001]; // dp[i][j] : 수열에서 i번째 까지에서 최대공약수가 j일 때의 개수
        int[] arr = new int[N];
        for(int i = 0; i < N; i++) arr[i] = Integer.parseInt(br.readLine());
        Arrays.sort(arr);

        for(int i = 0; i < N; i++) {
            dp[i][arr[i]] = 1;
            if(i == 0) continue;

            for(int j = 1; j <= arr[i]; j++) {
                dp[i][j] = (dp[i][j] + dp[i - 1][j]) % mod;
                dp[i][gcd(arr[i], j)] = (dp[i][gcd(arr[i], j)] + dp[i - 1][j]) % mod;
            }
        }
        System.out.println(dp[N - 1][1]);
    }
}