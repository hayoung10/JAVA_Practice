package Level3;

import java.util.*;

public class Restore_the_Formula { // 수식 복원하기
    public String[] solution(String[] expressions) {
        List<String> done = new ArrayList<>(); // 완성된 수식
        List<String> unknown = new ArrayList<>(); // 결과값이 지워진 수식
        int base = 1; // 살펴볼 시작 밑수
        for(int i = 0; i < expressions.length; i++) {
            String[] exp = expressions[i].split(" ");

            // 최대 밑수 찾기
            for(int j = 0; j < exp[0].length(); j++) base = Math.max(base, Integer.parseInt(String.valueOf(exp[0].charAt(j))));
            for(int j = 0; j < exp[2].length(); j++) base = Math.max(base, Integer.parseInt(String.valueOf(exp[2].charAt(j))));

            if(!exp[4].equals("X")) {
                done.add(expressions[i]);
                for(int j = 0; j < exp[4].length(); j++) base = Math.max(base, Integer.parseInt(String.valueOf(exp[4].charAt(j))));
            } else {
                unknown.add(expressions[i]);
            }
        }

        // 완성된 수식 살펴보기
        List<Integer> bases = new ArrayList<>(); // 가능한 밑수들
        for(int i = base + 1; i < 10; i++) {
            boolean check = true; // 해당 밑수가 가능한지 여부

            for(String d : done) {
                String[] s = d.split(" ");
                int num1 = N_to_Ten(s[0], i);
                int num2 = N_to_Ten(s[2], i);
                int num3 = N_to_Ten(s[4], i);

                if(s[1].equals("+") && num1 + num2 != num3) {
                    check = false; break;
                } else if(s[1].equals("-") && num1 - num2 != num3) {
                    check = false; break;
                }
            }

            if(check) bases.add(i);
        }

        // 결과값이 지워진 수식 살펴보기
        String[] answer = new String[unknown.size()];
        for(int i = 0; i < unknown.size(); i++) {
            String[] s = unknown.get(i).split(" ");

            Set<String> set = new HashSet<>();
            for(Integer N : bases) {
                int num1 = N_to_Ten(s[0], N);
                int num2 = N_to_Ten(s[2], N);
                if(s[1].equals("+")) set.add(Ten_to_N(num1 + num2, N));
                else if(s[1].equals("-")) set.add(Ten_to_N(num1 - num2, N));
            }

            if(set.size() == 1) answer[i] = String.join(" ", s[0], s[1], s[2], s[3], set.iterator().next());
            else answer[i] = String.join(" ", s[0], s[1], s[2], s[3], "?"); // 결과값이 불확실한 수식일 경우
        }

        return answer;
    }

    private int N_to_Ten(String num, int base) { // N진수 -> 10진수
        return Integer.parseInt(num, base);
    }

    private String Ten_to_N(int num, int base) { // 10진수 -> N진수
        return Integer.toString(num, base);
    }
}