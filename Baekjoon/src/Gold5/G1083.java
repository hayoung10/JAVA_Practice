package Gold5;

import java.io.*;
import java.util.*;

public class G1083 { // 소트
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 초기화
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(br.readLine());
        
        // Greedy Algorithm 이용
        for(int i = 0; i < N - 1 && S > 0; i++) {
            int maxNum = arr[i];
            int idx = i;
            for(int j = i + 1; j < N && j - i <= S; j++) {
                if(maxNum < arr[j]) {
                    maxNum = arr[j];
                    idx = j;
                }
            }

            if(idx == i) continue;

            // swap
            for(int j = idx; j > i; j--) {
                arr[j] = arr[j - 1];
            }
            arr[i] = maxNum;
            S -= (idx - i);
        }
        
        for(int i = 0; i < N; i++)
            System.out.print(arr[i] + " ");
    }
}