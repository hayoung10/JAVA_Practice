package Gold4;

import java.io.*;
import java.util.*;

public class G1022 { // 소용돌이 예쁘게 출력하기
    private static int calculate(int r, int c) { // 소용돌이 숫자 계산
        int bound = Math.max(Math.abs(r), Math.abs(c));
        int val = (int) Math.pow(2 * bound - 1, 2) + 1;

        if(r == bound) return val + 7 * bound - 1 + c;
        if(c == -bound) return val + 5 * bound - 1 + r;
        if(r == -bound) return val + 3 * bound - 1 - c;
        return val + bound - 1 - r;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int r1 = Integer.parseInt(st.nextToken());
        int c1 = Integer.parseInt(st.nextToken());
        int r2 = Integer.parseInt(st.nextToken());
        int c2 = Integer.parseInt(st.nextToken());

        int[][] swirl = new int[r2 - r1 + 1][c2 - c1 + 1]; // 소용돌이
        int maxNum = 0;
        for(int i = r1; i <= r2; i++) {
            for(int j = c1; j <= c2; j++) {
                swirl[i - r1][j - c1] = calculate(i, j);
                maxNum = Math.max(maxNum, swirl[i - r1][j - c1]);
            }
        }

        // 출력하기
        StringBuilder stringBuilder = new StringBuilder("%");
        stringBuilder.append(String.valueOf(maxNum).length()).append("d ");
        for(int i = 0; i <= r2 - r1; i++) {
            for(int j = 0; j <= c2 - c1; j++)
                System.out.printf(stringBuilder.toString(), swirl[i][j]);
            System.out.println();
        }
    }
}