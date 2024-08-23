package Level3;

import java.util.*;

// DP 동적계획법

public class AirConditioner { // 에어컨
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        // 초기화
        int maxPower = 1000 * 100 + 1;
        t1 += 10; t2 += 10; temperature += 10; // 온도 범위를 0~50으로 바꿔서 계산

        int dp[][] = new int[onboard.length][51]; // dp[i][j] : i분에 j 온도를 맞추기 위한 최소 소비전력
        for(int i = 0; i < onboard.length; i++)
            Arrays.fill(dp[i], maxPower);
        dp[0][temperature] = 0;

        //int flag = temperature > t2 ? -1 : 1;
        for(int i = 0; i < onboard.length - 1; i++) {
            for(int j = 0; j < 51; j++) {
                if(onboard[i] == 1 && (j < t1 || j > t2)) continue; // 탑승 중 온도 범위를 벗어난 경우

                // 1) 에어컨의 전원을 킨 경우 (on)
                // 1-1) 온도를 1도 높이거나 낮추는 경우 (a원 소비)
                if(j > 0) dp[i + 1][j - 1] = Math.min(dp[i][j] + a, dp[i + 1][j - 1]);
                if(j < 50) dp[i + 1][j + 1] = Math.min(dp[i][j] + a, dp[i + 1][j + 1]);
                // 1-2) 온도를 유지하는 경우 (b원 소비)
                dp[i + 1][j] = Math.min(dp[i][j] + b, dp[i + 1][j]);

                // 2) 에어컨의 전원을 끈 경우 (off) (0원 소비)
                // 2-1) 온도를 1도 높이거나 낮추는 경우
                if(j > 0 && j > temperature) dp[i + 1][j - 1] = Math.min(dp[i][j], dp[i + 1][j - 1]); // 현재 온도 > 실외 온도
                else if(j < temperature && j < 50) dp[i + 1][j + 1] = Math.min(dp[i][j], dp[i + 1][j + 1]); // 현재 온도 < 실외 온도
                // 2-2) 온도를 유지하는 경우
                else dp[i + 1][j] = Math.min(dp[i][j], dp[i + 1][j]);
            }
        }

        int answer = maxPower;
        for(int j = 0; j < 51; j++) {
            if (onboard[onboard.length - 1] == 1 && (j < t1 || j > t2)) continue;
            answer = Math.min(answer, dp[onboard.length - 1][j]);
        }

        return answer;
    }
}