package Gold4;

import java.io.*;
import java.util.*;

public class G1034 { // 램프
    public static void main(String[] args) throws IOException {
        // 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
        String[] lamp = new String[N];
        for(int i = 0; i < N; i++) lamp[i] = br.readLine();
        int K = Integer.parseInt(br.readLine());

        int answer = 0;
        for(int i = 0; i < N; i++) {
            int offCnt = 0; // 해당 행에서 꺼져있는 램프의 수
            for(int j = 0; j < M; j++)
                if(lamp[i].charAt(j) == '0') offCnt++;

            // 해당 행을 켜져있는 행으로 만들었을 때
            int cnt = 0; // 켜져있는 행의 수
            if(offCnt <= K && offCnt % 2 == K % 2) {
                for(int a = 0; a < N; a++)
                    if(lamp[i].equals(lamp[a])) cnt++;
            }
            answer = Math.max(answer, cnt);
        }
        System.out.println(answer);
    }
}