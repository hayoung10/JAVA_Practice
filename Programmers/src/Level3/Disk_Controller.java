package Level3;

import java.util.*;

// Heap 힙

public class Disk_Controller { // 디스크 컨트롤러
    public int solution(int[][] jobs) {
        int answer = 0;

        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]); // 작업이 요청되는 시간으로 정렬

        // 작업의 소요 시간으로 정렬
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        int idx = 0, time = 0; // time : 작업 시점

        while(idx < jobs.length || !pq.isEmpty()) {
            if(idx < jobs.length && jobs[idx][0] <= time) {
                pq.add(jobs[idx++]);
                continue;
            }

            if(!pq.isEmpty()) { // 소요 시간이 짧은 요청부터 수행
                time += pq.peek()[1];
                answer += (time - pq.peek()[0]);
                pq.remove();
            } else { // 작업 시점(time) 이후, 요청이 들어온 경우
                time = jobs[idx][0];
            }
        }

        return answer / jobs.length;
    }
}
