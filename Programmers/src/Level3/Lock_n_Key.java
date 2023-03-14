package Level3;

import java.util.*;

public class Lock_n_Key { // 자물쇠와 열쇠
    int M, N, size;
    int[][] map;

    public boolean solution(int[][] key, int[][] lock) {
        M = key.length;
        N = lock.length;
        size = 2 * M + N - 2;

        map = new int[size][size];
        for(int i = 0; i < N; i++)
            for(int j = 0; j < N; j++)
                map[i + M - 1][j + M - 1] = lock[i][j]; // map 가운데에 lock 배치

        for(int k = 0; k < 4; k++) { // 4번 회전전
            for(int i = 0; i < M + N - 1; i++) {
                for (int j = 0; j < M + N - 1; j++) {
                    if (check(i, j, key)) return true;
                }
            }
            key = rotate90(key);
        }

        return false;
    }

    private int[][] rotate90(int[][] key) { // 시계 방향으로 90도 회전
        int[][] tmp = new int[M][M];
        for(int i = 0; i < M; i++)
            for(int j = 0; j < M; j++)
                tmp[j][M - i - 1] = key[i][j];

        return tmp;
    }

    private boolean check(int x, int y, int[][] key) { // 열쇠로 자물쇠를 열 수 있는지 확인
        int[][] tmp = new int[size][size];
        for(int i = 0; i < size; i++)
            tmp[i] = Arrays.copyOf(map[i], size);

        for(int i = x; i < x + M; i++)
            for(int j = y; j < y + M; j++)
                tmp[i][j] += key[i - x][j - y];

        for(int i = M - 1; i < M + N - 1; i++) {
            for(int j = M - 1; j < M + N - 1; j++) {
                if(tmp[i][j] != 1) return false; // map에서 자물쇠(lock) 구간을 완벽하게 채우지 못한 경우
            }
        }

        return true;
    }
}
