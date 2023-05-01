package Level3;

public class Ball_Movement_Simulation { // 공 이동 시뮬레이션
    public long solution(int n, int m, int x, int y, int[][] queries) {
        // 좌표의 범위 : (x1, y1) ~ (x2, y2)
        int x1 = x, x2 = x;
        int y1 = y, y2 = y;

        for(int i = queries.length - 1; i >= 0; i--) { // 역순으로 추적
            int query = queries[i][0];
            int step = queries[i][1];
            if(query == 0) { // 오른쪽으로 이동
                if(y1 > 0) y1 += step;
                y2 = Math.min(m - 1, y2 + step);
            } else if(query == 1) { // 왼쪽으로 이동
                if(y2 < m - 1) y2 -= step;
                y1 = Math.max(0, y1 - step);
            } else if(query == 2) { // 아래로 이동
                if(x1 > 0) x1 += step;
                x2 = Math.min(n - 1, x2 + step);
            } else { // 위로 이동
                if(x2 < n - 1) x2 -= step;
                x1 = Math.max(0, x1 - step);
            }
            if(x1 >= n || x2 < 0 || y1 >= m || y2 < 0) return 0;
        }

        return (x2 - x1 + 1) * (y2 - y1 + 1);
    }
}
