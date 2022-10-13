package algorithm.sort;

public class bubbleSort {

    // 시간 복잡도 O(n^2)

    // 오름차순으로 정렬하는 버블정렬 메소드
    public int[] bubble_sort(int[] arr) {
        int cnt = 0;

        for(int i = arr.length - 1; i > 0; i++) {
            cnt = 0;
            for(int j = 0; j < i; j++) {
                if(arr[j] > arr[j+1]) {
                    swap(arr, j, j + 1);
                    cnt++;
                }
            }

            // finish (더 이상의 교환 연산이 필요 없는 경우 = 정렬 완료)
            if(cnt == 0) break;
        }

        return arr;
    }
    
    public void swap(int[] arr, int idx1, int idx2) {
        int tmp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = tmp;
    }
}
