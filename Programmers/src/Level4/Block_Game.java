package Level4;

import java.util.*;

public class Block_Game { // 블록 게임
    int[][] board;
    Map<Integer, Integer[]> blocks = new HashMap<>(); // (블록 번호, 블록 좌표)
    Map<Integer, ArrayList<Integer>> column = new HashMap<>(); // (열, 블록의 행)

    public int solution(int[][] board) {
        int answer = 0;

        // 초기화
        this.board = board;
        for(int j = 0; j < board.length; j++) {
            ArrayList<Integer> list = new ArrayList<>();
            for(int i = 0; i < board.length; i++) {
                int num = board[i][j];
                if(num == 0) continue;

                list.add(i);
                if(blocks.containsKey(num)) {
                    if(i < blocks.get(num)[0]) blocks.get(num)[0] = i;
                    if(blocks.get(num)[1] < i) blocks.get(num)[1] = i;
                    if(j < blocks.get(num)[2]) blocks.get(num)[2] = j;
                    if(blocks.get(num)[3] < j) blocks.get(num)[3] = j;
                } else {
                    blocks.put(num, new Integer[]{i, i, j , j});
                }
            }
            if(!list.isEmpty()) column.put(j, list);
        }

        ArrayList<Integer> removeBlocks = new ArrayList<>(); // 한 루프 당 제거할 블록들
        while(true) {
            removeBlocks.clear();
            for(int j : column.keySet()) { // 블록 위에 검은 블록을 2개씩 쌓기
                int i = column.get(j).get(0);

                if(0 < i) board[i - 1][j] = -1;
                if(1 < i) board[i - 2][j] = -1;
            }

            f : for(int num : blocks.keySet()) {
                // 속이 꽉 채워진 직사각형을 만들 수 있는지 검사
                for(int i = blocks.get(num)[0]; i <= blocks.get(num)[1]; i++)
                    for(int j = blocks.get(num)[2]; j <= blocks.get(num)[3]; j++)
                        if(board[i][j] != num && board[i][j] != -1) continue f;
                removeBlocks.add(num); // 제거할 블록 추가
            }

            if(removeBlocks.isEmpty()) break;

            for(int num : removeBlocks) {
                removeBlock(num);
                answer++;
            }
        }

        return answer;
    }

    private void removeBlock(int num) { // 해당 번호의 블록 제거
        for(int j = blocks.get(num)[2]; j <= blocks.get(num)[3]; j++) {
            for(int i = blocks.get(num)[0]; i <= blocks.get(num)[1]; i++) {
                for(int k = 0; k < column.get(j).size(); k++) {
                    if(board[i][j] == num && column.get(j).get(k) == i) {
                        column.get(j).remove(k);
                        break;
                    }
                }
                board[i][j] = 0;
            }
            if(column.get(j).isEmpty()) column.remove(j);
        }
        blocks.remove(num);
    }
}
