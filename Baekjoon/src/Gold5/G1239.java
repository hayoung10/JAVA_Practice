package Gold5;

import java.io.*;
import java.util.*;

public class G1239 { // 차트
    static int[] chart;
    static boolean[] visited;
    static int N, answer = 0;
    static int[] arr;

    private static void check() { // 원의 중심을 지나는 선이 있는지 확인
        int cnt = 0;
        System.arraycopy(arr, 0, arr, N, N); // 원형 차트는 처음과 끝이 없기 때문에 배열을 연장하여 검사

        for(int i = 0; i < N; i++) {
            int tmp = 0;
            for(int j = i; j < 2 * N; j++) {
                tmp += arr[j];
                if(tmp == 50) { // 원의 중심을 지나는 선이 있는 경우
                    cnt++;
                    break;
                } else if(tmp > 50) {
                    break;
                }
            }
        }
        answer = Math.max(answer, cnt);
    }

    private static void permutation(int depth) { // 순열
        if(depth == N) {
            check();
            return;
        }

        for(int i = 0; i < N; i++) {
            if(!visited[i]) {
                visited[i] = true;
                arr[depth] = chart[i];
                permutation(depth + 1);
                visited[i] = false;
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        // 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        chart = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        visited = new boolean[N];
        arr = new int[2 * N];

        Arrays.sort(chart);
        if(chart[N - 1] > 50) { // 원의 중심을 지나는 선이 없는 경우
            System.out.println(0);
        } else if(chart[N - 1] == 50) { // 원의 중심을 지나는 선이 한 개인 경우
            System.out.println(1);
        } else {
            permutation(0); // 순열을 이용하여 전체 탐색 (Brute-Force)
            System.out.println(answer / 2);
        }
    }
}