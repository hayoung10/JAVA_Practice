package Gold4;

import java.io.*;
import java.util.*;

public class G1229 { // 육각수
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 초기화
        int[] dp = new int[N + 1]; // dp[N] : 합이 N이 되는 육각수 개수의 최솟값
        for(int i = 0; i <= 5; i++) {
            dp[i] = i; dp[i + 6] = i + 1;
        }
        dp[12] = 2;

        if(N < 13) {
            System.out.println(dp[N]); return;
        }

        // 육각수 만들기
        ArrayList<Integer> hexagon_List = new ArrayList<>(); // 육각수(hn) 리스트
        int n = 1, hn = 0;
        while(hn <= N) {
            hn = n * (2 * n - 1);
            hexagon_List.add(hn);
            n++;
        }

        // 육각수 개수의 최솟값 구하기
        for(int i = 13; i <= N; i++) {
            int minCnt = Integer.MAX_VALUE;
            for(int h : hexagon_List) {
                if(h > i) break;
                minCnt = Math.min(minCnt, dp[i - h]);
            }
            dp[i] = minCnt + 1;
        }
        System.out.println(dp[N]);
    }
}