package Gold3;

import java.io.*;
import java.util.*;

public class G1132 { // 합
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 초기화
        int N = Integer.parseInt(br.readLine());
        Alpha[] alphas = new Alpha[10];
        for(int i = 0; i < 10; i++) alphas[i] = new Alpha();

        for(int i = 0; i < N; i++) {
            String str = br.readLine();
            for(int j = 0; j < str.length(); j++)
                alphas[str.charAt(j) - 'A'].weight += Math.pow(10, str.length() - j - 1); // 해당 알파벳에 가중치 부여
            alphas[str.charAt(0) - 'A'].first = true; // 첫 번째로 시작하는 알파벳
        }

        Arrays.sort(alphas);
        long answer = 0;
        boolean[] used = new boolean[10]; // 숫자의 사용 여부
        for(int i = 0; i < 10; i++) {
            if(alphas[i].first) { // 첫 번째로 시작하는 알파벳인 경우
                for(int num = 1; num < 10; num++) {
                    if(!used[num]) {
                        answer += (long) num * alphas[i].weight;
                        used[num] = true;
                        break;
                    }
                }
            } else {
                for(int num = 0; num < 10; num++) {
                    if(!used[num]) {
                        answer += (long) num * alphas[i].weight;
                        used[num] = true;
                        break;
                    }
                }
            }
        }
        System.out.println(answer);
    }

    static class Alpha implements Comparable<Alpha> {
        long weight = 0;
        boolean first = false;

        @Override
        public int compareTo(Alpha o) {
            if(this.weight > o.weight) return 1;
            else if(this.weight == o.weight) return 0;
            else return -1;
        }
    }
}