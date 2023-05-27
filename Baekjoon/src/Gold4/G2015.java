package Gold4;

import java.io.*;
import java.util.*;

public class G2015 { // 수들의 합 4
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] sum = new int[N + 1]; // sum[i] : 1~i까지의 합

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++)
            sum[i] = sum[i - 1] + Integer.parseInt(st.nextToken());

        long answer = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 1; i <= N; i++) {
            if(sum[i] == K) answer++;

            answer += map.getOrDefault(sum[i] - K, 0);

            map.put(sum[i], map.getOrDefault(sum[i], 0) + 1);
        }

        System.out.println(answer);
    }
}
