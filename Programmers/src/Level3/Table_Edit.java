package Level3;

import java.util.*;

public class Table_Edit { // 표 편집
    public String solution(int n, int k, String[] cmd) {
        Stack<Integer> deleteNum = new Stack<>();
        int size = n;

        for(int i = 0; i < cmd.length; i++) {
            switch(cmd[i].charAt(0)) {
                case 'U':
                    k -= Integer.parseInt(cmd[i].substring(2));
                    break;
                case 'D':
                    k += Integer.parseInt(cmd[i].substring(2));
                    break;
                case 'C': // 현재 선택된 행 삭제
                    deleteNum.add(k);
                    size--;
                    if(k + 1 > size) k--; // 마지막 행일 경우
                    break;
                case 'Z':
                    int num = deleteNum.pop();
                    if(num <= k) k++; // 복구하는 행이 현재보다 이전 행일 때
                    size++;
                    break;
            }
        }

        // 표 상태 비교
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i < size; i++) sb.append('O');

        while(!deleteNum.isEmpty()) {
            int num = deleteNum.pop();
            sb.insert(num, 'X');
        }

        return sb.toString();
    }
}
