package algorithm.math;

import java.util.*;

// 소수는 1과 자기 자신만을 약수로 가지는 수
public class Prime {

    // 소수 판별 함수
    public boolean isPrime(int num) {
        int cnt = 0; // cnt : num의 약수의 수
        for(int i = 1; i <= num; i++)
            if(num % i == 0) cnt++;

        // 소수인지 합성수인지 판별
        if(cnt == 2) return true;
        return false;
    }

    // 인자로 받은 해당 수까지의 소수들 찾기
    public List<Integer> primeList(int num) {
        List<Integer> list = new ArrayList<>();
        if(num == 1) return list; // 1까지의 소수는 없음

        int cnt = 0; // cnt : 약수의 수
        for(int i = 2; i <= num; i++) {
            cnt = 0;
            for(int j = 1; j <= i; j++)
                if(i % j == 0) cnt++;

            if(cnt == 2) list.add(i); // 소수일 경우 리스트에 삽입
        }

        return list;
    }
}
