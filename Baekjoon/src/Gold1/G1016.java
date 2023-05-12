package Gold1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G1016 { // 제곱 ㄴㄴ 수
    private static int solution(long min, long max) {
        int answer = 0;

        boolean[] num = new boolean[(int)(max - min + 1)];

        for(long i = 2; i * i <= max; i++) {
            long check = min / (i * i);
            if(min % (i * i) > 0) check++;

            while(check * i * i <= max) {
                num[(int)(check * i * i - min)] = true;
                check++;
            }
        }

        for(int i = 0; i <= max - min; i++)
            if(!num[i]) answer++;

        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long min = Long.parseLong(st.nextToken());
        long max = Long.parseLong(st.nextToken());

        System.out.println(solution(min, max));
    }
}
