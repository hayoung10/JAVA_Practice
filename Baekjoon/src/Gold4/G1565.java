package Gold4;

import java.io.*;
import java.util.*;

public class G1565 { // 수학
    private static int getGCD(int num1, int num2) { // 최대공약수 구하기
        if(num1 % num2 == 0) return num2;
        return getGCD(num2, num1 % num2);
    }

    private static List<Integer> getDivisorList(int num) { // 약수 구하기
        List<Integer> list = new ArrayList<>();
        for(int i = 1; i * i <= num; i++) {
            if(num % i == 0) {
                list.add(i);
                if(i * i != num) list.add(num / i);
            }
        }
        return list;
    }

    private static int solution(int[] D, int gcdM) { // 배열 D의 배수이면서 배열 M의 최대공약수의 약수인 개수 구하기
        List<Integer> list = getDivisorList(gcdM);
        int cnt = 0;
        for(int i = 0; i < list.size(); i++) {
            for(int j = 0; j < D.length; j++) {
                if(list.get(i) % D[j] != 0) break;
                if(j == D.length - 1) cnt++;
            }
        }
        return cnt;
    }

    public static void main(String[] args) throws IOException {
        // 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] D = new int[Integer.parseInt(st.nextToken())];
        int[] M = new int[Integer.parseInt(st.nextToken())];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < D.length; i++) D[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M.length; i++) M[i] = Integer.parseInt(st.nextToken());

        // 배열 M의 최대공약수 구하기
        int gcdM = M[0];
        for(int i = 1; i < M.length; i++) gcdM = getGCD(gcdM, M[i]);

        System.out.println(solution(D, gcdM));
    }
}