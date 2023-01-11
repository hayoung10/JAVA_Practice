package Level3;

// DFS 깊이 우선 탐색

public class Network { // 네트워크
    public int solution(int n, int[][] computers) {
        int answer = 0;

        for(int i = 0; i < computers.length; i++) {
            if(computers[i][i] == 1)
                if(DFS(i, computers))
                    answer++;
        }

        return answer;
    }

    private boolean DFS(int n, int[][] computers) {
        if(computers[n][n] == 0) // already visited
            return false;

        computers[n][n] = 0; // visited
        for(int i = 0; i < computers.length; i++) {
            if(computers[n][i] == 1)
                DFS(i, computers);
        }

        return true;
    }
}
