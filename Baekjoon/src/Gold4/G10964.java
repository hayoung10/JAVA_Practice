package Gold4;

import java.io.*;
import java.util.*;

public class G10964 { // Calvinball championship, again 4
    static int[] ans; // 최종 팀 구성
    static int[] color; // Graph Coloring 이용
    static List<Integer>[] dislikes;
    static int n, tSize = Integer.MAX_VALUE;

    private static void dfs(int player, int cnt) {
        if(player > n) { // 마지막 사람까지 모두 팀을 나눈 경우
            if(tSize > cnt) { // 이전에 구성한 것보다 팀 수가 더 적은 경우
                tSize = cnt;
                for(int i = 1; i <= n; i++) ans[i] = color[i];
            }
            return;
        }
        if(cnt >= tSize) return;

        for(int i = 1; i <= cnt + 1; i++) {
            boolean flag = false; // 싫어하는 사람이 팀에 있는지 여부
            for(int before : dislikes[player]) {
                if(color[before] == i) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                color[player] = i; // i팀에 합류
                dfs(player + 1, Math.max(cnt, i));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        dislikes = new List[n + 1];
        for(int i = 0; i <= n; i++) dislikes[i] = new ArrayList<>();
        ans = new int[n + 1];
        color = new int[n + 1];
        int m = Integer.parseInt(st.nextToken());
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(a > b) dislikes[a].add(b);
            else dislikes[b].add(a);
        }

        // 팀 나누기
        dfs(1, 0);

        // 결과 출력
        System.out.println(tSize);
        for(int i = 1; i <= tSize; i++) {
            for(int j = 1; j <= n; j++)
                if(ans[j] == i) System.out.print(j + " ");
            System.out.println();
        }
    }
}