package Gold5;

import java.io.*;
import java.util.*;

public class G2170 { // 선 긋기
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] pos = new int[N][2];

        StringTokenizer st;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            pos[i][0] = Integer.parseInt(st.nextToken());
            pos[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(pos, new Comparator<int[]>() { // x좌표 오름차순, y좌표 오름차순
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0];
            }
        });

        int start = pos[0][0];
        int end = pos[0][1];
        int answer = end - start;
        for(int i = 1; i < N; i++) {
            if(start <= pos[i][0] && pos[i][1] <= end) // 현재 선이 이전 선에 포함되는 경우
                continue;
            else if(pos[i][0] <= end) // 현재 선이 이전 선과 겹치는 경우
                answer += pos[i][1] - end;
            else // 현재 선이 이전 선과 겹치지 않는 경우
                answer += pos[i][1] - pos[i][0];

            start = pos[i][0];
            end = pos[i][1];
        }
        System.out.println(answer);
    }
}