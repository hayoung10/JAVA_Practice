package Gold3;

import java.io.*;
import java.util.*;

public class G1112 { // 진법 변환
    static StringBuilder ans = new StringBuilder();
    static boolean flag = false; // 음수를 양의 진법으로 바꿀 때

    private static void calculate(int x, int b) {
        int quot = x / b; // 몫
        int remain = x % b; // 나머지
        if(remain < 0) {
            quot++;
            remain = x - b * quot;
        }
        ans.append(remain);

        if(quot == 0) {
            if(flag) ans.append("-");
            return;
        }
        calculate(quot, b);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        if(input[0] < 0 && input[1] > 0) {
            flag = true;
            input[0] *= -1;
        }
        calculate(input[0], input[1]); // calculate(x, b)
        System.out.println(ans.reverse());
    }
}