package Gold5;

import java.io.*;
import java.util.*;

public class G1041 { // 주사위
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] num = new int[6];
        long minNum = 51, maxNum = 0, sum = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 6; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        long answer = 0;
        if(N == 1) { // 한 면을 제외한 5개의 면이 보임
            answer = sum - maxNum;
        } else {
            // 한 꼭짓점을 공유하는 세 면의 최솟값
            long minSum = 0;
            for(int i = 0; i < 3; i++)
                minSum += Math.min(num[i], num[5 - i]);
            answer += minSum * 4;

            // 한 모서리를 공유하는 두 면의 최솟값
            minSum = 102;
            for(int i = 0; i < 6; i++)
                for(int j = i + 1; j < 6; j++)
                    if(i + j != 5)
                        minSum = Math.min(minSum, num[i] + num[j]);
            answer += minSum * (long) ((N - 1) * 4 + (N - 2) * 4);

            // 한 면의 최솟값
            answer += minNum * ((long) (N - 2) * (N - 2) * 5 + (long)(N - 2) * 4);
        }
        System.out.println(answer);
    }
}