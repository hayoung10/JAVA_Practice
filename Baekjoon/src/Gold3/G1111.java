package Gold3;

import java.io.*;
import java.util.*;

public class G1111 { // IQ Test
    private static String solution(int N, int[] num) {
        if(N == 1) return "A"; // 다음 수가 여러 개일 경우
        if(N == 2) {
            if(num[0] != num[1]) return "A"; // 다음 수가 여러 개일 경우
            else return Integer.toString(num[0]);
        }

        int a = 0;
        if(num[0] != num[1])
            a = (num[1] - num[2]) / (num[0] - num[1]);
        int b = num[1] - num[0] * a;
        for(int i = 2; i < N; i++)
            if(num[i] != num[i - 1] * a + b)
                return "B"; // 다음 수를 구할 수 없는 경우

        return Integer.toString(num[N - 1] * a + b);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] num = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(solution(N, num));
    }
}
