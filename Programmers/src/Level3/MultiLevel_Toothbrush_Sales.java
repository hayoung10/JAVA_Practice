package Level3;

import java.util.*;

// DFS 깊이 우선 탐색

public class MultiLevel_Toothbrush_Sales { // 다단계 칫솔 판매
    private HashMap<String, String> er = new HashMap<>(); // enroll-referral
    private HashMap<String, Integer> result = new HashMap<>(); // enroll-result

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];

        for(int i = 0; i < enroll.length; i++) // mapping
            er.put(enroll[i], referral[i]);

        for(int i = 0; i < seller.length; i++)
            DFS(seller[i], amount[i] * 100); // 이익은 개당 100원

        for(int i = 0; i < enroll.length; i++)
            answer[i] = result.getOrDefault(enroll[i], 0);

        return answer;
    }

    private void DFS(String name, int profit) {
        if(name == "-") return;

        int give = profit / 10; // 추천인에게 배분할 10%의 금액
        int get = profit - give;
        result.put(name, result.getOrDefault(name, 0) + get);

        if(give < 1) return; // 10%를 계산한 금액이 1원 미만인 경우
        DFS(er.get(name), give);
    }
}
