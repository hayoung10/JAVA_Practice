package Gold4;

import java.io.*;
import java.util.*;

public class G1253 { // 좋다
    public static void main(String[] args) throws IOException {
        // 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(numbers);

        int answer = 0;
        for(int i = 0; i < N; i++) {
            int left = 0, right = N - 1;
            int sum = 0;
            while(left < right) { // 이분 탐색 이용
                sum = numbers[left] + numbers[right];
                if(sum == numbers[i]) {
                    if(i == left) {
                        left++;
                    } else if(i == right) {
                        right--;
                    } else {
                        answer++;
                        break;
                    }
                } else if(numbers[left] + numbers[right] < numbers[i]) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        System.out.println(answer);
    }
}