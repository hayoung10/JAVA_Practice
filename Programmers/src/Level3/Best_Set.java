package Level3;

public class Best_Set { // 최고의 집합
    public int[] solution(int n, int s) {
        int[] answer = new int[n];

        if(n > s) return new int[]{-1}; // 최고의 집합이 존재하지 않는 경우

        int quotient = s / n; // 몫
        int remainder = s % n; // 나머지

        for(int i = 0; i < n; i++)
            answer[i] = quotient;

        for(int i = 1; i <= remainder; i++)
            answer[n - i]++;

        return answer;
    }
}
