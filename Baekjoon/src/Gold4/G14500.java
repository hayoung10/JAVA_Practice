package Gold4;

import java.io.*;
import java.util.*;

public class G14500 { // 테트로미노
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int answer = 0;
    
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    private static void dfs(int r, int c, int cnt, int sum) {
        if(cnt == 4) { // 테트로미노 완성 시 최댓값 업데이트
            answer = Math.max(answer, sum);
            return;
        }
        
        for(int i = 0; i < 4; i++) { // 상하좌우 탐색
            int nx = r + dx[i];
            int ny = c + dy[i];
            
            if(nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny]) continue;
            
            if(cnt == 2) { // ㅗ모양의 테트로미노를 위해 2번째 칸에서 탐색을 한 번 더 진행
                visited[nx][ny] = true;
                dfs(r, c, cnt + 1, sum + map[nx][ny]);
                visited[nx][ny] = false;
            }
            
            visited[nx][ny] = true;
            dfs(nx, ny, cnt + 1, sum + map[nx][ny]);
            visited[nx][ny] = false;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // 초기화
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        
        for(int i = 0; i < N; i++)
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // dfs
        for(int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                dfs(i, j, 1, map[i][j]);
                visited[i][j] = false;
            }
        }

        System.out.println(answer);
    }
}
