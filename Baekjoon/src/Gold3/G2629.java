package Gold3;

import java.io.*;
import java.util.*;

public class G2629 { // 양팔저울
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] weight = new int[N];
        int weightMax = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            weight[i] = Integer.parseInt(st.nextToken());
            weightMax += weight[i];
        }

        boolean[] dp = new boolean[40001]; // 구슬의 무게를 확인할 수 있는지 여부
        dp[0] = true;
        for(int i = 0; i < N; i++) {
            for(int j = weightMax; j >= 0; j--)
                if(dp[j]) dp[j + weight[i]] = true;
            for(int j = 0; j <= weightMax; j++)
                if(dp[j]) dp[Math.abs(j - weight[i])] = true;
        }

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++) {
            int marble = Integer.parseInt(st.nextToken());
            if(dp[marble]) System.out.print("Y ");
            else System.out.print("N ");
        }
    }
}