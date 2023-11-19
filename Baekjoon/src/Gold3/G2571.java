package Gold3;

import java.io.*;
import java.util.*;

public class G2571 { // 색종이 - 3
    static int[][] board = new int[101][101];

    private static int prefixSum() { // 누적 합 이용
        // 높이 누적 합 구하기
        for(int i = 1; i < 100; i++)
            for(int j = 1; j < 101; j++)
                if(board[i][j] != 0 && board[i + 1][j] != 0)
                    board[i + 1][j] = board[i][j] + 1;

        // 직사각형의 최대 넓이 구하기
        int ret = 0;
        for(int i = 1; i < 101; i++) {
            for (int j = 1; j < 101; j++) {
                int h = 100;

                for(int k = j; k < 101; k++) {
                    h = Math.min(h, board[i][k]);
                    if(h == 0) break;
                    ret = Math.max(ret, h * (k - j + 1));
                }
            }
        }
        return ret;
    }

    public static void main(String[] args) throws IOException {
        // 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cnt = Integer.parseInt(br.readLine()); // 색종이의 수
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
