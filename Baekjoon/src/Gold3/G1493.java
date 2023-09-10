package Gold3;

import java.io.*;
import java.util.*;

public class G1493 { // 박스 채우기
    public static void main(String[] args) throws IOException {
        // 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int length = Integer.parseInt(st.nextToken()), width = Integer.parseInt(st.nextToken()), height = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(br.readLine());
        int[] cube = new int[N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            cube[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
        }

        long before = 0; // 이전 턴에 사용했던 큐브의 개수
        int answer = 0;
        for (int i = N - 1; i >= 0; i--) {
            before <<= 3; // 이전 턴보다 1번 분할을 했으므로 (2 * 2 * 2)배 늘리기

            long cubeCnt = (long) (length >> i) * (width >> i) * (height >> i) - before; // i번째 큐브로 채울 때 필요한 큐브 개수
            cubeCnt = Math.min(cube[i], cubeCnt); // 가능한 큐브의 개수

            before += cubeCnt;
            answer += cubeCnt;
        }

        if (before == (long) length * width * height) System.out.println(answer);
        else System.out.println(-1); // 채울 수 없는 경우
    }
}