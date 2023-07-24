package Gold3;

import java.io.*;
import java.util.*;

public class G1941 { // 소문난 칠공주
    static char[][] students = new char[5][5];
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[] loc = new int[7];
    static boolean[] visited;
    static int answer = 0;

    private static void combination(int idx, int cnt, int sCnt) { // 조합으로 7명 선택
        if(cnt - sCnt > 3) return; // '임도연파'가 우위에 있는 경우

        if(cnt == 7) {
            bfs();
            return;
        }

        for(int i = idx; i < 25; i++) {
            loc[cnt] = i;
            combination(i + 1, cnt + 1, students[i / 5][i % 5] == 'S' ? sCnt + 1 : sCnt);
        }
    }

    private static void bfs() { // 7명의 자리가 서로 인접해 있는지 확인
        Queue<Integer> q = new LinkedList<>();
        visited = new boolean[7];

        q.add(loc[0]);
        visited[0] = true;
        int cnt = 1;

        while(!q.isEmpty()) {
            int cur = q.remove();
            int r = cur / 5, c = cur % 5;

            for(int i = 0; i < 4; i++) {
                int nr = r + dr[i], nc = c + dc[i];
                if(nr < 0 || nr >= 5 || nc < 0 || nc >= 5) continue;

                int nxt = nr * 5 + nc;
                for(int k = 1; k < 7; k++) {
                    if(!visited[k] && loc[k] == nxt) {
                        visited[k] = true;
                        q.add(loc[k]);
                        cnt++;
                    }
                }
            }
        }
        if(cnt == 7) answer++;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i = 0; i < 5; i++) students[i] = br.readLine().toCharArray();

        combination(0, 0, 0);
        System.out.println(answer);
    }
}