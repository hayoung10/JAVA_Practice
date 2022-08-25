package algorithm.math;

// 두 수를 입력 받아서 거듭 제곱을 구하기
public class Power {

    // 재귀를 활용한 거듭 제곱 구현 메서드
    public long power_recursion(long base, long exponent) {
        if(exponent == 0) return 1;

        long tmp = power_recursion(base, exponent / 2);
        if(exponent % 2 == 0) return tmp * tmp;
        return tmp * tmp * base;
    }

    // 반복문을 활용한 거듭 제곱 구현 메서드
    public long power_iteration(int base, int exponent) {
        if(exponent == 0) return 1;

        long multiply = base;
        long result = 1;
        int cnt = exponent;

        while(cnt > 0) {
            if(cnt % 2 == 1) result *= multiply;

            multiply *= multiply;
            cnt /= 2;
        }

        return result;
    }

    // 거듭 제곱 결과를 추가로 입력 받은 숫자로 나눈 나머지 구하기
    public long power(int base, int exponent, int mod) {
        if(exponent == 0) return 1;

        long multiply = base % mod;
        long result = 1;
        int cnt = exponent;

        while(cnt > 0) {
            if(cnt % 2 == 1) {
                result *= multiply;
                result %= mod;
            }
            multiply *= multiply;
            multiply %= mod;
            cnt /= 2;
        }

        return result;
    }
}
