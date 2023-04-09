package Level3;

public class Pop_the_Balloon { // 풍선 터트리기
    public int solution(int[] a) {
        int answer = 0;

        if (a.length == 1) return 1;

        int[] leftMinValue = new int[a.length]; // 자신의 왼쪽 부분의 최솟값
        int[] rightMinValue = new int[a.length]; // 자신의 오른쪽 부분의 최솟값

        // 초기화
        int minValue = Integer.MAX_VALUE;
        for (int i = 0; i < a.length; i++) {
            minValue = Math.min(minValue, a[i]);
            leftMinValue[i] = minValue;
        }
        minValue = Integer.MAX_VALUE;
        for (int i = a.length - 1; i >= 0; i--) {
            minValue = Math.min(minValue, a[i]);
            rightMinValue[i] = minValue;
        }

        for (int i = 1; i < a.length - 1; i++)
            if (leftMinValue[i] >= a[i] || rightMinValue[i] >= a[i]) // 최후까지 남을 수 있는 경우
                answer++;

        return answer + 2;
    }
}