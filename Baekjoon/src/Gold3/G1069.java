package Gold3;

import java.io.*;
import java.util.*;

public class G1069 { // 집으로
    public static void main(String[] args) throws IOException {
        // 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int X = Integer.parseInt(st.nextToken()); int Y = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken()); int T = Integer.parseInt(st.nextToken());

        double dist = Math.sqrt(X * X + Y * Y);
        double answer = dist; // case 1 : 걸어가는 경우
        if(T < D) { // 점프하는 것이 걷는 것보다 빠른 경우
            int N = (int) dist / D; // 점프 횟수

            if(N == 0) {
                double case2 = 2 * T; // 두 번 점프하여 도착하는 경우
                double case3 = T + (D - dist); // 한 번 점프하고 걸어서 되돌아오는 경우
                answer = Math.min(answer, Math.min(case2, case3));
            } else {
                double case2 = N * T + (dist - N * D); // N번 점프하고 남은 거리를 걸어가는 경우
                double case3 = (N + 1) * T; // N+1번 점프하는 경우
                answer = Math.min(answer, Math.min(case2, case3));
            }
        }
        System.out.println(answer);
    }
}