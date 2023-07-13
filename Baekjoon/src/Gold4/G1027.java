package Gold4;

import java.io.*;
import java.util.*;

public class G1027 { // 고층 건물
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] buildings = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int answer = 0;
        for(int i = 0; i < N; i++) { // 빌딩의 두 지붕을 이은 기울기 이용
            double left_gradient = 1000000000;
            double right_gradient = -1000000000;
            int cnt = 0;
            for(int j = i - 1; j >= 0; j--) { // 왼쪽에서 볼 수 있는 빌딩 수 구하기
                double tmp_gradient = (double) (buildings[j] - buildings[i]) / (j - i);
                if(tmp_gradient < left_gradient) {
                    left_gradient = tmp_gradient;
                    cnt++;
                }
            }
            for(int j = i + 1; j < N; j++) { // 오른쪽에서 볼 수 있는 빌딩 수 구하기
                double tmp_gradient = (double) (buildings[j] - buildings[i]) / (j - i);
                if(tmp_gradient > right_gradient) {
                    right_gradient = tmp_gradient;
                    cnt++;
                }
            }
            answer = Math.max(answer, cnt);
        }
        System.out.println(answer);
    }
}