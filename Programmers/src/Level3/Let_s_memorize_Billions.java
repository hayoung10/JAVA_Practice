package Level3;

import java.util.*;

public class Let_s_memorize_Billions { // 억억단을 외우자
    public int[] solution(int e, int[] starts) {
        int[] answer = new int[starts.length];

        ValueCount[] valueCounts = new ValueCount[e];
        for(int i = 0; i < e; i++) valueCounts[i] = new ValueCount(i + 1, 0);

        for(int i = 1; i <= e; i++) {
            for(int j = 1; j <= e; j++) {
                if(i * j > e) break;
                valueCounts[i * j - 1].count++;
            }
        }
        Arrays.sort(valueCounts);

        for(int i = 0; i < starts.length; i++) {
            for(int j = 0; j < e; j++) {
                if(starts[i] <= valueCounts[j].value) {
                    answer[i] = valueCounts[j].value;
                    break;
                }
            }
        }
        return answer;
    }

    class ValueCount implements Comparable<ValueCount> {
        int value;
        int count;

        public ValueCount(int value, int count) {
            this.value = value;
            this.count = count;
        }

        @Override
        public int compareTo(ValueCount o) {
            return this.count == o.count ? this.value - o.value : o.count - this.count;
        }
    }
}
