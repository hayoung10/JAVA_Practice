package Level3;

import java.util.*;

public class Number_Game { // 숫자 게임
    public int solution(int[] A, int[] B) {
        int answer = 0;

        Arrays.sort(A); // A팀의 숫자들을 오름차순으로 정렬

        PriorityQueue<Integer> pq = new PriorityQueue<>(); // B 팀의 숫자들
        for(int num : B) pq.add(num);

        int idx = 0;
        while(!pq.isEmpty()) {
            if(idx == A.length) break;

            int bNum = pq.remove();
            if(bNum > A[idx]) {
                idx++;
                answer++; // B팀의 사원이 승리 -> 1점 얻음
            }
        }

        return answer;
    }
}
