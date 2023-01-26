package Level3;

// Graph 그래프
// Floyd Warshall Algorithm

public class Rank { // 순위
    public int solution(int n, int[][] results) {
        int answer = 0;

        // use Floyd Warshall Algorithm
        boolean[][] check = new boolean[n][n];
        for(int i = 0; i < results.length; i++)
            check[results[i][0] - 1][results[i][1] - 1] = true; // win -> true
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                for(int k = 0; k < n; k++)
                    if(check[j][i] && check[i][k])
                        check[j][k] = true;

        for(int i = 0; i < n; i++) {
            int cnt = 0;
            for(int j = 0; j < n; j++)
                if(check[i][j] || check[j][i])
                    cnt++;
            if(cnt == n - 1) // 정확한 순위를 매길 수 있는 선수일 경우
                answer++;
        }

        return answer;
    }
}
