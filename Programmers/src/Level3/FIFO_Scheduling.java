package Level3;

// Binary Search 이분탐색

public class FIFO_Scheduling { // 선입 선출 스케줄링
    int[] cores;
    public int solution(int n, int[] cores) {
        int answer = 0;

        this.cores = cores;

        int start = 0; // 시간의 최솟값
        int end = 10000 * n; // 시간의 최댓값
        int time = 0;
        int m = 0;

        while(start <= end) {
            int mid = (start + end) / 2;

            int count = calculate_count(time);
            if(count >= n) {
                end = mid - 1;
                time = mid;
                m = count;
            } else {
                start = mid + 1;
            }
        }

        // 마지막 작업을 처리하는 코어의 번호 구하기
        m -= n;
        for(int i = cores.length - 1; i >= 0; i--) {
            if(time % cores[i] == 0) {
                if(m == 0) {
                    answer = i + 1;
                    break;
                }

                m--;
            }
        }

        return answer;
    }

    private int calculate_count(int time) { // 시간(time)까지의 작업량 계산
        int count = cores.length;

        if(time == 0) return count;

        for(int core : cores)
            count += (time / core);

        return count;
    }
}
