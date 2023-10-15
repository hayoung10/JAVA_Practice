package Gold3;

public class G1318 {
    final static long total = 20358520; // = 52_C_6 (조합)

    static long[] priority = new long[12]; // 각각의 족보의 경우의 수
    static int[] Shape = new int[4]; // 카드 문양
    static int[] Num = new int[13]; // 카드 숫자
    static boolean[][] Card = new boolean[4][13]; // 카드 탐색 여부

    private static void solution(int sIdx, int nIdx, int cnt) { // Brute-Force 이용하여 모든 카드 탐색
        if(cnt == 0) { // 카드 6장을 모두 받은 경우
            priority[checkPriority() - 1]++; return;
        }
        if(nIdx >= 13) { // 해당 카드 문양의 마지막 숫자까지 탐색한 경우 다음 문양의 카드 탐색
            sIdx++; nIdx = 0;
            if(sIdx >= 4) return; // 모든 카드를 탐색한 경우
        }
        if(52 - (sIdx * 13 + nIdx) < cnt) return; // 받을 수 있는 카드가 없는 경우

        // 해당 카드를 받은 경우
        Shape[sIdx]++;
        Num[nIdx]++;
        Card[sIdx][nIdx] = true;
        solution(sIdx, nIdx + 1, cnt - 1);

        // 해당 카드를 받지 않은 경우
        Shape[sIdx]--;
        Num[nIdx]--;
        Card[sIdx][nIdx] = false;
        solution(sIdx, nIdx + 1, cnt);
    }

    private static int checkPriority() { // 족보 확인
        boolean flushStraight = false, flush = false, straight = false;

        for(int i = 0; i < 4; i++) {
            if(Card[i][0] && Card[i][1] && Card[i][2] && Card[i][3] && Card[i][4]) return 12; // 로얄 스트레이트 플러쉬
            if(Shape[i] >= 5) flush = true;
            int sfCnt = 0;
            for(int j = 0; j < 13; j++) {
                if(Card[i][j]) sfCnt++;
                else sfCnt = 0;
                if(sfCnt >= 5) flushStraight = true;
            }
        }
        if(flushStraight) return 11; // 스트레이트 플러쉬

        int sCnt = 0, pair = 0, triple = 0;
        for(int i = 0; i < 13; i++) {
            if(Num[i] > 0) sCnt++;
            else sCnt = 0;
            if(sCnt >= 5) straight = true;

            if(Num[i] == 4) return 10; // 포카드
            else if(Num[i] == 3) triple++;
            else if(Num[i] == 2) pair++;
        }

        if(triple >= 2 || (triple > 0 && pair > 0)) return 9; // 풀하우스
        if(flush) return 8; // 플러쉬
        if(Num[0] > 0 && Num[9] > 0 && Num[10] > 0 && Num[11] > 0 && Num[12] > 0) return 7; // 마운틴
        if(straight) {
            if(Num[0] > 0 && Num[1] > 0&& Num[2] > 0 && Num[3] > 0 && Num[4] > 0) return 6; // 빽스트레이트
            return 5; // 스트레이트
        }
        if(triple > 0) return 4; // 트리플
        if(pair >= 2) return 3; // 투페어
        if(pair >= 1) return 2; // 원페어
        return 1; // 탑
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
        solution(0, 0, 6);

        // 각각의 족보가 나올 확률 출력
        for(int i = 0; i < 12; i++) printFraction(priority[i]);
    }
}