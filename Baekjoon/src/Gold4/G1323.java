package Gold4;

import java.io.*;
import java.util.*;

public class G1323 { // 숫자 연결하기
    public static void main(String[] args) throws IOException {
        // 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String str = st.nextToken();
        long digit = (long) Math.pow(10, str.length());
        int N = Integer.parseInt(str);
        int K = Integer.parseInt(st.nextToken());

        int answer = 1;
        boolean[] check = new boolean[100001];
        int remainder = N % K; // 나머지
        check[remainder] = true;
        while(remainder != 0) {
            remainder = (int) ((remainder * digit + N) % K);
            if(check[remainder]) { // 한 번 나온 나머지가 다시 또 나온 경우 -> 불가능한 경우
                answer = -1;
                break;
            }
            check[remainder] = true;
            answer++;
        }
        System.out.println(answer);
    }
}