package Level4;

import java.util.*;

public class Largest_Triangular_Group { // 가장 큰 삼각형 덩어리
    // |\: state 0, shape 0
    // |/: state 0, shape 1
    // /|: state 1, shape 2
    // \|: state 1, shape 3

    int N, M;
    int[][][] group; // 해당 삼각형이 속한 덩어리 번호
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    int[][] dir = {{2, 1}, {0, 2}, {1, 3}, {0, 3}}; // 삼각형마다 이동 가능한 방향 (위:0,아래:1,왼:2,오:3)
    int[][] verState = {{1, 0}, {0, 1}, {1, 0}, {0, 1}}; // 위아래 칸에서 연결되는 삼각형 찾기

    public int solution(int[][] grid) {
        // 초기화
        N = grid.length;
        M = grid[0].length;
        group = new int[N][M][2];

        int answer = 0;
        int groupNum = 1;

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                for(int k = 0; k < 2; k++) {
                    if(group[i][j][k] == 0) {
                        answer = Math.max(answer, bfs(i, j, k, groupNum, grid));
                        groupNum++;
                    }
                }
            }
        }

        return answer;
    }

    private int bfs(int x, int y, int state, int num, int[][] grid) {
        Queue<Triangle> q = new LinkedList<>();
        q.add(new Triangle(x, y, state));
        group[x][y][state] = num;

        int size = 0; // 해당 덩어리에 속한 삼각형의 개수
        while(!q.isEmpty()) {
            Triangle cur = q.remove();
            size++;

            int shape;
            if (grid[cur.x][cur.y] == -1) shape = cur.state == 0 ? 0 : 3;
            else shape = cur.state == 0 ? 1 : 2;

            for (int i = 0; i < 2; i++) {
                int ndir = dir[shape][i];
                int nx = cur.x + dx[ndir];
                int ny = cur.y + dy[ndir];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if (group[nx][ny][0] == num || group[nx][ny][1] == num) continue; // 이미 방문한 경우

                int nState;
                if (ndir == 0 || ndir == 1) nState = verState[shape][grid[nx][ny] == -1 ? 0 : 1];
                else nState = ndir == 2 ? 1 : 0;

                group[nx][ny][nState] = num;
                q.add(new Triangle(nx, ny, nState));
            }
        }

        return size;
    }

    private class Triangle {
        public int x;
        public int y;
        public int state;

        public Triangle(int x, int y, int state) {
            this.x = x;
            this.y = y;
            this.state = state;
        }
    }
}
