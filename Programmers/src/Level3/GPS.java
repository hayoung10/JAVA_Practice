package Level3;

// DP 동적계획법

public class GPS { // GPS
    int MAX = 99999;

    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        int answer = 0;

        int[][] graph = new int[n + 1][n + 1]; // 이동 가능한 경로
        for(int i = 0; i < edge_list.length; i++) {
            graph[edge_list[i][0]][edge_list[i][1]] = 1;
            graph[edge_list[i][1]][edge_list[i][0]] = 1;
        }

        int[][] dp = new int[k][n + 1]; // 초기화
        for(int i = 0; i < k; i++)
            for(int j = 0; j < n + 1; j++)
                dp[i][j] = MAX;

        dp[0][gps_log[0]] = 0; // 첫 거점으로 초기화
        for(int p = 1; p < k; p++) {
            for(int q = 1; q < n + 1; q++) {
                dp[p][q] = Math.min(dp[p][q], dp[p - 1][q]); // 이동하지 않을 경우

                for(int r = 0; r < n + 1; r++)
                    if(graph[q][r] == 1) // 이동 가능할 경우
                        dp[p][q] = Math.min(dp[p][q], dp[p - 1][r]);

                if(q != gps_log[p]) // 오류 -> 수정 필요
                    dp[p][q]++;
            }
        }

        if(dp[k - 1][gps_log[k - 1]] < MAX)
            return dp[k - 1][gps_log[k - 1]];

        return -1;
    }
}
