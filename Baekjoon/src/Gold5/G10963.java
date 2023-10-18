package Gold5;

import java.io.*;
import java.util.*;

public class G10963 { // Calvinball championship, again 3
    static List<List<Integer>> teams = new ArrayList<>(); // 최종 팀 구성
    static int[] tmp; // 임시 팀 구성
    static List<List<Integer>> dislikes = new ArrayList<>();
    static int n, tSize = Integer.MAX_VALUE;

    private static void dfs(int player, int cnt) {
        if(player > n) { // 마지막 사람까지 모두 팀을 나눈 경우
            if(tSize > cnt) { // 이전에 구성한 것보다 팀 수가 더 적은 경우
                tSize = cnt;
                teams = new ArrayList<>();
                for(int i = 0; i < tSize; i++) teams.add(new ArrayList<>());
                for(int i = 1; i <= n; i++) teams.get(tmp[i] - 1).add(i);
            }
            return;
        }
        if(cnt >= tSize) return;

        for(int i = 1; i <= cnt + 1; i++) {
            boolean flag = false; // 싫어하는 사람이 팀에 있는지 여부
            for(int before : dislikes.get(player)) {
                if(tmp[before] == i) {
                    flag = true;
                    break;
                }
            }
            if (flag) continue;

            tmp[player] = i; // i팀에 합류
            dfs(player + 1, Math.max(cnt, i));
        }
    }

    public static void main(String[] args) throws IOException {
        // 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        for(int i = 0; i <= n; i++) dislikes.add(new ArrayList<>());
        tmp = new int[n + 1];
        int m = Integer.parseInt(st.nextToken());
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(a > b) dislikes.get(a).add(b);
            else dislikes.get(b).add(a);
        }

        // 팀 나누기
        dfs(1, 0);

        // 결과 출력
        System.out.println(tSize);
        for(List<Integer> team : teams)
            System.out.println(team.toString().replace("[", "").replace("]", "").replace(", ", " "));
    }
}