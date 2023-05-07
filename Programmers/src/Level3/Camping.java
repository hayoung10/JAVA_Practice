package Level3;

// Prefix Sum 누적합

import java.util.*;

public class Camping {
    public int solution(int n, int[][] data) {
        int answer = 0;

        Arrays.sort(data, (a, b) -> {
            if(a[0] == b[0]) return a[1] - b[1];
            else return a[0] - b[0];
        });

        for(int i = 0; i < n; i++) {
            int x1 = data[i][0];

            for(int j = i + 1; j < n; j++) {
                int x2 = data[j][0];
                int y1 = Math.min(data[i][1], data[j][1]);
                int y2 = Math.max(data[i][1], data[j][1]);

                if(x1 == x2 || y1 == y2) continue; // 같은 축에 있는 경우

                boolean flag = true; // 텐트가 점유하는 직사각형 영역 내부에 다른 쐐기를 포함하는지 여부 판단
                for(int k = i + 1; k < j; k++) {
                    if(data[k][0] > x1 && data[k][0] < x2 && data[k][1] > y1 && data[k][1] < y2) {
                        flag = false;
                        break;
                    }
                }
                if(flag == true) answer++; // 텐트를 설치할 수 있는 쐐기의 쌍인 경우
            }
        }

        return answer;
    }
}
