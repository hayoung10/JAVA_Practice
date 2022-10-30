package algorithm.sort;

import java.util.PriorityQueue;

public class heapSort {

    // 우선순위 큐를 이용한 힙 정렬
    public int[] pqHeapSort(int[] arr) {
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>();
        for(Integer num : arr) heap.add(num); // 배열 값을 힙에 할당하여 배열을 힙으로 만든다

        // 힙에서 우선순위가 가장 높은 원소(root 노드)를 하나씩 배열에 삽입
        for(int i = 0; i < arr.length; i++)
            arr[i] = heap.remove();

        return arr;
    }
}
