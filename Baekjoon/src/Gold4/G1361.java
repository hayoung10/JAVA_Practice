package Gold4;

import java.io.*;

public class G1361 { // 두 스트링 마스크
    private static StringBuilder compare(String flStr, String fsStr, String blStr, String bsStr) {
        // 앞쪽 문자열 비교
        StringBuilder sb1 = new StringBuilder();
        if(!fsStr.equals(flStr.substring(0, fsStr.length()))) return new StringBuilder("-1");
        sb1.append(flStr);

        // 뒤쪽 문자열 비교
        int idx = blStr.length() - bsStr.length();
        if(!bsStr.equals(blStr.substring(idx))) return new StringBuilder("-1");

        int sb1Len = sb1.length();
        int str1Idx = -1;
        for(int i = 0; i < idx; i++) {
            if(sb1Len < i + 1) break;
            if(sb1.substring(sb1Len - i - 1).equals(blStr.substring(0, i + 1))) str1Idx = i;
        }

        return sb1.append(blStr, str1Idx + 1, idx).append(bsStr);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S1 = br.readLine();
        String S2 = br.readLine();

        // 문자열 S1과 S2를 '*'을 기준으로 각각 앞쪽 문자열과 뒤쪽 문자열로 분리
        int starIdx1 = S1.indexOf('*'), starIdx2 = S2.indexOf('*');
        String S1_1 = S1.substring(0, starIdx1), S1_2 = S1.substring(starIdx1 + 1);
        String S2_1 = S2.substring(0, starIdx2), S2_2 = S2.substring(starIdx2 + 1);

        String answer = compare(
                S1_1.length() >= S2_1.length() ? S1_1 : S2_1, // 앞쪽 문자열 중 긴 문자열
                S1_1.length() >= S2_1.length() ? S2_1 : S1_1, // 앞쪽 문자열 중 짧은 문자열
                S1_2.length() >= S2_2.length() ? S1_2 : S2_2, // 뒤쪽 문자열 중 긴 문자열
                S1_2.length() >= S2_2.length() ? S2_2 : S1_2) // 뒤쪽 문자열 중 짧은 문자열
                .toString();
        System.out.println(answer);
    }
}
