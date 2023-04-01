package Level3;

import java.util.*;

public class Performance_appraisal { // 인사고과
    public int solution(int[][] scores) {
        int[] wanho = scores[0];

        // 근무 태도 점수 내림차순 정렬 (동점 시 동료 평가 점수 오름차순으로 정렬)
        Arrays.sort(scores, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0]);

        int maxScore = 0; // 동료 평가 점수 비교
        ArrayList<Integer> score_ranking = new ArrayList<>();

        for(int[] score : scores) {
            if(score[1] < maxScore) { // 두 점수가 모두 낮은 경우
                if(score.equals(wanho)) return -1; // 완호가 인센티브를 받지 못하는 경우
            } else { // 인센티브 대상
                score_ranking.add(score[0] + score[1]);
                maxScore = Math.max(maxScore, score[1]);
            }
        }

        score_ranking.sort(((o1, o2) -> o2 - o1)); // 총점 내림차순으로 정렬

        return score_ranking.indexOf(wanho[0] + wanho[1]) + 1;
    }
}
