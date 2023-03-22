package Level3;

import java.util.*;

// BFS 너비 우선 탐색

public class Move_Blocks { // 블럭 이동하기
    int[][] board;
    boolean[][] visitedh; // 가로일때
    boolean[][] visitedv; // 세로일때
    int last;

    public int solution(int[][] board) {
        this.board = board;
        last = board.length - 1;
        visitedh = new boolean[last + 1][last + 1];
        visitedv = new boolean[last + 1][last + 1];

        // (0,0), (0,1), 가로, 시간 : 0
        Robot robot = new Robot(0, 0, 0, 1, true, 0);
        int answer = bts(robot);

        return answer;
    }

    private int bts(Robot robot) {
        Queue<Robot> q = new LinkedList<>();
        q.add(robot);

        while(!q.isEmpty()) {
            Robot cur = q.remove();

            if(cur.x2 == last && cur.y2 == last) return cur.time; // 도착

            if(cur.type) { // 로봇이 가로로 위치하면
                if(0 < cur.y1) { // 왼쪽으로 이동
                    Robot tmp = new Robot(cur.x1, cur.y1 - 1, cur.x2, cur.y2 - 1, true, cur.time);
                    if(check(tmp)) q.add(tmp);
                }
                if(0 < cur.x1) { // 위로 이동
                    Robot tmp = new Robot(cur.x1 - 1, cur.y1, cur.x2 - 1, cur.y2, true, cur.time);
                    if(check(tmp)) q.add(tmp);

                    if(board[cur.x1 - 1][cur.y1] == 0 && board[cur.x2 - 1][cur.y2] == 0) {
                        // 왼쪽을 축으로 위로 회전
                        Robot tmp2 = new Robot(cur.x1 - 1, cur.y1, cur.x2, cur.y2 - 1, false, cur.time);
                        if(check(tmp2)) q.add(tmp2);

                        // 오른쪽을 축으로 위로 회전
                        Robot tmp3 = new Robot(cur.x1 - 1, cur.y1 + 1, cur.x2, cur.y2, false, cur.time);
                        if(check(tmp3)) q.add(tmp3);
                    }
                }
                if(cur.x1 < last) { // 아래로 이동
                    Robot tmp = new Robot(cur.x1 + 1, cur.y1, cur.x2 + 1, cur.y2, true, cur.time);
                    if(check(tmp)) q.add(tmp);

                    if(board[cur.x1 + 1][cur.y1] == 0 && board[cur.x2 + 1][cur.y2] == 0) {
                        // 왼쪽을 축으로 아래로 회전
                        Robot tmp2 = new Robot(cur.x1, cur.y1, cur.x2 + 1, cur.y2 - 1, false, cur.time);
                        if(check(tmp2)) q.add(tmp2);

                        // 오른쪽을 축으로 아래로 회전
                        Robot tmp3 = new Robot(cur.x1, cur.y1 + 1, cur.x2 + 1, cur.y2, false, cur.time);
                        if(check(tmp3)) q.add(tmp3);
                    }
                }
                if(cur.y2 < last) { // 오른쪽으로 이동
                    Robot tmp = new Robot(cur.x1, cur.y1 + 1, cur.x2, cur.y2 + 1, true, cur.time);
                    if(check(tmp)) q.add(tmp);
                }
            } else { // 로봇이 세로로 위치하면
                if(0 < cur.y1) { // 왼쪽으로 이동
                    Robot tmp = new Robot(cur.x1, cur.y1 - 1, cur.x2, cur.y2 - 1, false, cur.time);
                    if(check(tmp)) q.add(tmp);

                    if(board[cur.x1][cur.y1 - 1] == 0 && board[cur.x2][cur.y2 - 1] == 0) {
                        // 위를 축으로 왼쪽으로 회전
                        Robot tmp2 = new Robot(cur.x1, cur.y1 - 1, cur.x2 - 1, cur.y2, true, cur.time);
                        if(check(tmp2)) q.add(tmp2);

                        // 아래를 축으로 왼쪽으로 회전
                        Robot tmp3 = new Robot(cur.x1 + 1, cur.y1 - 1, cur.x2, cur.y2, true, cur.time);
                        if(check(tmp3)) q.add(tmp3);
                    }
                }
                if(0 < cur.x1) { // 위로 이동
                    Robot tmp = new Robot(cur.x1 - 1, cur.y1, cur.x2 - 1, cur.y2, false, cur.time);
                    if(check(tmp)) q.add(tmp);
                }
                if(cur.x2 < last) { // 아래로 이동
                    Robot tmp = new Robot(cur.x1 + 1, cur.y1, cur.x2 + 1, cur.y2, false, cur.time);
                    if(check(tmp)) q.add(tmp);
                }
                if(cur.y1 < last) { // 오른쪽으로 이동
                    Robot tmp = new Robot(cur.x1, cur.y1 + 1, cur.x2, cur.y2 + 1, false, cur.time);
                    if(check(tmp)) q.add(tmp);

                    if(board[cur.x1][cur.y1 + 1] == 0 && board[cur.x2][cur.y2 + 1] == 0) {
                        // 위를 축으로 오른쪽으로 회전
                        Robot tmp2 = new Robot(cur.x1, cur.y1, cur.x2 - 1, cur.y2 + 1, true, cur.time);
                        if(check(tmp2)) q.add(tmp2);

                        // 아래를 축으로 오른쪽으로 회전
                        Robot tmp3 = new Robot(cur.x1 + 1, cur.y1, cur.x2, cur.y2 + 1, true, cur.time);
                        if(check(tmp3)) q.add(tmp3);
                    }
                }
            }
        }

        return -1; // 실패 시
    }

    private boolean check(Robot robot) { // 이동 가능한지 확인
        if(board[robot.x1][robot.y1] == 1 || board[robot.x2][robot.y2] == 1)
            return false;

        robot.time++;
        if(robot.type && !visitedh[robot.x1][robot.y1]) // 가로일 때 방문 확인
            visitedh[robot.x1][robot.y1] = true;
        else if(!robot.type && !visitedv[robot.x1][robot.y1]) // 세로일 때 방문 확인
            visitedv[robot.x1][robot.y1] = true;
        else
            return false;

        return true;
    }

    class Robot {
        int x1, y1;
        int x2, y2;
        boolean type; // true : 가로, false : 세로
        int time; // 시간

        public Robot(int x1, int y1, int x2, int y2, boolean type, int time) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.type = type;
            this.time = time;
        }
    }
}
