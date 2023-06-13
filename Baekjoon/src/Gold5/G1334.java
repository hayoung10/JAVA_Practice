package Gold5;

import java.io.*;
import java.util.*;

public class G1334 { // 다음 팰린드롬 수
     public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String N = br.readLine(); // 주어진 수 N
        int[] numbers = Arrays.stream(N.split("")).mapToInt(Integer::parseInt).toArray();
        int len = numbers.length;

        boolean allNine = true; // 해당 수가 모두 9로 이루어져 있는지 확인
        for(int i = 0; i < len; i++) {
            if(numbers[i] != 9) {
                allNine = false;
                break;
            }
        }

        if(allNine) {
            String answer = "1";
            for(int i = 0; i < len - 1; i++) answer += "0";
            answer += "1";
            System.out.println(answer);
        } else {
            int[] tmpNumbers = Arrays.copyOf(numbers, len);
            for(int i = len - 1; i >= len / 2; i--)
                tmpNumbers[i] = tmpNumbers[len - i - 1];

            boolean check = true; // 만든 팰린드롬 수가 N보다 작거나 같은지 확인
            for(int i = 0; i < len; i++) {
                if(tmpNumbers[i] != numbers[i]) {
                    check = tmpNumbers[i] > numbers[i];
                    break;
                }
            }

            if(!check) {
                for(int i = len / 2; i < len; i++) {
                    if(tmpNumbers[i] > numbers[i] || tmpNumbers[len - i - 1] > numbers[len - i - 1]) break;
                    if(tmpNumbers[i] == 9) {
                        tmpNumbers[i] = 0;
                        if(len % 2 == 0 || i != len / 2) tmpNumbers[len - i - 1] = 0;
                        continue;
                    }
                    if(tmpNumbers[i] <= numbers[i]) {
                        if(i != len - i - 1) tmpNumbers[len - i - 1]++;
                        tmpNumbers[i]++; i--;
                    }
                }
            }
            System.out.println(Arrays.toString(tmpNumbers).replaceAll("[^0-9]", ""));
        }
    }
}