package algorithm.math;

// 최대 공약수 (GCD), 최소 공배수 (LCM) 구현

public class GCDnLCM {
    
    // 재귀를 활용한 GCD 구현 메서드
    public int GCD_recursion(int a, int b) {
        if(b == 0) return a;
        return GCD_recursion(b, a % b);
    }
    
    // 반복문을 활용한 GCD 구현 메서드
    public int GCD_iteration(int a, int b) {
        int tmp;
        while(b != 0) {
            tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }

    // 위의 GCD 구현 메서드 중 하나를 이용한 LCM 구현 메서드
    public int LCM(int a, int b) {
        return (a * b) / GCD_recursion(a, b);
    }
}
