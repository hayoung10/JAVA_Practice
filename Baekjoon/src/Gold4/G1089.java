package Gold4;

import java.io.*;

public class G1089 { // 스타트링크 파워
    static String[] numbers = {
            "####.##.##.####", // 0
            "..#..#..#..#..#", // 1
            "###..#####..###", // 2
            "###..####..####", // 3
            "#.##.####..#..#", // 4
            "####..###..####", // 5
            "####..####.####", // 6
            "###..#..#..#..#", // 7
            "####.#####.####", // 8
            "####.####..####" // 9
    };
    static String[] map = new String[5];
    static int[] digitCnt; // 해당 자릿수가 나오는 개수

    private static boolean checkDigit(int digit, int num) { // 해당 자릿 수에 해당 번호가 가능한지 여부 확인
        int loc = 4 * digit;
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 3; j++)
                if(map[i].charAt(loc + j) == '#' && numbers[num].charAt(i * 3 + j) == '.') return false;
        digitCnt[digit]++;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for(int i = 0; i < 5; i++) map[i] = br.readLine();

        boolean[][] check = new boolean[N][10]; // check[a][b] : a번 자리에 b 숫자가 가능한지 여부
        digitCnt = new int[N];
        int totalCnt = 1; // 나올 수 있는 총 숫자 개수
        for(int i = 0; i < N; i++) {
            for (int j = 0; j < 10; j++)
                check[i][j] = checkDigit(i, j);
            if(digitCnt[i] == 0) { // 가능한 층 번호가 없는 경우
                System.out.println(-1); return;
            }
            totalCnt *= digitCnt[i];
        }

        // 가능한 층 번호의 총합 구하기
        double answer = 0;
        for(int i = 0; i < N; i++)
            for(int j = 0; j < 10; j++)
                if(check[i][j])
                    answer += (Math.pow(10, N - i - 1) * j) * (totalCnt / digitCnt[i]);
        System.out.println(Math.round(answer / totalCnt * 100000) / 100000.0);
    }
}