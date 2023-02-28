package Level3;

// DP 동적계획법

public class Collect_Stickers_2 { // 스티커 모으기(2)
    public int solution(int sticker[]) {
        int answer = 0;

        int[] dp1 = new int[sticker.length]; // 첫 번째 스티커를 선택했을 경우
        int[] dp2 = new int[sticker.length]; // 첫 번째 스티커를 선택하지 않을 경우

        if(sticker.length == 1) return sticker[0];

        dp1[0] = dp1[1] = sticker[0];
        dp2[0] = 0; dp2[1] = sticker[1];
        for(int i = 2; i < sticker.length; i++) {
            dp2[i] = Math.max(dp2[i - 1], dp2[i - 2] + sticker[i]);
            if(i == sticker.length - 1) continue;
            dp1[i] = Math.max(dp1[i - 1], dp1[i - 2] + sticker[i]);
        }
        answer = Math.max(dp1[sticker.length - 2], dp2[sticker.length - 1]);

        return answer;
    }
}
