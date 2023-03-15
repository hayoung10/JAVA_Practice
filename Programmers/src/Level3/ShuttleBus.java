package Level3;

import java.util.*;

public class ShuttleBus { // 셔틀버스
    public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";

        // 크루가 대기열에 도착하는 시각을 '분'으로 바꾸기
        int[] crew = new int[timetable.length];
        for(int i = 0; i < timetable.length; i++) {
            int c_hour = Integer.parseInt(timetable[i].substring(0, 2));
            int c_min = Integer.parseInt(timetable[i].substring(3));
            crew[i] = c_hour * 60 + c_min;
        }
        Arrays.sort(crew); // 먼저 도착한 순서대로 정렬

        int cur_time = 540; // start 09:00
        int idx = 0;
        int ans = 0;
        for(int i = 1; i <= n; i++, cur_time += t) {
            int cnt = 0;
            for(int j = idx; j < crew.length; j++) {
                if(crew[j] <= cur_time) { // cur_time을 기준으로 대기열에 도착한 크루 확인
                    idx++; cnt++;
                    if(cnt == m) break;
                }
            }
            if(i == n) { // 콘이 셔틀을 타고 갈 수 있는 제일 늦은 도착 시간 구하기
                if(cnt == m) ans = crew[idx - 1] - 1;
                else ans = cur_time;
            }
        }

        // HH:MM 형식으로 바꾸기
        int hour = ans / 60;
        if(hour < 10) answer = "0" + Integer.toString(hour) + ":";
        else answer = Integer.toString(hour) + ":";

        int min = ans % 60;
        if(min < 10) answer += "0" + Integer.toString(min);
        else answer += Integer.toString(min);

        return answer;
    }
}
