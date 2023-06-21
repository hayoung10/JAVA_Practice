package Gold4;

import java.io.*;
import java.util.*;

public class G1896 { // 파급효과
    static int R, C;
    static int[][] table;
    static int[][] direction;
    static int[] dx = {-1, 0, 1, 0}; // up, right, down, left
    static int[] dy = {0, 1, 0, -1};

    static int[][] connInfo = {
            {0}, {1}, {0, 1}, {2}, {0, 2},
            {1, 2}, {0, 1, 2}, {3}, {0, 3}, {1, 3},
            {0, 1, 3}, {2, 3}, {0, 2, 3}, {1, 2, 3}, {0, 1, 2, 3}
    }; // 연결된 정보

    static boolean[][] visited; // 검사 여부
    static HashSet<Integer> numbers; // 폴리노미오

    private static boolean rule1() { //  첫 번째 규칙 검사
        visited = new boolean[R][C];

        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(visited[i][j] || direction[i][j] == 0) continue;

                numbers = new HashSet<>();
                if(!bfs(i, j)) return false;
            }
        }

        return true;
    }

    private static boolean bfs(int x, int y) { // 같은 폴리노미오 안의 숫자 검사
        visited[x][y] = true;
        if(numbers.contains(table[x][y])) return false;

        numbers.add(table[x][y]);
        for(int k : connInfo[direction[x][y] - 1]) {
            int nx = x + dx[k];
            int ny = y + dy[k];

            if(visited[nx][ny]) continue;
            if(!bfs(nx, ny)) return false;
        }

        return true;
    }

    private static boolean rule2() { // 두 번째 규칙 검사
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                int num = table[i][j];
                int rangeR = i + num;
                int rangeC = j + num;

                if(i + 1 < R && rangeR < R) {
                    for(int a = i + 1; a <= rangeR; a++)
                        if(table[a][j] == num) return false;
                }
                if(j + 1 < C && rangeC < C) {
                    for(int b = j + 1; b <= rangeC; b++)
                        if(table[i][b] == num) return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int i = 0; i < T; i++) {
            // 초기화
            st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            table = new int[R][C];
            direction = new int[R][C];
            for(int j = 0 ; j < R; j++)
                table[j] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
            for(int j = 0; j < R; j++)
                direction[j] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            // 규칙 검사
            if(rule1() && rule2()) System.out.println("valid");
            else System.out.println("invalid");
        }
    }
}