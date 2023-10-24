package Gold3;

import java.io.*;

public class G1341 { // 사이좋은 형제
    public static void main(String[] args) throws IOException {
        // 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        long a = Long.parseLong(input[0]);
        long b = Long.parseLong(input[1]);

        if(a == 0) {
            System.out.println("-");
            return;
        } else if(a == 1) { // b == 0
            System.out.println("*");
            return;
        }

        boolean flag = false;
        if(a < b - a) {
            a = b - a;
            flag = true;
        }

        // 'a / b = ma / mb' 에서 m 구하기
        int n = 1;
        long mb = 0, m = -1;
        while(n <= 60) {
            mb = (long) Math.pow(2, n) - 1;

            if(mb % b == 0) {
                m = mb / b;
                break;
            }
            n++;
        }
        if(m == -1) {
            System.out.println(-1);
            return;
        }

        StringBuilder sb = new StringBuilder(Long.toBinaryString(m * a));
        if(flag) {
            for (int i = 0; i < sb.length(); i++) {
                if(sb.charAt(i) == '1') sb.replace(i, i + 1, "-");
                else sb.replace(i, i + 1, "*");
            }
        } else {
            for(int i = 0; i < sb.length(); i++) {
                if(sb.charAt(i) == '1') sb.replace(i, i + 1, "*");
                else sb.replace(i, i + 1, "-");
            }
        }
        System.out.println(sb);
    }
}