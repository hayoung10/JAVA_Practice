package Gold5;

import java.io.*;

public class G2023 { // 신기한 소수
    static int N;

    private static boolean isPrime(int num) { // 소수 판별
        if(num == 0 || num == 1) return false;

        for(int i = 2; i * i <= num; i++)
            if(num % i == 0) return false;

        return true;
    }

    private static void makeNum(int num, int len) { // 신기한 소수 만들기
        if(len == N) {
            System.out.println(num);
            return;
        }

        for(int i = 1; i < 10; i += 2) {
            if(isPrime(num * 10 + i))
                makeNum(num * 10 + i, len + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int[] prime = {2, 3, 5, 7}; // 소수를 구성하는 숫자
        for(int i = 0; i < 4; i++)
            makeNum(prime[i], 1);
    }
}
