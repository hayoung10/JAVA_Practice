package Gold4;

import java.io.*;
import java.util.*;

public class G1339 { // 단어 수학
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[26];
        for(int i = 0; i < N; i++) {
            String str = br.readLine();
            for(int j = 0; j < str.length(); j++) {
                arr[str.charAt(j) - 'A'] += (int) Math.pow(10, str.length() - j - 1); // 해당 알파벳에 대한 가중치 부여
            }
        }

        Arrays.sort(arr);

        int answer = 0;
        int num = 9;
        for(int i = 25; i >= 0; i--) { // 가중치가 높은 순으로 값이 높은 숫자로 바꿔 계산
            if(arr[i] == 0) break;

            answer += arr[i] * num;
            num--;
        }
        System.out.println(answer);
    }
}
