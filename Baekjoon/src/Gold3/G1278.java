package Gold3;

import java.io.*;

public class G1278 { // 연극
    static int N;
    private static void recursive(int n) {
        if(n == 0) return; // 연극이 끝난 경우
        recursive(n - 1);
        System.out.println(n);
        recursive(n - 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int K = (1 << N) - 1; // 최대의 장면 수 (= 2^N - 1)
        System.out.println(K);
        recursive(N);
        System.out.println(N); // 마지막 장면에서 무대에 서게 되는 배우
    }
}