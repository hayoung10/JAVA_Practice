package Level4;

import java.util.*;

// DP 동적계획법, DFS 깊이 우선 탐색 - bottom-up

public class Minimize_Sales_decline { // 매출 하락 최소화
    int[][] DP = new int[300001][2]; // DP[i][0] : 직원번호 i가 불참석, DP[i][1] : 직원번호 i가 참석
    int[] sales;
    List<Integer>[] tree; // 회사 조직도

    public int solution(int[] sales, int[][] links) {
        // 초기화
        this.sales = sales;
        tree = new ArrayList[sales.length];
        for(int i = 0; i < sales.length; i++)
            tree[i] = new ArrayList<>();
        for(int[] link : links)
            tree[link[0] - 1].add(link[1] - 1);

        DFS(0);

        return Math.min(DP[0][0], DP[0][1]);
    }

    private void DFS(int idx) {
        DP[idx][0] = 0;
        DP[idx][1] = sales[idx];

        if(tree[idx].isEmpty()) return;

        int tmp = Integer.MAX_VALUE;
        for(int child : tree[idx]) {
            DFS(child);
            if(DP[child][0] < DP[child][1]) { // 팀장이 참석하고 팀원이 불참석한 경우
                DP[idx][0] += DP[child][0];
                DP[idx][1] += DP[child][0];
                tmp = Math.min(tmp, DP[child][1] - DP[child][0]);
            } else { // 팀장이 참석하고 팀원도 참석한 경우
                DP[idx][0] += DP[child][1];
                DP[idx][1] += DP[child][1];
                tmp = 0;
            }
        }

        DP[idx][0] += tmp; // 팀장 idx가 참석하지 않고 팀원이 참석했을 경우의 최적해
    }
}
