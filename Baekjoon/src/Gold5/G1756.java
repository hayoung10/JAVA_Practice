package Gold5;

import java.io.*;
import java.util.*;

public class G1756 { // 피자 굽기
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int D = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        long[] oven = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        long[] pizza = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();

        for(int i = 1; i < D; i++) {
            if(oven[i] > oven[i - 1])
                oven[i] = oven[i - 1];
        }

        int answer = D;
        for(int i = 0; i < N; i++) {
            for(int j = answer - 1; j >= 0; j--) {
                answer--;
                if(oven[j] >= pizza[i]) break; // 피자 반죽이 오븐에 들어가는 경우
            }

            if(answer <= 0) { // 피자가 모두 오븐에 들어가지 않는 경우
                answer = -1;
                break;
            }
        }
        System.out.println(answer + 1);
    }
}