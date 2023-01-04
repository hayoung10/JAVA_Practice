package Level3;

import java.util.*;

public class Expressed_as_N {
    // (1) DP 동적계획법
    public int solution1(int N, int number) {
        Set<Integer>[] set = new HashSet[9];

        int num = 0;
        for(int i = 1; i < 9; i++) {
            num = num * 10 + N;
            set[i] = new HashSet<>();
            set[i].add(num);
        }

        for(int k = 1; k < 9; k++) { // k : N 사용횟수
            for(int i = 1; i < k; i++) {
                for(Integer a : set[i]) {
                    for(Integer b : set[k - i]) {
                        set[k].add(a + b);
                        set[k].add(a - b);

                        if(b != 0) {
                            set[k].add(a * b);
                            set[k].add(a / b);
                        }
                    }
                }
            }
            if(set[k].contains(number))
                return k;
        }

        return -1;
    }

    // (2) DFS 깊이 우선 탐색
    private int depth = 9; // N 사용횟수의 최솟값 (초기값 : 9)

    public int solution2(int N, int number) {
        dfs(N, number, 0, 0);

        if(depth < 9) return depth;

        return -1;
    }

    private void dfs(int N, int number, int cnt, int cal) { // cnt : N 사용횟수, cal : 사칙연산 결과
        if(cnt > 8) return;

        if(cal == number)
            depth = Math.min(depth, cnt); // N 사용횟수의 최솟값 업데이트

        int num = 0;
        for(int i = 1; i < 9; i++) {
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
