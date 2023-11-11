package Gold3;

import java.io.*;
import java.util.*;

public class G1460 { // 진욱이의 농장
    static int N, M;
    static int[][] map;

    private static int prefixSum(int seed1, int seed2) { // 누적 합 이용
        int[][] dp = new int[N][N];

        for(int i = 0; i < N; i++)
            for(int j = 0; j < N; j++)
                if(map[i][j] == seed1 || map[i][j] == seed2)
                    dp[i][j] = 1;

        int ret = 0;
        for(int i = 1; i < N; i++) {
            for(int j = 1; j < N; j++) {
                if(dp[i][j] == 0) continue; // 0번 과일은 가져갈 수 없음

                dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
                ret = Math.max(ret, dp[i][j]);
            }
        }

        return ret;
    }

    public static void main(String[] args) throws IOException {
        // 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        int[] input;
        for(int cnt = 0; cnt < M; cnt++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for(int i = input[0]; i < input[0] + input[2]; i++)
                for(int j = input[1]; j < input[1] + input[2]; j++)
                    map[i][j] = input[3];
        }

        // 가져갈 수 있는 가장 넓은 농장의 넓이 구하기
        int answer = 0;
        for(int i = 1; i <= 7; i++)
            for(int j = i + 1; j <= 7; j++)
                answer = Math.max(answer, prefixSum(i, j));
        System.out.println(answer * answer);
    }
}