package Gold3;

import java.io.*;
import java.util.*;

public class G1414 { // 불우이웃돕기
    static int N;
    static String[] lan;
    static int[] parent;

    private static int findParent(int a) {
        if(a == parent[a]) return a;
        return parent[a] = findParent(parent[a]);
    }

    private static void unionParent(int a, int b) {
        int p_a = findParent(a);
        int p_b = findParent(b);

        if(p_a != p_b) parent[p_a] = p_b;
    }

    private static int Kruskal() { // Kruskal 알고리즘 이용
        parent = new int[N + 1];
        for(int i = 1; i <= N; i++) parent[i] = i;

        // MST(Minimum Spanning Tree)
        int total = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                int len = lan[i].charAt(j) - 0;
                if(len == 48) continue;

                if(97 <= len) len -= 96;
                else len -= 38;
                total += len;
                pq.add(new int[]{i + 1, j + 1, len});
            }
        }

        int cnt = 0;
        int usedLan = 0;
        while(!pq.isEmpty()) {
            if(cnt == N - 1) break;

            int[] cur = pq.remove();
            if(findParent(cur[0]) != findParent(cur[1])) { // 컴퓨터 cur[0]과 컴퓨터 cur[1]를 연결
                cnt++;
                usedLan += cur[2];
                unionParent(cur[0], cur[1]);
            }
        }

        return cnt < N - 1 ? -1 : total - usedLan;
    }

    public static void main(String[] args) throws IOException {
        // 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        lan = new String[N];
        for(int i = 0; i < N; i++) lan[i] = br.readLine();

        System.out.println(Kruskal());
    }
}