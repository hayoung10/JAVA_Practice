package Gold3;

import java.io.*;
import java.util.*;

public class G1112 { // 진법 변환
    static StringBuilder ans = new StringBuilder();

    private static void calculate(int x, int b) {
        int quot = x / b; // 몫
        int remain = x % b; // 나머지
        if(remain < 0) {
            quot++;
            remain = x - b * quot;
        }
        ans.append(remain);
        
        if(quot == 0) return;
        calculate(quot, b);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        calculate(input[0], input[1]); // calculate(x, b)
        System.out.println(ans.reverse());
    }
}