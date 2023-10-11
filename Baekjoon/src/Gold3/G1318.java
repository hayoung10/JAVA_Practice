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
        long[] priority = new long[13]; // 각각의 족보의 경우의 수
        // 12 로얄 스트레이트 플러쉬
        priority[12] = combination(4, 1) * combination(52 - 5, 1);
        // 11 스트레이트 플러쉬
        priority[11] = 9 * combination(4, 1) * combination(52 - 5, 1) - priority[12];
        // 10 포카드
        priority[10] = 13 * combination(52 - 4, 2);
        // 9 풀하우스
        priority[9] = 13 * combination(4, 3) * (13 - 1) * combination(4, 2) * combination(52 - 5, 1) - priority[10];
        // 8 플러쉬
        priority[8] = combination(13, 5) * combination(4, 1) * combination(52 - 5, 1) - priority[12] - priority[11];
        // 7 마운틴
        priority[7] = (long) Math.pow(4, 5) * combination(52 - 5, 1) - (4 * combination(52 - 5, 1));
        // 6 빽스트레이트
        priority[6] = (long) Math.pow(4, 5) * combination(52 - 5, 1) - (4 * combination(52 - 5, 1));
        // 5 스트레이트
        priority[5] = 9 * (long) Math.pow(4, 5) * combination(52 - 5, 1) - 9 * 4 * combination(52 - 5, 1) - priority[6];
        // 4 트리플
        priority[4] = 13 * combination(4, 3) * combination(52 - 3, 3) - priority[10] - priority[9];
        // 3 투페어
        priority[3] = 13 * combination(4, 2) * 12 * combination(4, 2) * combination(52 - 4, 2)
                - priority[10] - priority[9] - priority[4];
        // 2 원페어
        priority[2] = 13 * combination(4, 2) * combination(52 - 2, 4);
        for(int i = 12; i > 2; i--) priority[2] -= priority[i];
        // 1 탑
        priority[1] = total;
        for(int i = 12; i > 1; i--) priority[1] -= priority[i];

        // 각각의 족보가 나올 확률 출력
        for(int i = 1; i <= 12; i++) printFraction(priority[i]);
    }
}