package Gold3;

import java.io.*;
import java.util.*;

public class G1301 { // 비즈 공예
    static int N;
    static int[] arr = new int[6];
    // dp[pp][p][a][b][c][d][e]
    // : 이전에 택한 구슬 p, p 이전에 택한 구슬 pp, 구슬이 각각 a,b,c,d,e개 남았을 때, 목걸이를 만들 수 있는 경우의 수
    static Long[][][][][][][] dp = new Long[7][7][11][11][11][11][11];

    private static Long solution(int pp, int p, int a, int b, int c, int d, int e) {
        if(a == 0 && b == 0 && c == 0 && d == 0 && e == 0) return 1L;
        Long ret = dp[pp][p][a][b][c][d][e];
        if(ret != null) return ret;
        ret = 0L;
        if(a > 0 && pp != 0 && p != 0) ret += solution(p, 0, a - 1, b, c, d, e);
        if(b > 0 && pp != 1 && p != 1) ret += solution(p, 1, a, b - 1, c, d, e);
        if(c > 0 && pp != 2 && p != 2) ret += solution(p, 2, a, b, c - 1, d, e);
        if(d > 0 && pp != 3 && p != 3) ret += solution(p, 3, a, b, c, d - 1, e);
        if(e > 0 && pp != 4 && p != 4) ret += solution(p, 4, a, b, c, d, e - 1);
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for(int i = 0; i < N; i++) arr[i] = Integer.parseInt(br.readLine());
        System.out.println(solution(6, 5, arr[0], arr[1], arr[2], arr[3], arr[4]));
    }
}