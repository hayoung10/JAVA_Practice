package Level3;

// DFS 너비 우선 탐색

public class Moving_the_Cart { // 수레 움직이기
    int n, m;
    int[][] board = new int[4][4];
    int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {0, 0}};

    boolean[][][] visited = new boolean[4][4][2];
    int answer = Integer.MAX_VALUE;

    public int solution(int[][] maze) { // 0 빈칸, 5 벽 // 1(빨간수레) -> 3 // 2(파란수레) -> 4
        // 초기화
        n = maze.length;
        m = maze[0].length;
        board = maze;

        Point redCart = null, blueCart = null; // 빨간색 수레와 파란색 수레의 위치
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(maze[i][j] == 1) {
                    redCart = new Point(i, j); // 빨간색 수레의 시작 칸
                    visited[i][j][0] = true;
                } else if(maze[i][j] == 2) {
                    blueCart = new Point(i, j); // 파란색 수레의 시작 칸
                    visited[i][j][1] = true;
                }
            }
        }

        DFS(redCart, blueCart, 0);

        return answer == Integer.MAX_VALUE ? 0 : answer;
    }

    private void DFS(Point red, Point blue, int turn) {
        if(checkToArrived(red, blue)) {
            answer = Math.min(answer, turn);
            return;
        }

        for(int i = 0; i < 5; i++) {
            Point nRed = new Point(red.x + dir[i][0], red.y + dir[i][1]); // 다음 빨간색 수레

            for(int j = 0; j < 5; j++) {
                if(i == 4 && j == 4) break; // 수레 모두 움직이지 않은 경우

                Point nBlue = new Point(blue.x + dir[j][0], blue.y + dir[j][1]); // 다음 파란색 수레

                if (!checkTheRules(red, nRed, blue, nBlue)) continue; // 수레를 움직일 때의 규칙 확인하기
                visited[nRed.x][nRed.y][0] = true;
                visited[nBlue.x][nBlue.y][1] = true;

                DFS(nRed, nBlue, turn + 1);

                // 방문 기록 초기화
                visited[nRed.x][nRed.y][0] = false;
                visited[nBlue.x][nBlue.y][1] = false;
            }
        }
    }

    private boolean checkToArrived(Point red, Point blue) { // 빨간색 수레와 파란색 수레 모두 각각의 도착칸에 도착했는지 확인
        return board[red.x][red.y] == 3 && board[blue.x][blue.y] == 4;
    }

    private boolean checkTheRules(Point red, Point nRed, Point blue, Point nBlue) { // 수레를 움직일 때의 규칙 확인
        // 수레는 벽이나 격자 판 밖으로 움직일 수 없음
        if(nRed.x < 0 || nRed.x >= n || nRed.y < 0 || nRed.y >= m
                || nBlue.x < 0 || nBlue.x >= n || nBlue.y < 0 || nBlue.y >= m
                || board[nRed.x][nRed.y] == 5 || board[nBlue.x][nBlue.y] == 5) return false;

        // 수레는 자신이 방문했던 칸으로 움직일 수 없음
        // 자신의 도착 칸에 위치한 수레는 움직이지 않음. 계속 해당 칸에 고정해 놓아야 함.
        if((board[nRed.x][nRed.y] != 3 && visited[nRed.x][nRed.y][0])
                || (board[nBlue.x][nBlue.y] != 4 && visited[nBlue.x][nBlue.y][1])) return false;

        // 동시에 두 수레를 같은 칸으로 움직일 수 없음
        if(nRed.x == nBlue.x && nRed.y == nBlue.y) return false;

        // 수레끼리 자리를 바꾸며 움직일 수 없음
        if((nRed.x == blue.x && nRed.y == blue.y) && (nBlue.x == red.x && nBlue.y == red.y)) return false;

        return true;
    }

    class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}