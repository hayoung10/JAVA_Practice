package algorithm.sort;

public class insertionSort {

    public int[] insertion_sort(int[] arr) {
        for(int i = 1; i < arr.length; i++) {
            int key = arr[i]; // 현재 삽입될 숫자를 key 변수로 복사

            int idx = i - 1;
            for(; idx >= 0; idx--) { // key 값이 비교 값보다 클 때까지 반복
                if(key >= arr[idx]) break;

                arr[idx + 1] = arr[idx]; // 원소를 한 칸 씩 뒤로(오른쪽으로) 미룸
            }

            arr[idx + 1] = key; // 알맞은 위치에 key 삽입
        }

        return arr;
    }
}
