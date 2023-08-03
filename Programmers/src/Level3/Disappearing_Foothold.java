package Level3;

public class Disappearing_Foothold { // 사라지는 발판
    int N, M;
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    boolean[][] visited = new boolean[5][5];
    int[][] map = new int[5][5];

    private int dfs(int[] loc1, int[] loc2) {
        if(visited[loc1[0]][loc1[1]]) return 0; // 발판이 사라진 경우

        int turn1 = 0; // 현 시점
        for(int i = 0; i < 4; i++) {
            int nx = loc1[0] + dx[i];
            int ny = loc1[1] + dy[i];

            if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
            if(visited[nx][ny] || map[nx][ny] == 0) continue;

            visited[loc1[0]][loc1[1]] = true;
            int turn2 = dfs(loc2, new int[]{nx, ny}) + 1; // 다음 차례는 상대 플레이어
            visited[loc1[0]][loc1[1]] = false;

            // 현 시점과 새로 계산한 시점이 모두 패배인 경우 -> 최대한 오래 버티는 경우를 선택
            if(turn1 % 2 == 0 && turn2 % 2 == 0) turn1 = Math.max(turn1, turn2);
            // 현 시점은 패배인데 새로 계산한 시점이 승리인 경우 -> 새로 계산한 시점을 선택
            else if(turn1 % 2 == 0 && turn2 % 2 == 1) turn1 = turn2;
            // 현 시점과 새로 계산된 시점이 모두 승리인 경우 -> 최대한 빨리 승리하는 경우를 선택
            else if(turn1 % 2 == 1 && turn2 % 2 == 1) turn1 = Math.min(turn1, turn2);
            // 현 시점이 승리인데 새로 계산한 시점이 패배인 경우 -> 현 시점을 선택 (turn1 = turn1)
        }
        return turn1;
    }

    public int solution(int[][] board, int[] aloc, int[] bloc) {
        N = board.length; M = board[0].length;
        for(int i = 0; i < N; i++)
            for(int j = 0; j < M; j++)
                map[i][j] = board[i][j];

        return dfs(aloc, bloc);
    }
}
