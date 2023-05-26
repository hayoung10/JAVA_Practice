package Gold5;

import java.io.*;

public class G1662 { // 압축
    static String S;
    //static boolean[] visited = new boolean[50];

    private static int func1(int idx) {
        int cnt = 0;

        for(int i = idx; i < S.length(); i++) {
            //if(visited[i]) continue;

            //visited[i] = true;
            if(S.charAt(i) == '(') {
                int num = S.charAt(i - 1) - '0';
                cnt += num * func1(i + 1) - 1;
            } else if(S.charAt(i) == ')') {
                return cnt;
            } else {
                cnt++;
            }
        }

        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();

        System.out.println(func1(0));
    }
}
