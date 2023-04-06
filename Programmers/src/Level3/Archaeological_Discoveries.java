package Level3;

// Exhaustive Search 완전 탐색 - Bitmask

public class Archaeological_Discoveries { // 고고학 최고의 발견
    int N;
    public int solution(int[][] clockHands) {
        N = clockHands.length;

        int answer = (int) Math.pow(3, N * N);

        for(int i = 0; i < Math.pow(4, N); i++) { // 비트 연산
            int[][] map = deepCopy(clockHands);
            int total_cnt = 0;
            int tmp = i;

            for(int col = 0; col < N; col++) { // 첫 번째 줄만 판별
                int cnt = (int) Math.floor(tmp % 4);
                tmp /= 4;
                rotate(map, 0, col, cnt);
                total_cnt += cnt;
            }

            for(int row = 1; row < N; row++) {
                for(int col = 0; col < N; col++) {
                    int cnt = (4 - map[row - 1][col]) % 4; // 해당 시계의 바로 위 시계 확인

                    if(cnt == 0) continue; // 회전 필요 없음

                    rotate(map, row, col, cnt); // 필요한 만큼 회전
                    total_cnt += cnt;
                }
            }

            if(check(map))
                answer = Math.min(answer, total_cnt);
        }

        return answer;
    }

    private void rotate(int[][] map, int row, int col, int cnt) { // cnt 번 만큼 시곗바늘 회전
        map[row][col] = (map[row][col] + cnt) % 4;

        if(row > 0) map[row - 1][col] = (map[row - 1][col] + cnt) % 4;
        if(col > 0) map[row][col - 1] = (map[row][col - 1] + cnt) % 4;
        if(row < N - 1) map[row + 1][col] = (map[row + 1][col] + cnt) % 4;
        if(col < N - 1) map[row][col + 1] = (map[row][col + 1] + cnt) % 4;
    }

    private boolean check(int[][] map) { // 모든 시곗바늘이 12시 방향을 가리키는지 확인
        for(int col = 0; col < N; col++)
            if(map[N - 1][col] > 0)
                return false;
        return true;
    }

    private int[][] deepCopy(int[][] arr) { // 깊은 복사
        int[][] copyArr = new int[N][N];
        for(int i = 0; i < N; i++)
            copyArr[i] = arr[i].clone();
        return copyArr;
    }
}
