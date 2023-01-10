package Level3;

import java.util.*;

// Greedy 탐욕법

public class Enforcement_Camera { // 단속카메라
    public int solution(int[][] routes) {
        int answer = 1; // 최소 카메라 수 : 1

        Arrays.sort(routes, (o1, o2) -> {
            if(o1[0] == o2[0])
                return Integer.compare(o1[1], o2[1]);
            else
                return Integer.compare(o1[0], o2[0]);
        }); // 오름차순으로 정렬

        int point = routes[0][1]; // 기준 지점
        for(int i = 0; i < routes.length; i++) {
            if(point < routes[i][0]) {
                point = routes[i][1]; // 업데이트
                answer++; // 카메라 설치
            }

            if(point > routes[i][1]) // 기준보다 먼저 나가는 차량이 있을 경우
                point = routes[i][1]; // 업데이트
        }

        return answer;
    }
}
