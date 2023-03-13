package Level4;

import java.util.*;

public class Muzi_Mukbang_Live { // 무지의 먹방 라이브
    public int solution(int[] food_times, long k) {
        ArrayList<Pair> pairs = new ArrayList<>();
        long len = food_times.length;
        long sum = 0; // 모든 음식을 먹는데 걸리는 시간

        for(int i = 0; i < len; i++) {
            pairs.add(new Pair(i + 1, food_times[i]));
            sum += food_times[i];
        }
        if(sum <= k) return -1;
        Collections.sort(pairs, ((o1, o2) -> o1.time - o2.time)); // time 기준으로 정렬

        long prevTime = 0; // 이전에 먹은 음식 시간
        long total = 0; // 먹기 위해 사용한 시간
        int idx = 0;
        for(Pair p : pairs) {
            total = len * (p.time - prevTime);

            if(total <= k) {
                k -= total;
                prevTime = p.time;
            } else {
                pairs.subList(idx, food_times.length).sort(((o1, o2) -> o1.num - o2.num)); // num 순서대로 재정렬
                idx += (k % len);
                return pairs.get(idx).num;
            }
            len--;
            idx++;
        }

        return -1;
    }

    class Pair {
        int num, time; // num : 음식 번호, time : 음식을 모두 먹는데 필요한 시간

        public Pair(int num, int time) {
            this.num = num;
            this.time = time;
        }
    }
}
