package Level3;

// Prefix Sum 누적합

public class Undestroyed_Building { // 파괴되지 않은 건물
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;

        int N = board.length;
        int M = board[0].length;
        int[][] sum = new int[N + 1][M + 1]; // 누적합 배열

        for(int[] s : skill) { // 누적합 표기
            int degree = s[5] * (s[0] == 1 ? -1 : 1);

            sum[s[1]][s[2]] += degree;
            sum[s[3] + 1][s[4] + 1] += degree;
            sum[s[1]][s[4] + 1] += degree * -1;
            sum[s[3] + 1][s[2]] += degree * -1;
        }

        for(int i = 0; i < N + 1; i++) // 가로 누적합 계산 (왼 -> 오른)
            for(int j = 0; j < M; j++)
                sum[i][j + 1] += sum[i][j];

        for(int j = 0; j < M + 1; j++) // 세로 누적합 계산 (위 -> 아래)
            for(int i = 0; i < N; i++)
                sum[i + 1][j] += sum[i][j];

        // 파괴되지 않은 건물 count
        for(int i = 0; i < N; i++)
            for(int j = 0; j < M; j++)
                if(board[i][j] + sum[i][j] > 0) answer++;

        return answer;
    }
}
