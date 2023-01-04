package Level3;

import java.util.*;

// Heap 힙

public class Dual_PriorityQueue { // 이중우선순위큐
    public int[] solution(String[] operations) {
        int[] answer = {0, 0};

        PriorityQueue<Integer> minPQ = new PriorityQueue<>();
        PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Collections.reverseOrder()); // 내림차순

        for(String op : operations) {
            if(op.charAt(0) == 'I') { // 주어진 숫자 삽입
                int num = Integer.parseInt(op.substring(2));
                maxPQ.add(num);
                minPQ.add(num);
            } else {
                if(!maxPQ.isEmpty()) {
                    if(op.charAt(2) == '-') { // 최솟값 삭제
                        int minNum = minPQ.peek();
                        maxPQ.remove(minNum);
                        minPQ.remove(minNum);
                    } else { // 최댓값 삭제
                        int maxNum = maxPQ.peek();
                        maxPQ.remove(maxNum);
                        minPQ.remove(maxNum);
                    }
                }
            }
        }

        if(!maxPQ.isEmpty()) {
            answer[0] = maxPQ.peek();
            answer[1] = minPQ.peek();
        }

        return answer;
    }
}
