package Gold3;

import java.io.*;
import java.util.*;

public class G3165 { // 5
    private static int count(String str) {
        int cnt = 0;
        for(int i = 0; i < str.length(); i++)
            if(str.charAt(i) == '5') cnt++;
        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String answer = st.nextToken(); int K = Integer.parseInt(st.nextToken());

        long N = Long.parseLong(answer);
        long num = 0;
        int len = answer.length();
        int idx = 1;
        while(true) {
            if(num > N && count(answer) >= K) break;

            while(answer.charAt(len - idx) == '5' && idx < len)
                idx++;

            num = Long.parseLong(answer) + (long) Math.pow(10, idx - 1);
            answer = String.valueOf(num);
            len = answer.length();
        }

        System.out.println(answer);
    }
}