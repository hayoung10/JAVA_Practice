package Level4;

// DP 동적계획법

public class Word_Puzzle { // 단어 퍼즐
    public int solution(String[] strs, String t) {
        int[] dp = new int[t.length()];

        for(int i = 0; i < t.length(); i++) {
            dp[i] = t.length() + 1;

            for(int j = 0; j < strs.length; j++) {
                int lastIdx = strs[j].length() - 1;

                if(i < lastIdx) continue;

                if(strs[j].equals(t.substring(i - lastIdx, i + 1))) {
                    if(i == lastIdx)
                        dp[i] = 1;
                    else
                        dp[i] = Math.min(dp[i], dp[i - lastIdx - 1] + 1);
                }
            }
        }

        if(dp[t.length() - 1] >= t.length() + 1) return -1;

        return dp[t.length() - 1];
    }
}
