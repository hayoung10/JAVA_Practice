package Gold5;

import java.io.*;
import java.util.*;

public class G1025 { // 제곱수 찾기
    private static boolean isSquare(int num) { // 완전 제곱수인지 판별
        int root = (int)Math.sqrt(num);
        if(root * root == num) return true;
        return false;
    }

    private static int solution(int N, int M, int[][] table) {
        int answer = -1;

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                for(int dx = -N; dx < N; dx++) {
                    for(int dy = -M; dy < M; dy++) {
                        if(dx == 0 && dy == 0) continue; // 움직이지 않은 경우

                        int x = i;
                        int y = j;
                        int cur = 0;
                        while(0 <= x && x < N && 0 <= y && y < M) {
                            cur = cur * 10 + table[x][y];
                            if(isSquare(cur))
                                answer = Math.max(answer, cur);
                            x += dx;
                            y += dy;
                        }
                    }
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 초기화
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] table = new int[N][M];
        
        for(int i = 0; i < N; i++)
            table[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        
        System.out.println(solution(N, M, table));
    }
}
