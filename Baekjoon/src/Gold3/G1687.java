package Gold3;

import java.io.*;
import java.util.*;

public class G1687 { // 행렬 찾기
    public static void main(String[] args) throws IOException {
        // 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] matrixSum = new int[N + 1][M + 1];
        for(int i = 1; i <= N; i++) {
            String s = br.readLine();
            for(int j = 1; j <= M; j++)
                if(s.charAt(j - 1) == '0') matrixSum[i][j] = matrixSum[i - 1][j] + 1;
        }

        int answer = 0;
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= M; j++) {
                if(matrixSum[i][j] == 0) continue;

                int minValue = Integer.MAX_VALUE;
                for(int k = j; k > 0; k--) {
                    minValue = Math.min(minValue, matrixSum[i][k]);
                    answer = Math.max(answer, (j - k + 1) * minValue);
                }
            }
        }
        System.out.println(answer);
    }
}