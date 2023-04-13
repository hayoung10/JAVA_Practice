package Level3;

// DFS 깊이 우선 탐색

public class Maze_Escape_Command { // 미로 탈출 명령어
    String[] command = {"d", "l", "r", "u"}; // 알파벳 순
    int[] dx = {1, 0, 0, -1}; // d -> l -> r -> u
    int[] dy = {0, -1, 1, 0}; // d -> l -> r -> u
    int n, m, r, c;


    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        this.n = n;
        this.m = m;
        this.r = r;
        this.c = c;

        int dist = Math.abs((x - 1) - (r - 1)) + Math.abs((y - 1) - (c - 1)); // 남은 거리

        String answer = DFS(x - 1, y - 1, "", k, dist);

        return answer;
    }

    private String DFS(int x, int y, String str, int k, int dist) {
        if(k == 0 && dist == 0) return str;

        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 0 || ny < 0 || nx >= n || ny >= m || dist > k || k % 2 != dist % 2) continue;

            int dist2 = Math.abs(nx - (r - 1)) + Math.abs(ny - (c - 1));
            String str2 = DFS(nx, ny, str + command[i], k - 1, dist2);
            if(!str2.equals("impossible"))
                return str2;
        }
        return "impossible";
    }
}
