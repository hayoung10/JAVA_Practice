package Gold3;

import java.io.*;

public class G1248 { // Guess
    static int n;
    static int[] sequence;
    static char[][] matrix; // sign matrix

    private static boolean check(int idx) { // sign matrix 조건에 부합하는지 확인
        int sum = 0;

        for(int i = idx; i >= 0; i--) {
            sum += sequence[i];

            if(matrix[i][idx] == '+' && sum <= 0) return false;
            if(matrix[i][idx] == '-' && sum >= 0) return false;
            if(matrix[i][idx] == '0' && sum != 0) return false;
        }
        return true;
    }

    private static boolean solution(int idx) { // Backtracking 이용
        if(idx == n) return true;

        for(int i = -10; i <= 10; i++) {
            sequence[idx] = i;
            if(check(idx)) if(solution(idx + 1)) return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        sequence = new int[n];
        matrix = new char[n][n];
        String s = br.readLine();
        int idx = 0;
        for(int i = 0; i < n; i++)
            for(int j = i; j < n; j++)
                matrix[i][j] = s.charAt(idx++);

        solution(0);

        for(int i = 0; i < n; i++) System.out.print(sequence[i] + " ");
    }
}