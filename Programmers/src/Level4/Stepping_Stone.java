package Level4;

import java.util.*;

// Binary Search 이분탐색

public class Stepping_Stone { // 징검다리
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;

        Arrays.sort(rocks); // 오름차순 정렬
        int[] rocksArr = Arrays.copyOf(rocks, rocks.length + 1);
        rocksArr[rocks.length] = distance; // 도착지점 추가

        int left = 0, right = distance;
        while(left <= right) {
            int mid = (left + right) / 2; // mid : 거리의 최솟값으로 가정
            int cur = 0, cnt = 0; // cur : 현재 위치, cnt : 제거한 바위의 수

            for(int i = 0; i < rocksArr.length; i++) {
                if(mid > rocksArr[i] - cur) cnt++; // 바위 제거
                else cur = rocksArr[i]; // 현재 위치를 해당 바위로 설정
            }

            if(cnt > n) { // 불가능
                right = mid - 1;
            } else { // 가능
                answer = Math.max(answer, mid);
                left = mid + 1;
            }
        }

        return answer;
    }
}
