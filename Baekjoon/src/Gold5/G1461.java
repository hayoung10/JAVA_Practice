package Gold5;

import java.io.*;
import java.util.*;

public class G1461 { // 도서관
    public static void main(String[] args) throws IOException {
        // 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] book = new int[N];

        int idx = 0;
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            book[i] = Integer.parseInt(st.nextToken());
            if(book[i] < 0) idx++;
        }
        Arrays.sort(book);

        int answer = 0;
        for(int i = 0; i < idx; i += M)
            answer += Math.abs(book[i] * 2);
        for(int i = N - 1; i >= idx; i -= M)
            answer += Math.abs(book[i] * 2);
        answer -= Math.max(Math.abs(book[0]), Math.abs(book[N - 1])); // 왕복 거리 제외
        System.out.println(answer);
    }
}
