package Gold4;

import java.io.*;
import java.util.*;

public class G1565 { // 수학
    private static int getGCD(int num1, int num2) { // 최대공약수 구하기
        if(num1 % num2 == 0) return num2;
        return getGCD(num2, num1 % num2);
    }

    private static int getLCM(int num1, int num2) { // 최소공배수 구하기
        return num1 / getGCD(num1, num2) * num2;
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

    private static int solution(int lcmD, int gcdM) { // 배열 D의 최소공배수의 배수이면서 배열 M의 최대공약수의 약수인 개수 구하기
        List<Integer> list = getDivisorList(gcdM);
        int cnt = 0;
        for(int i = 0; i < list.size(); i++)
            if(list.get(i) % lcmD == 0) cnt++;
        return cnt;
    }

    public static void main(String[] args) throws IOException {
        // 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int dLen = Integer.parseInt(st.nextToken());
        int mLen = Integer.parseInt(st.nextToken());

        // 배열 D의 최소공배수 구하기
        st = new StringTokenizer(br.readLine());
        int lcmD = Integer.parseInt(st.nextToken());
        for(int i = 1; i < dLen; i++) lcmD = getLCM(lcmD, Integer.parseInt(st.nextToken()));

        // 배열 M의 최대공약수 구하기
        st = new StringTokenizer(br.readLine());
        int gcdM = Integer.parseInt(st.nextToken());
        for(int i = 1; i < mLen; i++) gcdM = getGCD(gcdM, Integer.parseInt(st.nextToken()));

        System.out.println(solution(lcmD, gcdM));
    }
}