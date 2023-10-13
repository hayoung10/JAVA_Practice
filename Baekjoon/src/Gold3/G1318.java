package Gold3;

public class G1318 {
    final static long total = combination(52, 6);

    private static long combination(int n, int r) { // 조합 nCr 계산
        long num = 1;
        for(int i = 0; i < r; i++) num *= n--;
        for(int i = r; i > 0; i--) num /= i;
        return num;
    }

    private static long getGCD(long a, long b) { // 최대공약수 구하기
        if(a % b == 0) return b;
        return getGCD(b, a % b);
    }

    private static void printFraction(long num) { // a/b꼴의 분수로 출력
        long gcd = getGCD(num, total);
        long a = num / gcd;
        long b = total / gcd;
        System.out.println(a + "/" + b);
    }

    public static void main(String[] args) {
        long[] priority = new long[]{
                6612900,
                9730740,
                2532816,
                732160,
                282060,
                39780,
                39780,
                205976,
                165984,
                14664,
                1472,
                188
        }; // 각각의 족보의 경우의 수

        // 각각의 족보가 나올 확률 출력
        for(int i = 0; i < 12; i++) printFraction(priority[i]);
    }
}