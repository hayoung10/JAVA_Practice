package Level3;

import java.util.*;

// BFS 너비 우선 탐색

public class Card_Matching { // 카드 짝 맞추기
    int[][] gboard = new int[4][4]; // game board
    int[][] dist = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    Integer[] card; // 뒤집을 카드
    int cur_r, cur_c; // 최초 cursor 위치

    public int solution(int[][] board, int r, int c) {
        int answer = Integer.MAX_VALUE;

        Set<Integer> card_check = new HashSet<>();
        for(int i = 0; i < 4; i++)
            for(int j = 0; j < 4; j++)
                if(board[i][j] > 0) card_check.add(board[i][j]);
        card = card_check.toArray(new Integer[0]);

        do {
            // gboard, cur_r, cur_c 초기화
            for(int i = 0; i < 4; i++)
                gboard[i] = board[i].clone();
            cur_r = r; cur_c = c;
            int tmp = 0;
            for(int num : card) { // a pair of two cards
                tmp += bfs(num);
                tmp += bfs(num);
            }
            answer = Math.min(answer, tmp);
        } while(nextPermutation());


        return answer;
    }

    private boolean nextPermutation() {
        int idx = card.length - 1;
        while(idx > 0 && card[idx - 1] >= card[idx]) idx--;

        if(idx == 0) return false; // 다음 순열이 존재하지 않는 경우

        int big_idx = card.length - 1;
        while(card[idx - 1] >= card[big_idx]) big_idx--;

        // swap
        int tmp = card[idx - 1];
        card[idx - 1] = card[big_idx];
        card[big_idx] = tmp;

        Arrays.sort(card, idx, card.length);
        return true;
    }

    private int bfs(int num) {
        boolean[][] visited = new boolean[4][4];
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(cur_r, cur_c, 0));
        visited[cur_r][cur_c] = true;

        while(!q.isEmpty()) {
            Point p = q.remove();

            if(gboard[p.x][p.y] == num) {
                gboard[p.x][p.y] = 0;
                cur_r = p.x; cur_c = p.y;
                return p.move + 1; // + Enter
            }
            for(int i = 0; i < 4; i++) { // ←, ↑, ↓, →
                int nx = p.x + dist[i][0];
                int ny = p.y + dist[i][1];
                if (nx < 0 || ny < 0 || nx > 3 || ny > 3) continue;

                if(!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    q.add(new Point(nx, ny, p.move + 1));
                }
            }
            for(int i = 0; i < 4; i++) { // [Ctrl] + ←, ↑, ↓, →
                int nx = p.x;
                int ny = p.y;
                while(0 <= nx + dist[i][0] && nx + dist[i][0] < 4 && 0 <= ny + dist[i][1] && ny + dist[i][1] < 4) {
                    nx += dist[i][0];
                    ny += dist[i][1];
                    if(gboard[nx][ny] > 0) break;
                }
                if(!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    q.add(new Point(nx, ny, p.move + 1));
                }
            }
        }

        return 0;
    }

    class Point {
        int x, y;
        int move;

        public Point(int x, int y, int move) {
            this.x = x;
            this.y = y;
            this.move = move;
        }
    }
}
