package Gold5;

import java.io.*;
import java.util.*;

public class G2470 { // 두 용액
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 초기화
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());
        
        Arrays.sort(arr); // 오름차순으로 정렬

        int left = 0;
        int right = N - 1;
        int answer1 = 0, answer2 = 0;
        int minSum = 2000000000; // 두 용액을 혼합한 특성값의 최댓값
        while(left < right) {
            int sum = arr[left] + arr[right];

            if(minSum > Math.abs(sum)) {
                minSum = Math.abs(sum);
                answer1 = arr[left];
                answer2 = arr[right];

                if(sum == 0) break; // 특성값의 최솟값
            }

            if(sum < 0) left++;
            else right--;
        }

        System.out.println(answer1 + " " + answer2);
    }
}
