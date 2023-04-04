package Level3;

// DP 동적계획법

import java.util.*;

public class Number_Typing_Contest { // 숫자 타자 대회
    int[][] cost = {
            {1, 7, 6, 7, 5, 4, 5, 3, 2, 3}, // 0 -> ?
            {7, 1, 2, 4, 2, 3, 5, 4, 5, 6}, // 1 -> ?
            {6, 2, 1, 2, 3, 2, 3, 5, 4, 5}, // 2 -> ?
            {7, 4, 2, 1, 5, 3, 2, 6, 5, 4}, // 3 -> ?
            {5, 2, 3, 5, 1, 2, 4, 2, 3, 5}, // 4 -> ?
            {4, 3, 2, 3, 2, 1, 2, 3, 2, 3}, // 5 -> ?
            {5, 5, 3, 2, 4, 2, 1, 5, 3, 2}, // 6 -> ?
            {3, 4, 5, 6, 2, 3, 5, 1, 2, 4}, // 7 -> ?
            {2, 5, 4, 5, 3, 2, 3, 2, 1, 2}, // 8 -> ?
            {3, 6, 5, 4, 5, 3, 2, 4, 2, 1}, // 9 -> ?
    }; // 번호 간 최소 이동 비용

    int[][][] DP; // DP[idx][left][right]
    String numbers;

    public int solution(String numbers) {
        // 초기화
        this.numbers = numbers;
        DP = new int[numbers.length()][10][10];
        for(int i = 0; i < numbers.length(); i++)
            for(int j = 0; j < 10; j++)
                Arrays.fill(DP[i][j], -1);

        return move(0, 4, 6);
    }

    private int move(int idx, int L, int R) {
        if(idx == numbers.length()) return 0;

        if(DP[idx][L][R] != -1) return DP[idx][L][R];

        int num = numbers.charAt(idx) - '0';
        int result = Integer.MAX_VALUE;

        // 왼손 엄지 이용
        if(num != R) result = Math.min(move(idx + 1, num, R) + cost[L][num], result);

        // 오른손 엄지 이용
        if(num != L) result = Math.min(move(idx + 1, L, num) + cost[R][num], result);

        return DP[idx][L][R] = result;
    }
}
