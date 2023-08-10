package Gold3;

import java.io.*;
import java.util.*;

public class G1423 { // 원숭이 키우기
    private static int N, D;
    private static int[] num, power;
    // dp[훈련기간][레벨][남은 캐릭터의 수]
    // dp[a][b][c] : 훈련 기간 a에 b레벨 가진 캐릭터가 선택할 수 있는 남은 캐릭터의 수가 c와 같은 때 추가로 낼 수 있는 최대의 힘
    private static int[][][] dp;
    private static boolean[][][] visited; // 방문 여부

    private static int solution(int a, int b, int c) {
        if(b == N - 1) return 0; // 최대 레벨이므로 훈련시킬 필요 없음

        if(visited[a][b][c]) return dp[a][b][c]; // 방문 여부 확인

        visited[a][b][c] = true;
        for(int i = 0; i <= num[b] + c; i++) {
            if(a < i) break;
            dp[a][b][c] = Math.max(dp[a][b][c], solution(a - i, b + 1, i) + (power[b + 1] - power[b]) * i);
        }
        return dp[a][b][c];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        num = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        power = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        D = Integer.parseInt(br.readLine());

        long answer = 0;
        for(int i = 0; i < N; i++) answer += (long) num[i] * power[i]; // 초기 힘의 합
        dp = new int[D + 1][N + 1][D + 1];
        visited = new boolean[D + 1][N + 1][D + 1];
        System.out.println(answer + (long) solution(D, 0, 0));
    }
}