package algorithm.sort;

import java.util.Arrays;

public class quickSort {

    // SWAP 함수
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // 부분배열의 가장 왼쪽 원소가 피벗이 되는 방식
    public static void left_pivot_sort(int[] arr, int low, int high) {
        if(low >= high) return;

        int pivot = left_partition(arr, low, high);
        left_pivot_sort(arr, low, pivot - 1);
        left_pivot_sort(arr, pivot + 1, high);
    }

    public static int left_partition(int[] arr, int low, int high) {
        int pivot = arr[low]; // 부분배열의 왼쪽 요소를 피벗으로 선택
        int left = low;

        while(low < high) {
            while(pivot < arr[high] && low < high) high--;
            while(arr[low] <= pivot && low < high) low++;

            swap(arr, low, high);
        }
        swap(arr, left, low);

        return low;
    }


    // 부분배열의 가운데 원소가 피벗이 되는 방식
    public static void middle_pivot_sort(int[] arr, int low, int high) {
        if(low >= high) return;

        int pivot = middle_partition(arr, low, high);
        middle_pivot_sort(arr, low, pivot - 1);
        middle_pivot_sort(arr, pivot, high);
    }

    public static int middle_partition(int[] arr, int low, int high) {
        int pivot = arr[(low + high) / 2]; // 부분배열의 가운데 요소를 피벗으로 선택

        while(low <= high) {
            while(arr[low] < pivot) low++;
            while(pivot < arr[high]) high--;
            if(low <= high) {
                swap(arr, low, high);
                low++; high--;
            }
        }
        return low;
    }


    // 부분배열의 가장 오른쪽 원소가 피벗이 되는 방식
    public static void right_pivot_sort(int[] arr, int low, int high) {
        if(low >= high) return;

        int pivot = right_partition(arr, low, high);
        right_pivot_sort(arr, low, pivot - 1);
        right_pivot_sort(arr, pivot + 1, high);
    }

    public static int right_partition(int[] arr, int low, int high) {
        int pivot = arr[high]; // 부분배열의 마지막 요소를 피벗으로 선택
        int right = high;

        while(low < high) {
            while(arr[low] < pivot && low < high) low++;
            while(pivot <= arr[high] && low < high) high--;

            swap(arr, low, high);
        }
        swap(arr, right, high);

        return high;
    }

    // 피벗을 선택한 방법에 따른 quick sort 결과를 보여주는 메소드
    public void sort(int[] arr) {
        // 왼쪽 피벗을 선택한 방법
        int[] larr = Arrays.copyOf(arr, arr.length);
        left_pivot_sort(larr, 0, larr.length - 1);
        System.out.println("왼쪽 피벗을 선택한 방식");
        System.out.println(Arrays.toString(larr));

        System.out.println();

        // 가운데 피벗을 선택한 방법법
        int[] marr = Arrays.copyOf(arr, arr.length);
        middle_pivot_sort(marr, 0, marr.length - 1);
        System.out.println("가운데 피벗을 선택한 방식");
        System.out.println(Arrays.toString(marr));

        System.out.println();

        // 오른 피벗을 선택한 방법
        int[] rarr = Arrays.copyOf(arr, arr.length);
        right_pivot_sort(rarr, 0, rarr.length - 1);
        System.out.println("오른쪽 피벗을 선택한 방식");
        System.out.println(Arrays.toString(rarr));
    }
}
