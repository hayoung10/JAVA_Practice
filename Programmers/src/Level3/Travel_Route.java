package Level3;

import java.util.*;

// DFS 깊이 우선 탐색

public class Travel_Route { // 여행경로
    private String[] answer;
    private boolean[] used; // 티켓 사용여부

    public String[] solution(String[][] tickets) {
        answer = new String[tickets.length + 1];
        used = new boolean[tickets.length];

        Arrays.sort(tickets, ((o1, o2) -> { // 알파벳 순서로 정렬
            if(o1[0].equals(o2[0]))
                return o1[1].compareTo(o2[1]) < 0 ? -1 : 1;
            else
                return o1[0].compareTo(o2[0]) < 0 ? -1 : 1;
        }));

        DFS(tickets, "ICN", 0); // 항상 "ICN" 공항에서 출발

        return answer;
    }

    private boolean DFS(String[][] tickets, String depart, int depth) {
        answer[depth] = depart;

        if(tickets.length == depth)
            return true;

        for(int i = 0; i < tickets.length; i++) {
            if(tickets[i][0].equals(depart) && !used[i]) {
                used[i] = true;
                if(DFS(tickets, tickets[i][1], depth + 1))
                    return true; // 올바른 경로일 경우
                used[i] = false;
            }
        }

        return false;
    }
}
