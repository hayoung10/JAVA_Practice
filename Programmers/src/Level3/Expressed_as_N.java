package Level3;

public class Expressed_as_N {
    // (1) DP 동적계획법
    public int solution1(int N, int number) {
        int answer = 0;
        return answer;
    }

    // (2) DFS 깊이 우선 탐색
    private int depth = 9; // N 사용횟수의 최댓값

    public int solution2(int N, int number) {
        dfs(N, number, 0, 0);

        if(depth > 8) return -1;

        return depth;
    }

    private void dfs(int N, int number, int cnt, int cal) { // cal : 사칙연산 결과
        if(cnt > 8) return;

        if(cal == number)
            depth = Math.min(depth, cnt); // N 사용횟수의 최솟값 업데이트

        int num = 0;
        for(int i = 1; i < 10; i++) {
            num = num * 10 + N;

            dfs(N, number, cnt + i, cal + num); // 덧셈
            dfs(N, number, cnt + i, cal - num); // 뺄셈

            if(cal != 0) {
                dfs(N, number, cnt + i, cal * num); // 곱셈
                dfs(N, number, cnt + i, cal / num); // 나눗셈
            }
        }
    }
}
