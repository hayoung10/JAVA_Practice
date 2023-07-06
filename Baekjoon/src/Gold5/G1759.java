package Gold5;

import java.io.*;
import java.util.*;

public class G1759 { // 암호 만들기
    static int L, C;
    static char[] alpha;
    static boolean[] visited;
    static char[] output;

    private static void check() { // 암호가 최소 한 개의 모음과 최소 두 개의 자음으로 구성되어 있는지 확인
        int cnt = 0;
        for(Character c : output)
            if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') cnt++;

        if(cnt >= 1 && C - cnt >= 2) System.out.println(String.valueOf(output));
    }

    private static void permutation(int idx, int depth) { // 정렬된 알파벳을 증가하는 순서로 암호 구성
        if(depth == L) {
            check();
            return;
        }

        for(int i = idx; i < C; i++) {
            if(!visited[i]) {
                visited[i] = true;
                output[depth] = alpha[i];
                permutation(i + 1, depth + 1);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        alpha = br.readLine().replace(" ", "").toCharArray();
        visited = new boolean[C];
        output = new char[L];

        Arrays.sort(alpha);
        permutation(0, 0);
    }
}