package Gold4;

import java.io.*;

public class G1437 { // 수 분해
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        int answer = 1;
        while(N >= 5) {
            answer = (3 * answer) % 10007;
            N -= 3;
        }
        System.out.println((answer * N) % 10007);
    }
}