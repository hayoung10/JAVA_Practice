package Gold3;

import java.io.*;
import java.util.*;

public class G1566 { // P배열
    static int N, M;
    static int[][] matrix;
    static int[] rowSum; // 행에 있는 원소의 합
    static int[] colSum; // 열에 있는 원소의 합
    static int answer;
    static List<Integer> colSelect = new ArrayList<>(); // 선택한 행

    private static void change_signOfRow(int idx) { // 행의 부호를 바꿈
        rowSum[idx] *= -1;
        for(int j = 0; j < M; j++) {
            matrix[idx][j] *= -1;
            colSum[j] += matrix[idx][j] * 2;
        }
    }

    private static void change_signOfCol(int idx) { // 열의 부호를 바꿈
        colSum[idx] *= -1;
        for(int i = 0; i < N; i++) {
            matrix[i][idx] *= -1;
            rowSum[i] += matrix[i][idx] * 2;
        }
    }

    private static void rollBack() { // 열을 원상복구
        for(Integer i : colSelect) change_signOfCol(i);
    }

    private static void solution(int cnt, int select) {
        if(cnt == N) { // check
            colSelect.clear();
            for(int i = 0; i < M; i++) {
                if(colSum[i] == 0) { // P 배열이 될 수 없으므로 원상복구
                    rollBack(); return;
                } else if(colSum[i] < 0) {
                    change_signOfCol(i);
                    colSelect.add(i);
                }
            }
            for(int i = 0; i < N; i++) {
                if(rowSum[i] <= 0) {
                    rollBack(); return;
                }
            }
            answer = Math.min(answer, select + colSelect.size());
            rollBack(); // 원상복구
            return;
        }

        solution(cnt + 1, select); // 해당 행을 선택하지 않은 경우

        // 해당 행을 선택한 경우
        change_signOfRow(cnt);
        solution(cnt + 1, select + 1);
        change_signOfRow(cnt);
    }

    public static void main(String[] args) throws IOException {
        // 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());
        matrix = new int[N][M]; rowSum = new int[N]; colSum = new int[M];
        answer = (N + M) / 2 + 1; // 정답의 최댓값 : (N + M) / 2

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
                rowSum[i] += matrix[i][j];
                colSum[j] += matrix[i][j];
            }
        }
        solution(0, 0);

        if(answer == (N + M) / 2 + 1) System.out.println(-1); // 불가능한 경우
        else System.out.println(answer);
    }
}