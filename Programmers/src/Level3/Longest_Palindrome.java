package Level3;

public class Longest_Palindrome { // 가장 긴 팰린드롬
    public int solution(String s)
    {
        int answer = 0;

        for(int i = 0; i < s.length(); i++) { // i : 팰린드롬을 비교할 기준
            int odd = isPalindrome(s, i, i); // 팰린드롬의 길이가 홀수일 경우
            int even = isPalindrome(s, i - 1, i); // 팰린드롬의 길이가 짝수일 경우
            answer = Math.max(answer, Math.max(odd, even));
        }

        return answer;
    }

    private int isPalindrome(String s, int left, int right) { // 팰린드롬인지 검사
        while(0 <= left && right < s.length()) {
            if(s.charAt(left) != s.charAt(right)) break;
            left--;
            right++;
        }
        return right - left - 1; // 팰린드롬의 길이
    }
}
