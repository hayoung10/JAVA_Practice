package Level3;

import java.util.*;

// Binary Search 이분탐색

public class Immigration { // 입국심사
    public long solution(int n, int[] times) {
        long answer = 0;

        long people = 0;

        Arrays.sort(times);
        long start = 1; // min time : 1
        long end = (long) times[times.length - 1] * n; // max time
        long mid = (start + end) / 2;

        while(start <= end) {
            for(int t : times)
                people += mid / t;

            if(n <= people) {
                answer = mid; // find one of the targets
                end = mid - 1;
            } else {
                start = mid + 1;
            }

            mid = (start + end) / 2;
            people = 0;
        }

        return answer;
    }
}
