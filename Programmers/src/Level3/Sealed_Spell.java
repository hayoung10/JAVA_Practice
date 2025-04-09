package Level3;

import java.util.*;

public class Sealed_Spell { // 봉인된 주문
    public String solution(long n, String[] bans) {
        Arrays.sort(bans, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(o1.length() == o2.length()) return o1.compareTo(o2);
                return o1.length() - o2.length();
            }
        });

        int cnt = 0;
        for(String b : bans) {
            long num = toDecimal(b);
            if(n + cnt >= num) cnt++;
            else break; // 이미 오름차순인 경우
        }

        return toBase26(n + cnt);
    }

    private long toDecimal(String value) { // 26진수(문자열) -> 10진수(Long)
        long result = 0;

        for(int i = 0; i < value.length(); i++)
            result = result * 26 + (value.charAt(i) - 'a' + 1);

        return result;
    }

    private String toBase26(long num) { // 10진수(Long) -> 26진수(문자열)
        StringBuilder sb = new StringBuilder();

        while(num > 0) {
            num--;
            sb.append((char) ('a' + (num % 26)));
            num /= 26;
        }

        return sb.reverse().toString();
    }
}
