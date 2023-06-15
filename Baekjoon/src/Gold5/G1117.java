package Gold5;

import java.io.*;
import java.util.*;

public class G1117 { // 색칠 1
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long W = Long.parseLong(st.nextToken());
        long H = Long.parseLong(st.nextToken());
        long f = Long.parseLong(st.nextToken());
        long c = Long.parseLong(st.nextToken());
        long x1 = Long.parseLong(st.nextToken());
        long y1 = Long.parseLong(st.nextToken());
        long x2 = Long.parseLong(st.nextToken());
        long y2 = Long.parseLong(st.nextToken());

        long answer = W * H; // 전체 넓이
        long rangeX = f > W / 2 ? W - f : f; // 접힌 영역의 x 범위

        // 직사각형이 x=f에 맞춰 접은 종이와 겹치는 범위에 따른 계산
        if(rangeX <= x1) {
            answer -= (x2 - x1) * (y2 - y1) * (c + 1);
        } else if(x2 <= rangeX) {
            answer -= (x2 - x1) * (y2 - y1) * (c + 1) * 2;
        } else {
            answer -= (rangeX - x1) * (y2 - y1) * (c + 1) * 2;
            answer -= (x2 - rangeX) * (y2 - y1) * (c + 1);
        }

        System.out.println(answer);
    }
}
