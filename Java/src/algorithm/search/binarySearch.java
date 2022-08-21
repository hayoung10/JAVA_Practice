package algorithm.search;

public class binarySearch {
    // 이진 탐색이란? 정렬되어 있는 배열에서 특정 값(target)을 찾아내는 알고리즘
    // arr : 정렬된 배열, target : 찾고자 하는 특정 값
    // low : 탐색 범위의 시작 인덱스, high : 탐색 범위의 마지막 인덱스
    // mid : 탐색 범위의 중간 인덱스

    // 반복문을 이용한 이진 탐색 메서드
    public int binarySearch_iteration(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        int mid;

        while(low <= high) {
            mid = (low + high) / 2;
            if(arr[mid] == target) return mid; // target을 찾은 경우 인덱스 반환
            else if(arr[mid] > target) high = mid - 1;
            else low = mid + 1;
        }

        return -1; // target이 배열에 없는 경우
    }

    // 재귀함수를 이용한 이진 탐색 메서드
    public int binarySearch_recursion(int[] arr, int target, int low, int high) {
        if(low > high) return -1; // target이 배열에 없는 경우

        int mid = (low + high) / 2;

        if(arr[mid] == target) return mid; // target을 찾은 경우 인덱스 반환
        else if(arr[mid] > target) return binarySearch_recursion(arr, target, low, mid - 1);
        else return binarySearch_recursion(arr, target, mid + 1, high);
    }
}
