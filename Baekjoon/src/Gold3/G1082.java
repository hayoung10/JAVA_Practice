package Gold3;

import java.io.*;
import java.util.*;

public class G1082 { // 방 번호
    public static void main(String[] args) throws IOException {
        // 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] price = new int[N];
        int minIdx = 0, minPrice = 51;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            price[i] = Integer.parseInt(st.nextToken());
            if(i > 0 && minPrice > price[i]) { // 0을 제외하고 가장 싼 숫자 찾기
                minIdx = i; minPrice = price[i];
            }
        }
        int M = Integer.parseInt(br.readLine());

        if(minPrice > M) { // 0을 제외하고 가장 싼 숫자를 살 수 없다면
            System.out.println(0);
            return;
        }

        int[] answer = new int[51];
        answer[0] = minIdx; // 0을 제외하고 가장 싼 숫자가 첫 자리로 와야 함
        int idx = 1, cost = minPrice;
        if(price[0] < minPrice) minIdx = 0;
        while(cost + price[minIdx] <= M) { // 자릿수를 최대한 길게 만들기
            answer[idx++] = minIdx;
            cost += price[minIdx];
        }

        for(int i = 0; i < idx; i++) {
            for(int j = N - 1; j >= 0; j--) {
                if(cost - price[answer[i]] + price[j] <= M) { // 해당 자릿수의 숫자보다 더 큰 숫자로 바꾸기
                    cost = cost - price[answer[i]] + price[j];
                    answer[i] = j;
                    break;
                }
            }
        }

        for(int i = 0; i < idx; i++) System.out.print(answer[i]);
    }
}