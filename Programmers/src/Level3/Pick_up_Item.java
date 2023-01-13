package Level3;

import java.util.*;

// BFS 너비 우선 탐색

public class Pick_up_Item { // 아이템 줍기
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int answer = 0;

        int[][] map = new int[103][103];
        boolean[][] visited = new boolean[103][103];

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        // 초기화
        for(int[] arr : rectangle) {
            for(int i = arr[0] * 2; i <= arr[2] * 2; i++) {
                for(int j = arr[1] * 2; j <= arr[3] * 2; j++) {
                    if(map[i][j] == -1) continue;
                    map[i][j] = -1;
                    if(i == arr[0] * 2 || i == arr[2] * 2 || j == arr[1] * 2 || j == arr[3] * 2)
                        map[i][j] = 1;
                }
            }
        }

        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(characterX * 2, characterY * 2));
        visited[characterX * 2][characterY * 2] = true;

        while(!q.isEmpty()) {
            Pair p = q.poll();

            if(p.x == itemX * 2 && p.y == itemY * 2) {
                answer = (answer == 0) ? map[p.x][p.y] : Math.min(answer, map[p.x][p.y]);
                continue;
            }

            for(int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if(nx < 0 || ny < 0 || nx > 100 || ny > 100) continue;
                if(visited[nx][ny] || map[nx][ny] <= 0) continue;

                map[nx][ny] = map[p.x][p.y] + 1;
                visited[nx][ny] = true;
                q.add(new Pair(nx, ny));
            }
        }

        return answer / 2;
    }
}

class Pair {
    int x, y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
