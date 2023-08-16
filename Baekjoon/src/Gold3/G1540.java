package Gold3;

import java.io.*;

public class G1540 { // 정사각형의 개수
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        if(N < 4) {
            System.out.println(0); return; // 정사각형을 만들 수 없는 경우
        }

        // N보다 작으면서 (m * m)개의 점을 꼭짓점으로 하는 정사각형의 개수의 최댓값 먼저 구하기
        int sqrtN = (int) Math.sqrt(N); // 소숫점 아래를 버린 N의 제곱근 = m
        int answer = 0;
        for(int i = 1; i < sqrtN; i++) answer += (i * i);

        int idx = sqrtN * sqrtN + 1;
        int add = 0; // 추가해야 할 정사각형의 개수
        while(idx <= N) {
            if(add < sqrtN) answer += add++;
            else if(add == sqrtN) add = 1;
            idx++;
        }
        System.out.println(answer);
    }
}
