package Level3;

public class Star_Sequence { // 스타 수열
    public int solution(int[] a) {
        int answer = -1;

        int[] num_cnt = new int[a.length];
        for(int i = 0; i < a.length; i++) num_cnt[a[i]]++;

        for(int i = 0; i < num_cnt.length; i++) {
            if(num_cnt[i] == 0 || num_cnt[i] <= answer) continue;

            int count = 0; // 사용된 횟수
            for(int j = 0; j < a.length - 1; j++) {
                if(a[j] != i && a[j + 1] != i) continue; // 인접한 2개의 원소 중 공통된 원소가 있어야 함
                if(a[j] == a[j + 1]) continue; // 인접한 원소는 서로 달라야 함
                count++; j++;
            }

            answer = Math.max(answer, count);
        }

        return answer * 2;
    }
}
