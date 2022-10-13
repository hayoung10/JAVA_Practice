package algorithm.sort;

public class insertionSort {

    public int[] insertion_sort(int[] arr) {
        for(int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int idx = i - 1;
            for(; idx >= 0; idx--) {
                if(key >= arr[idx]) break;

                arr[idx + 1] = arr[idx];
            }
            arr[idx + 1] = key;
        }

        return arr;
    }
}
