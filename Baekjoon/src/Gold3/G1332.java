package Gold3;

import java.io.*;
import java.util.*;

public class G1332 { // 풀자
    static int N, V;
    static int[] interest;
    static int ans = Integer.MAX_VALUE;

    private static void solution(int idx, int minV, int maxV, int cnt) {
        if(idx >= N) return;
        if(cnt >= ans) return;

        minV = Math.min(minV, interest[idx]);
        maxV = Math.max(maxV, interest[idx]);

        if(maxV - minV >= V) {
            ans = Math.min(ans, cnt);
            return;
        }

        solution(idx + 1, minV, maxV, cnt + 1);
        solution(idx + 2, minV, maxV, cnt + 1);
    }

    public static void main(String[] args) throws IOException {
        // 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());
        interest = new int[N];

        int maxI = Integer.MIN_VALUE, minI = Integer.MAX_VALUE;
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            interest[i] = Integer.parseInt(st.nextToken());
            maxI = Math.max(maxI, interest[i]);
            minI = Math.min(minI, interest[i]);
        }

        if(maxI - minI < V) ans = N;
        else solution(0, 1000, -1000, 1);
        System.out.println(ans);
    }
}