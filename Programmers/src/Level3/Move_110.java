package Level3;

import java.util.*;

public class Move_110 { // 110 옮기기
    public String[] solution(String[] s) {
        String[] answer = new String[s.length];

        for(int i = 0; i < s.length; i++) {
            // s[i]에서 110 뽑기
            int count = 0;
            Stack<Character> stack = new Stack<>();
            for(int j = 0; j < s[i].length(); j++) {
                char c = s[i].charAt(j);

                if(stack.size() >= 2) {
                    char b = stack.pop();
                    char a = stack.pop();

                    if(a == '1' && b == '1' && c == '0') {
                        count++;
                    } else {
                        stack.push(a); stack.push(b); stack.push(c);
                    }
                } else {
                    stack.push(c);
                }
            }

            // 뽑았던 110을 삽입할 위치 구하기
            StringBuilder sb = new StringBuilder();
            int idx = stack.size(); // 삽입할 위치
            boolean flag = false;
            while(!stack.isEmpty()) {
                char c = stack.pop();
                sb.insert(0, c);

                if(!flag && c == '1') idx--;
                if(!flag && c == '0') flag = true;
            }

            while(count > 0) { // 110 삽입
                count--;
                sb.insert(idx, "110");
            }
            answer[i] = sb.toString();
        }

        return answer;
    }
}
