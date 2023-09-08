package Gold3;

import java.io.*;
import java.util.*;

public class G1493 { // 박스 채우기
    static int N;
    static int[] cube;
    static boolean flag = true; // 큐브를 이용해서 박스를 채울 수 있는지 여부
    static int answer = 0;

    private static void divide(int l, int w, int h) { // 분할 정복 이용
        if(l == 0 || w == 0 || h == 0) return; // 모두 채운 경우

        flag = false;
        int size = 0; // 박스를 채울 큐브 중 가장 큰 한 변의 길이
        for(int i = N - 1; i >= 0; i--) {
            if(cube[i] == 0) continue;

            size = 1 << i; // 큐브의 한 변의 길이 : 2^i
            if(size > l || size > w || size > h) continue;

            cube[i]--;
            answer++;
            flag = true;
            break;
        }

        // 채울 수 없는 경우
        if(!flag) return;

        divide(size, w - size, size);
        divide(l - size, w, size);
        divide(l, w, h - size);
    }

    public static void main(String[] args) throws IOException {
        // 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int length = Integer.parseInt(st.nextToken()), width = Integer.parseInt(st.nextToken()), height = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(br.readLine());
        cube = new int[N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            cube[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
        }

        divide(length, width, height);
        System.out.println(flag ? answer : -1);
    }
}