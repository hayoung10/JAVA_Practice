package algorithm.sort;

public class selectionSort {

    // SWAP 함수
    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public void selectionSort(int[] arr) {
        for(int i = 0; i < arr.length - 1; i++) {
            int min_idx = i; // i번째 값 선택

            for(int j = i + 1; j < arr.length; j++) { // 최소값을 탐색
                if(arr[j] < arr[min_idx]) // 선택한 값보다 순회하고 있는 값이 작다면
                    min_idx = j; // 인덱스 갱신
            }

            swap(arr, min_idx, i); // swap
        }
    }
}
