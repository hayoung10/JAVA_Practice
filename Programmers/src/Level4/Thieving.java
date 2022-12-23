package Level4;

// DP 동적계획법

public class Thieving {
    public int solution(int[] money) {
        int len = money.length;

        int[] firstDP = new int[len]; // 첫 번째 집을 선택한 경우
        int[] secondDP = new int[len]; // 두 번째 집부터 선택한 경우

        firstDP[0] = money[0];
        firstDP[1] = money[0]; // 두 번째 집을 선택하지 못하므로

        secondDP[0] = 0; // 첫 번째 집을 선택하지 않으므로
        secondDP[1] = money[1];

        for(int i = 2; i < len - 1; i++) {
            firstDP[i] = Math.max(firstDP[i - 2] + money[i], firstDP[i - 1]);
            secondDP[i] = Math.max(secondDP[i - 2] + money[i], secondDP[i - 1]);
        }
        secondDP[len - 1] = Math.max(secondDP[len - 3] + money[len - 1], secondDP[len - 2]);

        return Math.max(firstDP[len - 2], secondDP[len - 1]);
    }
}
