package Level3;

// Prefix Sum 누적합

public class Insert_Advertising { // 광고 삽입
    public String solution(String play_time, String adv_time, String[] logs) {
        // 초기화
        int pTime = convertToInt(play_time);
        int aTime = convertToInt(adv_time);

        long[] timeCnt = new long[pTime + 1];
        for(String log : logs) {
            timeCnt[convertToInt(log.substring(0, 8))]++;
            timeCnt[convertToInt(log.substring(9))]--;
        }

        for(int i = 1; i <= pTime; i++) // 각 시각별 시청자 수
            timeCnt[i] += timeCnt[i - 1];

        for(int i = 1; i <= pTime; i++) // 각 시간별 누적 재생시간
            timeCnt[i] += timeCnt[i - 1];

        // 누적 재생시간이 가장 많은 곳의 시작 시각 구하기
        long maxTime = timeCnt[aTime - 1];
        int startTime = 0;
        for(int i = 0; i + aTime <= pTime; i++) {
            long tmp = timeCnt[i + aTime] - timeCnt[i];

            if(tmp > maxTime) {
                maxTime = tmp;
                startTime = i + 1;
            }
        }

        return convertToStr(startTime);
    }

    private int convertToInt(String s) { // 초 단위로 변환
        int ss = Integer.parseInt(s.substring(6));
        int mm = Integer.parseInt(s.substring(3, 5)) * 60;
        int hh = Integer.parseInt(s.substring(0, 2)) * 3600;

        return hh + mm + ss;
    }

    private String convertToStr(int time) { // 초 단위를 hh:mm:ss 형식으로 변환
        int hh = time / 3600;
        int mm = (time - hh * 3600) / 60;
        int ss = time - hh * 3600 - mm * 60;

        return String.format("%02d:%02d:%02d", hh, mm, ss);
    }
}
