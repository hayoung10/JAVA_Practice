package Gold3;

import java.io.*;
import java.util.*;

public class G2143 { // 두 배열의 합
    static List<Integer> aSum, bSum;

    private static int lowerBound(int target) { // List에서 범위 내의 원소들 중 target 값보다 크거나 같은 첫 번째 원소의 index 리턴
        int low = 0;
        int high = bSum.size();
        int mid;

        while(low < high) {
            mid = (low + high) / 2;
            if(bSum.get(mid) < target) low = mid + 1;
            else high = mid;
        }
        return low;
    }

    private static int upperBound(int target) { // List에서 처음으로 target 값을 초과하는 원소의 index 리턴
        int low = 0;
        int high = bSum.size();
        int mid;

        while(low < high) {
            mid = (low + high) / 2;
            if(bSum.get(mid) <= target) low = mid + 1;
            else high = mid;
        }
        return low;
    }

    private static long binarySearch(int T) { // 이분 탐색 이용
        Collections.sort(bSum);
        long ret = 0;
        for(int i = 0; i < aSum.size(); i++) {
            int target = T - aSum.get(i);
            int lo = lowerBound(target);
            int hi = upperBound(target);
            ret += (hi - lo);
        }
        return ret;
    }

    private static long twoPointer(int T) { // 투 포인터 알고리즘 이용
        Collections.sort(aSum);
        Collections.sort(bSum);

        long ret = 0;
        int aIdx = 0;
        int bIdx = bSum.size() - 1;
        int aValue, bValue;

        while(aIdx < aSum.size() && bIdx >= 0) {
            aValue = aSum.get(aIdx);
            bValue = bSum.get(bIdx);
            long sum = aValue + bValue;

            if(sum == T) {
                long aCnt = 0;
                long bCnt = 0;
                while(aIdx < aSum.size() && aValue == aSum.get(aIdx)) {
                    aCnt++;
                    aIdx++;
                }
                while(bIdx >= 0 && bValue == bSum.get(bIdx)) {
                    bCnt++;
                    bIdx--;
                }

                ret += aCnt * bCnt;
            } else if(sum < T) {
                aIdx++;
            } else {
                bIdx--;
            }
        }

        return ret;
    }

    public static void main(String[] args) throws IOException {
        // 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());
        int[] A = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) A[i] = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(br.readLine());
        int[] B = new int[m];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < m; i++) B[i] = Integer.parseInt(st.nextToken());

        // 누적 합 구하기
        aSum = new ArrayList<>();
        bSum = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            int sum = A[i];
            aSum.add(sum);
            for(int j = i + 1; j < n; j++) {
                sum += A[j];
                aSum.add(sum);
            }
        }
        for(int i = 0; i < m; i++) {
            int sum = B[i];
            bSum.add(sum);
            for(int j = i + 1; j < m; j++) {
                sum += B[j];
                bSum.add(sum);
            }
        }

        // 모든 부 배열 쌍의 개수 구하기
//        System.out.println(binarySearch(T)); // 이분 탐색 이용
        System.out.println(twoPointer(T)); // 투 포인터 알고리즘 이용 (--> 해당 문제에서 더 효율적)
    }
}