package Gold4;

import java.io.*;
import java.util.*;

public class G1091 { // 카드 섞기
    static int N;
    static int[][] P = new int[3][48]; // P[0][], P[1][] : 섞은 카드 배열, P[2][] : 원본 배열
    static int[] S = new int[48];

    private static boolean checkOrder(int cur) { // 카드의 순서가 맞는지 확인
        for(int i = 0; i < N; i++)
            if(P[cur][i] != i % 3) return false;
        return true;
    }

    private static void mixCard(int pre, int cur) { // 카드 섞기
        for(int i = 0; i < N; i++)
            P[cur][S[i]] = P[pre][i];
    }

    private static boolean checkCycle(int cur) { // 싸이클 여부 확인
        for(int i = 0; i < N; i++)
            if(P[cur][i] != P[2][i]) return false;
        return true; // 싸이클 발생
    }

    public static void main(String[] args) throws IOException {
        // 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) P[0][i] = P[2][i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) S[i] = Integer.parseInt(st.nextToken());
        
        int answer = 0;
        while(!checkOrder(answer % 2)) {
            answer++;
            mixCard((answer - 1) % 2, answer % 2);

            if(checkCycle(answer % 2)) {
                answer = -1; break;
            }
        }
        System.out.println(answer);
    }
}