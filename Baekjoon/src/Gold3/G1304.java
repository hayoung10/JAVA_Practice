package Gold3;

import java.io.*;
import java.util.*;

public class G1304 { // 지역
    static int N, M;
    static int[] parent; // 루트 지역

    private static int findParent(int a) {
        if(a == parent[a]) return a;
        return parent[a] = findParent(parent[a]);
    }

    private static void unionParent(int a, int b) {
        int p_a = findParent(a);
        int p_b = findParent(b);

        if(p_a != p_b) parent[p_a] = p_b;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];
        for(int i = 1; i <= N; i++) parent[i] = i;
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            if(S > E) // E~S는 같은 지역이어야 함
                for(int j = E; j < S; j++)
                    unionParent(S, j);
        }

        int answer = 1;
        for(int i = 1; i < N; i++) {
            if(N % i != 0) continue;

            boolean flag = true; // i로 나눈 지역에 속한 도시들이 다른 루트 지역을 갖는지 확인
            for(int j = 1; j < N / i; j++) {
                int p1 = findParent(i * j);
                int p2 = findParent(i * j + 1);
                if(p1 == p2) { // 같은 루트 지역을 가지므로 다시(더 잘게) 나눠야 함
                    flag = false; break;
                }
            }
            if(flag) {
                answer = N / i; break;
            }
        }
        System.out.println(answer);
    }
}