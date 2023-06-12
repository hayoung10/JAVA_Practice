package Gold3;

import java.io.*;
import java.util.*;

public class G1030 { // 프렉탈 평면
    static int s, N, K;
    static int R1, R2, C1, C2;
    static int[][] plane = new int[50][50];

    private static boolean isBlack(int b) { // 프렉탈 평면의 검정색 부분인지 확인
        if((N - K) / 2 <= b && b < (N - K) / 2 + K) return true;
        return false;
    }

    private static void divide(int r, int c, int size, boolean color) {
        if(r + size <= C1 || r > C2 || c + size <= R1 || c > R2) return; // 범위 밖
        if(size == 1) {
            plane[c - R1][r - C1] = color ? 1 : 0; // true : 검정색(1), false : 흰색(0)
            return;
        }

        int nSize = size / N;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                divide(r + nSize * i, c + nSize * j, nSize,
                        color || (isBlack(i) && isBlack(j)));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        s = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        R1 = Integer.parseInt(st.nextToken());
        R2 = Integer.parseInt(st.nextToken());
        C1 = Integer.parseInt(st.nextToken());
        C2 = Integer.parseInt(st.nextToken());

        int size = (int) Math.pow(N, s); // 평면 크기
        divide(0, 0, size, false);

        // 출력
        for(int i = 0; i <= R2 - R1; i++) {
            for(int j = 0; j <= C2 - C1; j++)
                System.out.print(plane[i][j]);
            System.out.println();
        }
    }
}