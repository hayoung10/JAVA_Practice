package Gold4;

import java.io.*;
import java.util.*;

public class G1188 { // 음식 평론가
    private static int gcd(int a, int b) {
        if(a % b == 0) return b;

        return gcd(b, a % b);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int answer = M - gcd(N, M);
        System.out.println(answer);
    }
}
