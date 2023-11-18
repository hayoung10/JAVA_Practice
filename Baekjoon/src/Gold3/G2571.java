package Gold3;

import java.io.*;
import java.util.*;

public class G2571 { // 색종이 - 3
    static int[][] board = new int[101][101];

    private static int prefixSum() { // 누적 합 이용
        int[][] dp = new int[101][101];
        for(int i = 1; i < 101; i++)
            for(int j = 1; j < 101; j++)
                dp[i][j] = board[i][j] + (dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1]);

        // 직사각형의 최대 넓이 구하기 (직사각형의 크기 : (i, j) ~ (m, n))
        int ret = 0;
        for(int i = 1; i < 101; i++) {
            for (int j = 1; j < 101; j++) {
                for (int m = i + 1; m < 101; m++) {
                    for (int n = j + 1; n < 101; n++) {
                        int area = dp[m][n] - (dp[m][j - 1] + dp[i - 1][n] - dp[i - 1][j - 1]);
                        if(area < 0) break;
                        ret = Math.max(ret, area);
                    }
                }
            }
        }
        return ret;
    }

    public static void main(String[] args) throws IOException {
        // 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cnt = Integer.parseInt(br.readLine()); // 색종이의 수
        for(int[] b : board) Arrays.fill(b, -10001);
        StringTokenizer st;
        for(int i = 0; i < cnt; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            for(int x = r; x < r + 10; x++)
                for(int y = c; y < c + 10; y++)
                    board[x][y] = 1;
        }

        System.out.println(prefixSum()); // 누적 합 이용
    }
}
