package Gold4;

import java.io.*;
import java.util.*;

public class G2239 { // 스도쿠
    static int[][] puzzle = new int[9][9];
    static boolean[][] row = new boolean[9][10]; // 행 중복 체크
    static boolean[][] col = new boolean[9][10]; // 열 중복 체크
    static boolean[][] square = new boolean[9][10]; // 3x3 사각형 중복 체크

    static boolean flag = false;

    private static void solution(int idx) { // BackTracking 이용
        if(idx >= 81) {
            flag = true;
            return;
        }

        int x = idx / 9;
        int y = idx % 9;
        int squareIdx = 3 * (x / 3) + (y / 3);
        if(puzzle[x][y] == 0) {
            for (int k = 1; k <= 9; k++) { // 사전식으로 앞서는 답을 구하기 위해 1부터 탐색
                if (row[x][k] || col[y][k] || square[squareIdx][k]) continue; // 중복 체크

                puzzle[x][y] = k;
                row[x][k] = col[y][k] = square[squareIdx][k] = true;

                solution(idx + 1);
                if(flag) return;

                puzzle[x][y] = 0;
                row[x][k] = col[y][k] = square[squareIdx][k] = false;
            }
        } else {
            solution(idx + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i = 0; i < 9; i++) {
            String line = br.readLine();

            for(int j = 0; j < 9; j++) {
                int num = line.charAt(j) - '0';
                if(num == 0) continue;

                puzzle[i][j] = num;
                row[i][num] = col[j][num] = square[3 * (i / 3) + (j / 3)][num] = true;
            }
        }
        solution(0);

        for(int i = 0; i < 9; i++)
            System.out.println(Arrays.toString(puzzle[i]).replaceAll("[^0-9]", ""));
    }
}