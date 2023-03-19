package Level4;

import java.util.*;

// Binary Search 이분탐색

public class Geography_Modification { // 지형 편집
    int[] blocks;
    int P, Q;
    long total; // 총 블럭 수

    public long solution(int[][] land, int P, int Q) {
        long answer = 0;

        this.P = P;
        this.Q = Q;

        int idx = 0;
        blocks = new int[land.length * land.length];
        for(int i = 0; i < land.length; i++) {
            for(int j = 0; j < land.length; j++) {
                blocks[idx++] = land[i][j];
                total += land[i][j];
            }
        }
        Arrays.sort(blocks); // 낮은 블럭 순으로 정렬

        long start = blocks[0];
        long end = blocks[blocks.length - 1];
        long mid = (start + end) / 2;

        while(start <= end) {
            long leftCost = calculateCost(mid);
            long rightCost = calculateCost(mid + 1);

            if(leftCost <= rightCost) {
                answer = leftCost;
                end = mid - 1;
            } else {
                answer = rightCost;
                start = mid + 1;
            }
            mid = (start + end) / 2;
        }

        return answer;
    }

    private long calculateCost(long height) { // 해당 높이에 대한 비용 구하기
        long add_blocks = 0; // 추가해야 되는 블럭 수

        for(int b : blocks) {
            if(b > height) break;

            add_blocks += (height - b); // b <= height
        }
        long remove_blocks = total + add_blocks - height * blocks.length; // 제거해야 되는 블럭 수

        return add_blocks * P + remove_blocks * Q;
    }
}
