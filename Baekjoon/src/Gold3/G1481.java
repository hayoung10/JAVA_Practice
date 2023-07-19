package Gold3;

import java.io.*;
import java.util.*;

public class G1481 { // 숌 사각형
    static int N, D, diff;
    static int[][] rect;
    static boolean[][] row, col;
    static boolean flag = false;

    private static void solution(int idx) { // BackTracking 이용
        if(idx >= N * N) { // 규칙에 적합한 지 여부 확인
            for(int i = 0; i < N; i++) {
                if(i > diff && (!row[i][0] || !col[i][0])) {
                    flag = false; return;
                }
                for(int k = 1 ; k < D; k++) {
                    if(!row[i][k] || !col[i][k]) {
                        flag = false; return;
                    }
                }
            }
            flag = true; return;
        }

        int x = idx / N;
        int y = idx % N;
        if(diff < x && diff < y) {
            for(int k = 0; k < D; k++) { // 사전순으로 빠른 답을 구하기 위해 0부터 탐색
                if(row[x][k] || col[y][k]) continue;

                rect[x][y] = k;
                row[x][k] = col[y][k] = true;

                if(y == N - 1) solution(idx + diff + 2);
                else solution(idx + 1);
                if(flag) return;

                rect[x][y] = 0;
                row[x][k] = col[y][k] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); D = Integer.parseInt(st.nextToken());
        rect = new int[N][N]; row = new boolean[N][D]; col = new boolean[N][D];
        diff = N - D;

        // 숌 사각형 패턴 초기화
        for(int i = 0; i <= diff; i++) {
            for(int j = 1; j < D; j++) {
                rect[i][diff + j] = j; row[i][j] = true; col[diff + j][j] = true;
            }
        }
        for(int j = 0; j <= diff; j++) {
            for (int i = 1; i < D; i++) {
                rect[diff + i][j] = i; col[j][i] = true; row[diff + i][i] = true;
            }
        }

        // 사전순으로 숌 사각형 탐색 시작
        solution(N * (diff + 1) + diff + 1);

        // 숌 사각형 출력
        for(int i = 0; i < N; i++)
            System.out.println(Arrays.toString(rect[i]).replaceAll("[^0-9\\s]",""));
    }
}