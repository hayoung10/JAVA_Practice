package Gold3;

import java.io.*;
import java.util.*;

public class G1695 { // 팰린드롬 만들기
    static int[] arr;
    static int[][] dp; // dp[a][b] : a~b까지의 수열을 (팰린드롬으로 만들기 위해) 끼워 넣어야 하는 수들의 최소 개수

    private static int solution(int left, int right) {
        if(left >= right) return 0;

        if(dp[left][right] != -1) return dp[left][right]; // 이미 찾은 값인 경우

        int minCnt = 0; // 수열에 끼워 넣어야 하는 최소 개수

        // 팰린드롬 상태인 경우
        if(arr[left] == arr[right]) minCnt = solution(left + 1, right - 1);
        // 아닌 경우, 왼쪽에 수를 끼워 넣는 경우와 오른쪽에 수를 끼워 넣는 경우를 비교
        else minCnt = Math.min(solution(left, right - 1) + 1, solution(left + 1, right) + 1);
        return dp[left][right] = minCnt;
    }

    public static void main(String[] args) throws IOException {
        // 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
        dp = new int[N + 1][N + 1];
        for(int i = 0; i < N; i++) Arrays.fill(dp[i], -1);

        int answer = solution(0, N - 1);
        System.out.println(answer);
    }
}