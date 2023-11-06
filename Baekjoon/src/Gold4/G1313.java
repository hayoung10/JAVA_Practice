package Gold4;

import java.io.*;

public class G1313 { // 합성소수
    private static int[] N;
    static boolean[] isCD = new boolean[10000001]; // 합성소수인지 확인

    private static boolean isPrime(int num) { // 소수인지 판별
        if(num == 0 || num == 1) return false;

        for(int i = 2; i * i <= num; i++)
            if(num % i == 0) return false;

        return true;
    }

    private static void eratosthenes(int maxNum) { // 에라토스테네스의 체 이용
        for(int i = 113; i <= maxNum; i++) {
            if(isPrime(i)) continue;

            if(isCompositeDecimal(i)) isCD[i] = true;
        }
    }

    private static boolean isCompositeDecimal(int num) { // 합성소수인지 판별
        String str = Integer.toString(num);

        // N의 연속되는 진부분수가 모두 소수인지 확인
        for(int i = 0; i < str.length() - 1; i++) {
            for(int j = i + 2; j <= str.length(); j++) {
                String strSub = str.substring(i, j); // 연속되는 진부분수
                if(str.equals(strSub)) continue;
                if(!isPrime(Integer.parseInt(strSub))) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        N = new int[T];
        int maxN = 0;
        for(int i = 0; i < T; i++) {
            N[i] = Integer.parseInt(br.readLine());
            maxN = Math.max(maxN, N[i]);
        }
        eratosthenes(maxN);

        // 결과 출력
        outer: for(int i = 0; i < T; i++) {
            for(int j = N[i]; j >= 113; j--) {
                if(isCD[j]) {
                    System.out.println(j);
                    continue outer;
                }
            }
            System.out.println(-1); // N이하인 합성소수가 존재하지 않는 경우
        }
    }
}
