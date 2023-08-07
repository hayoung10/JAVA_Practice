package Gold4;

import java.io.*;
import java.util.*;

public class G1101 { // 카드 정리 1
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 박스 수
        int M = Integer.parseInt(st.nextToken()); // 카드 색상 수

        int[][] arr = new int[N][M];
        int[] rowCnt = new int[N]; // 각 박스가 가지고 있는 색상의 수
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] > 0) rowCnt[i]++;
            }
        }

        int answer = N;
        boolean[] visited;
        for(int k = 0; k < N; k++) { // k번째 상자가 조커 상자일 경우
            int cnt = 0;
            visited = new boolean[M];
            for(int i = 0; i < N; i++) {
                if(k == i) continue;
                else if(rowCnt[i] >= 2) cnt++; // 2가지 이상의 색상을 가지고 있으면 이동해야 함
                else if(rowCnt[i] == 0) continue;
                else if(rowCnt[i] == 1) { // 1가지 색상을 가지고 있는 경우
                    boolean flag = false; // 이전 상자들 중 해당 색상의 카드를 가진 상자가 존재하는지 여부
                    for(int j = 0; j < M; j++) {
                        if(arr[i][j] > 0 && !visited[j]) { // 해당 색상의 카드를 가진 상자가 이전에 없는 경우
                            visited[j] = true;
                            flag = true;
                            break;
                        }
                    }
                    if(!flag) cnt++; // 이전 상자에서 해당 색상의 카드를 가지고 있으므로 이동해야 함
                }
            }
            answer = Math.min(answer, cnt);
        }
        System.out.println(answer);
    }
}