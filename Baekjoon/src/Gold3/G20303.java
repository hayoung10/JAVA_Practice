package Gold3;

import java.io.*;
import java.util.*;

public class G20303 { // 할로윈의 양아치
    static int N, M, K;
    static int[] candy, parent;

    private static int findParent(int a) {
        if(parent[a] == a) return a;
        return parent[a] = findParent(parent[a]);
    }

    private static void unionParent(int a, int b) {
        int p_a = findParent(a);
        int p_b = findParent(b);
        if(p_a != p_b) parent[p_a] = p_b;
    }

    private static int solution() {
        // 서로 친구인 아이들끼리 그룹으로 나누기
        int[] grp_child = new int[N + 1]; // 각 그룹의 아이들 수
        int[] grp_candy = new int[N + 1]; // 각 그룹의 사탕 총합
        for(int i = 1; i <= N; i++) {
            int p = findParent(i);
            grp_child[p]++;
            grp_candy[p] += candy[i];
        }

        int[] dp = new int[K];
        for(int i = 1; i <= N; i++) {
            if(grp_child[i] == 0 && grp_candy[i] == 0) continue;
            for(int j = K - 1; j >= grp_child[i]; j--)
                dp[j] = Math.max(dp[j], dp[j - grp_child[i]] + grp_candy[i]);
        }
        return dp[K - 1];
    }

    public static void main(String[] args) throws IOException {
        // 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken()); K = Integer.parseInt(st.nextToken());
        parent = new int[N + 1]; for(int i = 1; i <= N; i++) parent[i] = i;
        st = new StringTokenizer(br.readLine());
        candy = new int[N + 1]; for(int i = 1; i <= N; i++) candy[i] = Integer.parseInt(st.nextToken());
        for(int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            unionParent(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        System.out.println(solution());
    }
}