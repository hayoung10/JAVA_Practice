package Gold5;

import java.io.*;
import java.util.*;

public class G10963 { // Calvinball championship, again 3
    static List<List<Integer>> teams = new ArrayList<>(); // 모든 팀
    static List<List<Integer>> dislikes = new ArrayList<>();

    private static void divideTeam(int player) { // 팀 나누기
        for(List<Integer> team : teams) {
            boolean flag = true;
            for(int i = 0; i < dislikes.get(player).size(); i++) {
                if(team.contains(dislikes.get(player).get(i))) {
                    flag = false;
                    break;
                }
            }
            if(flag) {
                team.add(player);
                return;
            }
        }
        List<Integer> newTeam = new ArrayList<>();
        newTeam.add(player);
        teams.add(newTeam);
    }

    public static void main(String[] args) throws IOException {
        // 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        for(int i = 0; i <= n; i++) dislikes.add(new ArrayList<>());
        int m = Integer.parseInt(st.nextToken());
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            dislikes.get(a).add(b);
            dislikes.get(b).add(a);
        }

        // 팀 나누기
        for(int i = 1; i <= n; i++) divideTeam(i);

        // 결과 출력
        System.out.println(teams.size());
        for(List<Integer> team : teams)
            System.out.println(team.toString().replace("[", "").replace("]", "").replace(", ", " "));
    }
}
