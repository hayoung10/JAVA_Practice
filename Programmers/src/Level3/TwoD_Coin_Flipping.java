package Level3;

// DFS 깊이 우선 탐색

public class TwoD_Coin_Flipping { // 2차원 동전 뒤집기
    int n, m;
    int[][] board;
    int[][] t;
    int answer = Integer.MAX_VALUE;

    public int solution(int[][] beginning, int[][] target) {
        // 초기화
        n = beginning.length;
        m = beginning[0].length;

        board = new int[n][m];
        for(int i = 0; i < n; i++)
            board[i] = beginning[i].clone();
        t = target;

        dfs(0, 0);

        if(answer == Integer.MAX_VALUE) return -1;
        return answer;
    }

    private void dfs(int r, int cnt) {
        if(r == n) {
            boolean flag = true;
            for(int j = 0; j < m; j++) {
                int ret = compareCol(j);
                if(ret == -1) { // target과 동일하게 만들 수 없는 경우
                    flag = false;
                    break;
                }
                cnt += ret;
            }

            if(flag) {
                answer = Math.min(answer, cnt);
            }
            return;
        }

        flipRow(r);
        dfs(r + 1, cnt + 1); // 행을 뒤집는 경우
        flipRow(r);
        dfs(r + 1, cnt); // 행을 뒤집지 않는 경우
    }

    private int compareCol(int c) { // target의 열과 비교
        int cnt = 0;
        for(int i = 0; i < n; i++) {
            if(board[i][c] == t[i][c])
                cnt++;
        }

        if(cnt == n) return 0; // 해당 열이 전부 동일한 경우
        else if(cnt == 0) return 1; // 해당 열이 전부 반대인 경우
        else return -1;
    }

    private void flipRow(int r) { // 행 뒤집기
        for(int i = 0; i < m; i++) {
            board[r][i] = (board[r][i] + 1) % 2;
        }
    }
}
