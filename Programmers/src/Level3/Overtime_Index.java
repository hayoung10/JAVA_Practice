package Level3;

import java.util.*;

public class Overtime_Index { // 야근 지수
    public long solution(int n, int[] works) {
        // 초기화
        long sum = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int work : works) {
            sum += work;
            pq.add(work);
        }
        if(sum <= n) return 0; // 남은 작업량이 없는 경우

        for(int i = 0; i < n; i++) { // 남은 작업량 구하기
            int work = pq.remove();
            pq.add(work - 1);
        }
        sum = 0;
        for(int work : pq)
            sum += Math.pow(work, 2); // 야근 지수 구하기

        return sum;
    }
}
