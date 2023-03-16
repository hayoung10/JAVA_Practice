package Level3;

public class Chuseok_Traffic { // 추석 트래픽
    public int solution(String[] lines) {
        int answer = 0;

        if(lines.length == 1) return 1;

        int[] sTime = new int[lines.length]; // start time
        int[] eTime = new int[lines.length]; // end time

        // milliseconds 단위로 바꿔서 start time과 end time 구하기
        for(int i = 0; i < lines.length; i++) {
            int hh = Integer.parseInt(lines[i].substring(11,13)) * 3600 * 1000; // hour
            int mm = Integer.parseInt(lines[i].substring(14,16)) * 60 * 1000; // minute
            int ss = Integer.parseInt(lines[i].substring(17,19)) * 1000; // seconds
            int ms = Integer.parseInt(lines[i].substring(20,23)); // milliseconds
            int T = (int) (Double.parseDouble(lines[i].substring(24, lines[i].length() - 1)) * 1000); // 처리시간

            int end_time = hh + mm + ss + ms;
            eTime[i] = end_time;
            sTime[i] = (end_time - T + 1);
        }

        // 초당 처리량 구하기
        int cnt = 1;
        for(int i = 0; i < lines.length - 1; i++) {
            cnt = 1;

            for(int j = i + 1; j < lines.length; j++)
                if(eTime[i] + 1000 > sTime[j]) // 1s -> 1000
                    cnt++;

            answer = Math.max(answer, cnt);
        }

        return answer;
    }
}
