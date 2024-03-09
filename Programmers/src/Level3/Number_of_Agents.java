package Level3;

import java.util.*;

// Exhaustive Search 완전 탐색 - Backtracking

public class Number_of_Agents { // 상담원 인원
    int k;
    int[][] reqs;
    List<List<Integer>> cases = new ArrayList<>(); // 모든 경우(각 유형에 배치된 멘토의 수)의 수

    private void backtracking(int rest, int idx, List<Integer> c) { // 모든 경우의 수 구하기
        if(rest <= 0) {
            cases.add(c);
            return;
        }

        for(int i = idx; i < c.size(); i++) {
            c.set(i, c.get(i) + 1);
            backtracking(rest - 1, i, new ArrayList<>(c));
            c.set(i, c.get(i) - 1);
        }
    }

    private int getWaiting(int caseIdx) { // 주어진 경우에서 참가자가 기다린 시간의 합 구하기
        int ret = 0; // 참가자가 기다린 시간의 합
        PriorityQueue<Integer>[] pq = new PriorityQueue[k + 1]; // 각 유형의 따른 멘토들의 상담 시간 큐

        // 초기화
        for(int i = 1; i <= k; i++) {
            pq[i] = new PriorityQueue<>();
            for(int j = 0; j < cases.get(caseIdx).get(i); j++) pq[i].add(0);
        }

        // 기다린 시간의 합 구하기
        for(int[] req: reqs) {
            int start = req[0]; // 참가자가 요청한 상담 "시각"
            int time = req[1]; // 참가자가 요청한 상담 "시간"
            int idx = req[2]; // 참가자가 요청한 상담 "유형"

            int mentor = pq[idx].remove(); // 멘토의 상담 시간

            if(mentor > start) { // 멘토의 상담 시간 > 상담 요청 시각
                ret += (mentor - start); // 대기 시간
                pq[idx].add(mentor + time);
            }

            else if(mentor < start) { // 멘토의 상담 시간 < 상담 요청 시각
                // 대기 시간 없음
                pq[idx].add(start + time);
            }

            else { // 멘토의 상담 시간 == 상담 요청 시각
                // 대기 시간 없음
                pq[idx].add(mentor + time);
            }
        }

        return ret;
    }

    public int solution(int k, int n, int[][] reqs) {
        this.k = k;
        this.reqs = reqs;
        int answer = Integer.MAX_VALUE;

        // 모든 경우(각 유형에 배치된 멘토의 수)의 수 구하기
        List<Integer> c = new ArrayList<>();
        for(int i = 0; i <= k; i++) c.add(1);
        backtracking(n - k, 1, c);

        for(int i = 0; i < cases.size(); i++)
            answer = Math.min(answer, getWaiting(i));

        return answer; // 참가자가 기다린 시간의 합의 "최소"
    }
}