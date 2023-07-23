package Gold3;

import java.io.*;
import java.util.*;

public class G3165 { // 5
    private static int count(String str) { // 5인 자릿 수를 세기
        int cnt = 0;
        for(int i = 0; i < str.length(); i++)
            if(str.charAt(i) == '5') cnt++;
        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long N = Long.parseLong(st.nextToken()); int K = Integer.parseInt(st.nextToken());

        String answer = String.valueOf(N + 1);
        long num = 0;
        int len = answer.length();
        int idx = 1; // 뒤에서부터 접근한 자릿수
        while(true) {
            if(count(answer) >= K) break;

            while(answer.charAt(len - idx) == '5' && idx < len) // 뒤에서 부터 접근
                idx++;

            num = Long.parseLong(answer) + (long) Math.pow(10, idx - 1);
            answer = String.valueOf(num);
            len = answer.length();
        }

        System.out.println(answer);
    }
}