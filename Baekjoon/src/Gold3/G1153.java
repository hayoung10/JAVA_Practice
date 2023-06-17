package Gold3;

import java.io.*;
import java.util.*;

public class G1153 { // 네 개의 소수
    static boolean[] isPrime = new boolean[1000001];

    private static void eratosthenes() { // 에라토스테네스의 체 이용
        isPrime[0] = isPrime[1] = true;

        for(int i = 2; i * i <= 1000000; i++) {
            if(isPrime[i]) continue;

            for(int j = i * i; j <= 1000000; j+= i)
                isPrime[j] = true;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        if(N < 8) { // 불가능한 경우
            System.out.println(-1);
            return;
        }

        eratosthenes();
        List<Integer> list = new ArrayList<>();
        list.add(2);

        // 골드바흐의 추측 이용
        if(N % 2 == 0) { // N이 짝수인 경우
            list.add(2);
            N -= 4;
        } else { // N이 홀수인 경우
            list.add(3);
            N -= 5;
        }
        
        for(int i = 2; i <= N / 2; i++) {
            if(!isPrime[i] && !isPrime[N - i]) {
                list.add(i);
                list.add(N - i);
                break;
            }
        }

        String answer = list.toString().replace("[", "").replace("]", "").replace(", ", " ");
        System.out.println(answer);
    }
}
