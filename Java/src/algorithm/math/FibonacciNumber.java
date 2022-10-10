package algorithm.math;

public class FibonacciNumber {

    // 재귀를 활용한 피보나치 수 구현 함수
    public int fibonacci_recursion(int num) {
        if(num <= 1) return num;

        return fibonacci_recursion(num - 1) + fibonacci_recursion(num - 2); // 재귀
    }

    // 동적 프로그래밍을 활용한 피보나치 수 구현 함수
    public int fibonacci_dp(int num) {
        if(num <= 1) return num;

        int[] dp = new int[num + 1];
        dp[0] = 0;
        dp[1] = 1;
        for(int i = 2; i <= num; i++)
            dp[i] = dp[i - 1] + dp[i - 2];

        return dp[num];
    }

    // 피보나치 공식을 이용한 피보나치 수 구현 함수
    public int fibonacci_formula(int num) {
        double a = (1 + Math.sqrt(5)) / 2;
        double b = (1 - Math.sqrt(5)) / 2;

        return (int)Math.round((Math.pow(a, num) - Math.pow(b, num)) / Math.sqrt(5));
    }
}

