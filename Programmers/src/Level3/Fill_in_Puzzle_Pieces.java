package Level3;

import java.util.*;

// BFS 너비 우선 탐색

public class Fill_in_Puzzle_Pieces { // 퍼즐 조각 채우기
    private int[] dx = {-1, 1, 0, 0};
    private int[] dy = {0, 0, -1, 1};

    private boolean[][] visited;

    private List<List<int[]>> boardList = new ArrayList<>();
    private List<List<int[]>> tableList = new ArrayList<>();

    public int solution(int[][] game_board, int[][] table) {
        int answer = 0;

        // 초기화
        int len = game_board.length;
        visited = new boolean[len][len];

        for(int i = 0; i < len; i++) {
            for(int j = 0; j < len; j++) {
                if(game_board[i][j] == 0 && !visited[i][j])
                    boardList.add(BFS(game_board, i, j, 0)); // 게임 보드의 빈 칸 확인
            }
        }

        for(int i = 0; i < len; i++)
            Arrays.fill(visited[i], false); // 초기화

        for(int i = 0; i < len; i++) {
            for(int j = 0; j < len; j++) {
                if(table[i][j] == 1 && !visited[i][j])
                    tableList.add(BFS(table, i, j, 1)); // 퍼즐 조각 확인
            }
        }

        boolean[] visitedBoard = new boolean[boardList.size()];
        for(int i = 0; i < tableList.size(); i++) {
            List<int[]> tablePieces = tableList.get(i);

            for(int j = 0; j < boardList.size(); j++) {
                List<int[]> boardPieces = boardList.get(j);

                if(boardPieces.size() != tablePieces.size() || visitedBoard[j]) continue;

                if(rotateCheck(boardPieces, tablePieces)) { // 퍼즐을 회전하여 비교
                    answer += tablePieces.size(); // 퍼즐 조각 수 추가
                    visitedBoard[j] = true;
                    break;
                }
            }
        }

        return answer;
    }

    private boolean rotateCheck(List<int[]> boardPieces, List<int[]> tablePieces) {
        boardPieces.sort((o1, o2) -> {
            return o1[0] == o2[0] ? Integer.compare(o1[1], o2[1]) : Integer.compare(o1[0], o2[0]);
        });

        for(int i = 0; i < 4; i++) { // 90도씩 회전하여 비교
            tablePieces.sort((o1, o2) -> {
                return o1[0] == o2[0] ? Integer.compare(o1[1], o2[1]) : Integer.compare(o1[0], o2[0]);
            });

            // 기준 좌표
            int originX = tablePieces.get(0)[0];
            int originY = tablePieces.get(0)[1];
            for(int j = 0; j < tablePieces.size(); j++) { // 위 좌표를 기준으로 이동
                tablePieces.get(j)[0] -= originX;
                tablePieces.get(j)[1] -= originY;
            }

            boolean pointsCheck = true;
            for(int j = 0; j < boardPieces.size(); j++) { // 좌표 비교
                if(boardPieces.get(j)[0] != tablePieces.get(j)[0] || boardPieces.get(j)[1] != tablePieces.get(j)[1]) {
                    pointsCheck = false;
                    break;
                }
            }

            if(pointsCheck)
                return true;

            // 90도 회전 : (x,y) -> (y, -x)
            for(int j = 0; j < tablePieces.size(); j++) {
                int temp = tablePieces.get(j)[0];
                tablePieces.get(j)[0] = tablePieces.get(j)[1];
                tablePieces.get(j)[1] = -temp;
            }
        }

        return false;
    }

    private List<int[]> BFS(int[][] graph, int x, int y, int check) {
        visited[x][y] = true;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        List<int[]> list = new ArrayList<>();
        list.add(new int[]{0, 0}); // (x, y) 좌표 기준

        while(!queue.isEmpty()) {
            int[] front = queue.remove();

            for(int i = 0; i < 4; i++) {
                int nx = front[0] + dx[i];
                int ny = front[1] + dy[i];

                if(nx < 0 || ny < 0 || nx >= graph.length || ny >= graph.length)
                    continue;

                if(graph[nx][ny] == check && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.add(new int[]{nx, ny});
                    list.add(new int[]{nx - x, ny - y});
                }
            }
        }

        return list;
    }
}
