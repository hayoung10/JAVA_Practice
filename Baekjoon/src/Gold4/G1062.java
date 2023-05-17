package Gold4;

import java.io.*;
import java.util.*;

public class G1062 { // 가르침
    static int N, K, answer = 0;
    static String[] words = new String[50];
    static boolean[] visited = new boolean[26];

    private static void bfs(int alpha, int cnt) {
        if(cnt == K - 5) {
            int tmp = 0;
            for(int i = 0; i < N; i++) {
                boolean check = true; // 읽을 수 있는 단어인지 확인
                for(int j = 4; j < words[i].length() - 3; j++) { // 단어의 시작 부분 "anta"와 끝 부분 "tica"를 제외한 글자 확인
                    if(!visited[words[i].charAt(j) - 'a']) {
                        check = false;
                        break;
                    }
                }
                if(check) tmp++;
            }
            answer = Math.max(answer, tmp);
            return;
        }

        for(int i = alpha + 1; i < 26; i++) {
            if(!visited[i]) {
                visited[i] = true;
                bfs(i, cnt + 1);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for(int i = 0; i < N; i++)
            words[i] = br.readLine();

        if(K < 5) {
            System.out.println(0);
        } else if(K == 26) {
            System.out.println(N);
        } else {
            visited[0] = true; // a
            visited['c' - 'a'] = true; // c
            visited['i' - 'a'] = true; // i
            visited['n' - 'a'] = true; // n
            visited['t' - 'a'] = true; // t

            bfs(0, 0);
            System.out.println(answer);
        }
    }
}
