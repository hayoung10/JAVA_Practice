package Level4;

import java.util.*;

public class Matrices_n_Operations { // 행렬과 연산
    int R, C, cnt = 0; // cnt : ShiftRow 연산 횟수
    ArrayDeque<Integer> colL = new ArrayDeque<>(); // 행렬의 가장 왼쪽 열
    ArrayDeque<Integer> colR = new ArrayDeque<>(); // 행렬의 가장 오른쪽 열
    ArrayDeque<Integer>[] rows; // 양쪽 열을 하나씩 제외한 행들

    private void init(int[][] rc) { // 초기화
        R = rc.length;
        C = rc[0].length;
        for(int i = 0; i < R; i++) {
            colL.addLast(rc[i][0]);
            colR.addLast(rc[i][C - 1]);
        }
        rows = new ArrayDeque[R];
        for(int i = 0; i < R; i++) {
            rows[i] = new ArrayDeque<>();
            for(int j = 1; j < C - 1; j++)
                rows[i].addLast(rc[i][j]);
        }
    }

    private void shiftRow() { // ShiftRow 연산
        colL.addFirst(colL.pollLast());
        colR.addFirst(colR.pollLast());
        cnt++; cnt %= R;
    }

    private void rotate() { // Rotate 연산
        rows[(R - cnt) % R].addFirst(colL.pollFirst());
        colR.addFirst(rows[(R - cnt) % R].pollLast());
        rows[R - 1 - cnt].addLast(colR.pollLast());
        colL.addLast(rows[R - 1 - cnt].pollFirst());
    }

    private int[][] getAnswer() { // 출력 형식으로 행렬 만들기
        int[][] answer = new int[R][C];
        for(int i = 0; i < R; i++) {
            answer[i][0] = colL.pollFirst();
            for(int j = 1; j < C - 1; j++)
                answer[i][j] = rows[(i + R - cnt) % R].pollFirst();
            answer[i][C - 1] = colR.pollFirst();
        }
        return answer;
    }

    public int[][] solution(int[][] rc, String[] operations) {
        init(rc);

        for(String op : operations) { // 연산하기
            if(op.equals("ShiftRow")) shiftRow();
            else if(op.equals("Rotate")) rotate();
        }
        return getAnswer();
    }
}