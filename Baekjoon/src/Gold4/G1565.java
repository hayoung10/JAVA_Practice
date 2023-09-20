package Gold4;

import java.io.*;
import java.util.*;

public class G1565 { // 수학
    private static int getGCD(int num1, int num2) { // 최대공약수 구하기
        if(num1 % num2 == 0) return num2;
        return getGCD(num2, num1 % num2);
    }

    private static Map<Integer, Integer> lcm(Map<Integer, Integer> f1, Map<Integer, Integer> f2) { // 최소공배수 구하기
        Map<Integer, Integer> factors = new HashMap<>();
        for(Integer factor : f1.keySet()) {
            if(f2.containsKey(factor)) factors.put(factor, Math.max(f1.get(factor), f2.get(factor)));
            else factors.put(factor, f1.get(factor));
        }
        for(Integer factor : f2.keySet())
            if(!f1.containsKey(factor)) factors.put(factor, f2.get(factor));

        return factors;
    }

    private static Map<Integer, Integer> primeFactorization(int num) { // 소인수분해
        Map<Integer, Integer> factors = new HashMap<>();

        for(int i = 2; i * i <= num; i++) {
            while(num % i == 0) {
                if(factors.containsKey(i)) factors.put(i, factors.get(i) + 1);
                else factors.put(i, 1);
                num /= i;
            }
        }
        if(num > 1) factors.put(num, 1);

        return factors;
    }

    // (소인수분해된 값들을 통해) 배열 D의 최소공배수의 배수이면서 배열 M의 최대공약수의 약수인 개수 구하기
    private static int solution(Map<Integer, Integer> lcmDFactors, Map<Integer, Integer> gcdMFactors) {
        int ref = 1;
        for(Integer factor : lcmDFactors.keySet()) {
            if(!gcdMFactors.containsKey(factor)) {
                return 0;
            }
            if(gcdMFactors.get(factor) < lcmDFactors.get(factor)) {
                return 0;
            }
            gcdMFactors.put(factor, gcdMFactors.get(factor) - lcmDFactors.get(factor));
        }
        for(Integer cnt : gcdMFactors.values())
            ref *= (cnt + 1);
        return ref;
    }

    public static void main(String[] args) throws IOException {
        // 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] D = new int[Integer.parseInt(st.nextToken())];
        int[] M = new int[Integer.parseInt(st.nextToken())];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < D.length; i++) D[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M.length; i++) M[i] = Integer.parseInt(st.nextToken());

        // 배열 D의 소인수분해된 최소공배수 구하기
        Map<Integer, Integer> lcmDFactors = primeFactorization(D[0]);
        for(int i = 1; i < D.length; i++) lcmDFactors = lcm(lcmDFactors, primeFactorization(D[i]));

        // 배열 M의 소인수분해된 최대공약수 구하기
        int gcdM = M[0];
        for(int i = 1; i < M.length; i++) gcdM = getGCD(gcdM, M[i]);
        Map<Integer, Integer> gcdMFactors = primeFactorization(gcdM);

        System.out.println(solution(lcmDFactors, gcdMFactors));
    }
}