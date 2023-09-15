package Gold3;

import java.io.*;

public class G16438 { // 원숭이 스포츠
    static StringBuilder[] tables = new StringBuilder[7];

    private static void divide(int order, int start, int end) {
        if(order == 7) return;

        int mid = (start + end) / 2;

        for(int i = start; i < mid; i++) tables[order].append('A');
        for(int i = mid; i < end; i++) tables[order].append('B');

        divide(order + 1, start, mid);
        divide(order + 1, mid, end);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for(int i = 0; i < 7; i++) tables[i] = new StringBuilder();
        divide(0, 0, N);

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) sb.append('B');
        String allB = sb.toString();
        sb.setCharAt(0, 'A');
        String oneA = sb.toString();

        for(int i = 0; i < 7; i++) {
            String str = tables[i].toString();
            if(str.equals(allB)) System.out.println(oneA); // 모든 원숭이가 같은 팀이면 안 됨
            else System.out.println(str);
        }
    }
}