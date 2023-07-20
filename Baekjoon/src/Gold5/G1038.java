package Gold5;

import java.io.*;
import java.util.*;

public class G1038 { // 감소하는 수
    static List<Long> list = new ArrayList<>();

    private static void make_decNums(long num, int last) { // 감소하는 수 만들기
        list.add(num);
        for(int i = last - 1; i >= 0; i--)
            make_decNums(num * 10 + i, i);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        if(N <= 10) System.out.println(N);
        else if(N > 1022) System.out.println(-1);
        else {
            for(int i = 0; i < 10; i++)
                make_decNums(i, i);
            Collections.sort(list);

            System.out.println(list.get(N));
        }
    }
}