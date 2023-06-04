package Gold3;

import java.io.*;
import java.util.*;

public class G1099 { // 알 수 없는 문장
    private static boolean check(String word, String tmp) { // tmp 단어로 word 단어를 만들 수 있는지 확인
        if(word.length() != tmp.length()) return false;

        int[] alpha = new int[26];
        for(int i = 0; i < word.length(); i++) {
            alpha[word.charAt(i) - 'a']++;
            alpha[tmp.charAt(i) - 'a']--;
        }
        for(int i = 0; i < 26; i++)
            if(alpha[i] != 0) return false;

        return true;
    }

    private static int calCost(String word, String tmp) { // 비용 측정
        int cost = 0;
        for(int i = 0; i < word.length(); i++)
            if(word.charAt(i) != tmp.charAt(i)) cost++;

        return cost;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 초기화
        String sentence = br.readLine();
        int N = Integer.parseInt(br.readLine());
        String[] word = new String[N];
        for(int i = 0; i < N; i++)
            word[i] = br.readLine();

        int[] dp = new int[sentence.length() + 1];
        Arrays.fill(dp, 987654321);
        dp[0] = 0;

        for(int i = 1; i < sentence.length() + 1; i++) {
            for(int j = 0; j < i; j++) {
                String tmp = sentence.substring(j, i);
                for(int k = 0; k < N; k++) {
                    if(check(word[k], tmp))
                        dp[i] = Math.min(dp[i], dp[j] + calCost(word[k], tmp));
                }
            }
        }

        int answer = dp[sentence.length()] == 987654321 ? -1 : dp[sentence.length()];
        System.out.println(answer);
    }
}