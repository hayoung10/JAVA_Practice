package Level3;

// Binary Search 이분탐색

public class Crossing_the_Stepping_Stone { // 징검다리 건너기
    public int solution(int[] stones, int k) {
        int start = 1, end = 1;
        for(int s : stones) end = Math.max(end, s);

        int cnt = 0;
        int mid = (start + end) / 2;
        while(start <= end) {
            // mid 명이 징검다리를 통과할 수 있는지 확인
            cnt = 0;
            for(int i = 0; i < stones.length; i++) {
                if(stones[i] <= mid) cnt++;
                else cnt = 0;

                if(cnt >= k) break; // 불가능 -> 최댓값을 내려야 함
            }
            if(cnt >= k) end = mid - 1;
            else start = mid + 1;

            mid = (start + end) / 2;
        }

        return start;
    }
}
