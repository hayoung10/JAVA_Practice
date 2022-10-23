package algorithm.sort;

import java.util.Arrays;

public class mergeSort {

    private int[] sorted; // merge 과정에서 사용하는 임시 배열

    // left ~ right까지의 부분 배열을 병합하는 과정
    public void merge(int[] arr, int left, int mid, int right) {
        int i = left, j = mid + 1, k = left; // 왼쪽 부분 배열의 시작 인덱스, 오른쪽 부분 배열의 시작 인덱스, 채워넣을 배열의 인덱스
        
        while(i <= mid && j <= right) {
            if(arr[i] <= arr[j]) sorted[k++] = arr[i++];
            else sorted[k++] = arr[j++];
        }
        
        if(i > mid) { // 왼쪽 부분 배열이 먼저 채워졌을 경우
            for(; j <= right; j++)
                sorted[k++] = arr[j];
        } else { // 오른쪽 부분 배열이 먼저 채워졌을 경우
            for(; i <= mid; i++)
                sorted[k++] = arr[i];
        }
        
        // 정렬된 새 배열을 기존 배열에 복사
        for(int idx = left; idx <= right; idx++)
            arr[idx] = sorted[idx];
    }

    // Bottom-Up 방식으로 구현한 merge sort
    public void bottomUpMergeSort(int[] arr, int left, int right) {
        for(int size = 1; size <= right; size += size) { // size : 부분 배열의 크기
            for(int idx = 0; idx <= right - size; idx += (2 * size)) { // idx : 왼쪽 부분 배열의 인덱스
                merge(arr, idx, idx + size - 1, Math.min(idx + (2 * size) - 1, right)); // 병합
            }
        }
    }

    // Top-Down 방식으로 구현한 merge Sort
    public void topDownMergeSort(int[] arr, int left, int right) {
        if(left < right) {
            int mid = (left + right) / 2;
            topDownMergeSort(arr, left, mid); // 왼쪽 부분 배열
            topDownMergeSort(arr, mid + 1, right); // 오른쪽 부분 배열
            merge(arr, left, mid, right); // 병합
        }
    }

    public void mergeSort(int[] arr) {
        // Bottom-Up 방식
        int[] buarr = Arrays.copyOf(arr, arr.length);
        sorted = new int[arr.length];
        bottomUpMergeSort(buarr, 0, buarr.length - 1);

        // Top-Down 방식
        int[] tdarr = Arrays.copyOf(arr, arr.length);
        sorted = new int[arr.length];
        topDownMergeSort(tdarr, 0, tdarr.length - 1);
    }
}
