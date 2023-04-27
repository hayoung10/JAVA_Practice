package Level3;

// DP 동적계획법

public class Coding_Test_Study { // 코딩 테스트 공부
    public int solution(int alp, int cop, int[][] problems) {
        // 초기화
        int max_alp = alp; // 최종 알고력
        int max_cop = cop; // 최종 코딩력
        for(int i = 0; i < problems.length; i++) {
            max_alp = Math.max(max_alp, problems[i][0]);
            max_cop = Math.max(max_cop, problems[i][1]);
        }

        // case 1. 알고력과 코딩력이 모두 최종 값에 도달한 경우
        if(alp >= max_alp && cop >= max_cop) return 0;

        // case 2. 알고력만 최종값에 도달한 경우
        if(alp >= max_alp) alp = max_alp;

        // case 3. 코딩력만 최종값에 도달한 경우
        if(cop >= max_cop) cop = max_cop;

        // case 4. 알고력과 코딩력 모두 최종 값에 도달하지 못한 경우
        int[][] DP = new int[max_alp + 2][max_cop + 2];
        for(int i = alp; i <= max_alp; i++)
            for(int j = cop; j <= max_cop; j++)
                DP[i][j] = Integer.MAX_VALUE;

        DP[alp][cop] = 0;
        for(int i = alp; i <= max_alp; i++) {
            for(int j = cop; j <= max_cop; j++) {
                DP[i + 1][j] = Math.min(DP[i + 1][j], DP[i][j] + 1);
                DP[i][j + 1] = Math.min(DP[i][j + 1], DP[i][j] + 1);

                for(int[] p : problems) {
                    if(i >= p[0] && j >= p[1]) {
                        if(i + p[2] > max_alp && j + p[3] > max_cop) // case 1
                            DP[max_alp][max_cop] = Math.min(DP[max_alp][max_cop], DP[i][j] + p[4]);
                        else if(i + p[2] > max_alp) // case 2
                            DP[max_alp][j + p[3]] = Math.min(DP[max_alp][j + p[3]], DP[i][j] + p[4]);
                        else if(j + p[3] > max_cop) // case 3
                            DP[i + p[2]][max_cop] = Math.min(DP[i + p[2]][max_cop], DP[i][j] + p[4]);
                        else if(i + p[2] <= max_alp && j + p[3] <= max_cop) // case 4
                            DP[i + p[2]][j + p[3]] = Math.min(DP[i + p[2]][j + p[3]], DP[i][j] + p[4]);
                    }
                }
            }
        }

        return DP[max_alp][max_cop];
    }
}
