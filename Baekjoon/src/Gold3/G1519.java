package Gold3;

import java.io.*;
import java.util.*;

public class G1519 { // 부분 문자열 뽑기 게임
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        if(N < 10) { // 이길 수 없는 경우
            System.out.println(-1); return;
        }

        int[] dp = new int[N + 1];
        for(int num = 10; num <= N; num++) {
            Set<String> set = new HashSet<>(); // num으로 만들 수 있는 진 부분 문자열들
            String str = String.valueOf(num);

            for(int i = 0; i < str.length(); i++) {
                if(str.charAt(i) == '0') continue;

                String tmp = "";
                for(int j = i; j < str.length(); j++) {
                    tmp += str.charAt(j);
                    if(!tmp.equals(str)) set.add(tmp);
                }
            }

            int minValue = Integer.MAX_VALUE;
            for(String subStr : set) {
                int n = Integer.parseInt(subStr);
                if(dp[num - n] == 0) minValue = Math.min(minValue, n); // 이길 수 있는 경우
            }

            if(minValue != Integer.MAX_VALUE) dp[num] = minValue;
        }

        if(dp[N] == 0) dp[N] = -1; // 이길 수 없는 경우
        System.out.println(dp[N]);
    }
}