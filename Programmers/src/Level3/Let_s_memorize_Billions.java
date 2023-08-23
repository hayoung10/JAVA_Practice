package Level3;

public class Let_s_memorize_Billions { // 억억단을 외우자
    public int[] solution(int e, int[] starts) {
        int[] answer = new int[starts.length];

        int[] cnt = new int[e + 1]; // cnt[i] : 숫자 i가 등장하는 횟수
        for(int i = 1; i <= e; i++)
            for(int j = 1; j <= e / i; j++)
                cnt[i * j]++;

        int[] max = new int[e + 1]; // max[i] : [i, e] 범위에서 가장 많이 등장한 수 중 가장 작은 수
        max[e] = e;
        for(int i = e - 1; i >= 1; i--) {
            if(cnt[i] >= cnt[max[i + 1]]) max[i] = i;
            else max[i] = max[i + 1];
        }

        for(int i = 0; i < starts.length; i++) answer[i] = max[starts[i]];
        return answer;
    }
}