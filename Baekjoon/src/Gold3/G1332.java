package Gold3;

import java.io.*;
import java.util.*;

public class G1332 { // 풀자
    static int N, V;
    static int[] interest;

    private static int solution() { // Brute-Force 이용
        int min = interest[0];
        int max = interest[0];

        int ret = Integer.MAX_VALUE;
        for(int i = 1; i < N; i++) {
            if(interest[i] < min) min = interest[i];
            else if(interest[i] > max) max = interest[i];

            if(max - min >= V) {
                for(int j = 0; j < i; j++)
                    if(Math.abs(interest[i] - interest[j]) >= V)
                        ret = Math.min(ret, (j + 1) / 2 + 1 + (i - j) + 1 / 2);
                return ret;
            }
        }
        return N;
    }

    public static void main(String[] args) throws IOException {
        // 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());
        interest = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) interest[i] = Integer.parseInt(st.nextToken());

        System.out.println(solution());
    }
}