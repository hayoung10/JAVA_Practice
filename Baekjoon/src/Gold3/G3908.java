package Gold3;

import java.io.*;
import java.util.*;

public class G3908 { // 서로 다른 소수의 합
    static int[][] dp = new int[1121][15]; // dp[n][k] : n을 서로 다른 k개의 소수의 합으로 나타내는 방법의 수
    static List<Integer> prime = new ArrayList<>(); // 소수 리스트

    private static void eratosthenes() { // 에라토스테네스의 체 이용
        boolean flag = true;

        prime.add(2);
        for(int i = 3; i <= 1120; i++) {
            flag = true;
            for(int j = 2; j < i; j++) {
                if(i % j == 0) {
                    flag = false;
                    break;
                }
            }
            if(flag) prime.add(i);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        eratosthenes();
        dp[0][0] = 1;
        for(int i = 0; i < prime.size(); i++) {
            for(int j = 1120; j >= prime.get(i); j--)
                for(int k = 1; k <= 14; k++)
                    dp[j][k] += dp[j - prime.get(i)][k - 1];
        }

        StringTokenizer st;
        for(int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            System.out.println(dp[n][k]);
        }
    }
}