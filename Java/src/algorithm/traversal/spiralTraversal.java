package algorithm.traversal;

public class spiralTraversal {
    // 2차원 M x N 배열을 나선형(spiral)으로 순회

    // {{'A', 'B', 'C'},
    //  {'D', 'E', 'F'},
    //  {'G', 'H', 'I'}}   => result : ABCDEFGHI

    public String spiral(Character[][] matrix) {
        // 좌측 상단 (0,0)에서 시작
        int c1 = 0, c2 = matrix[0].length - 1; // column
        int r1 = 0, r2 = matrix.length - 1; // row
        int rotate = 0;
        String result = "";

        while(r1 <= r2 && c1 <= c2) {
            if(rotate % 4 == 0) {
                for(int j = c1; j <= c2; j++)
                    result += matrix[r1][j];
                r1++;
            } else if(rotate % 4 == 1) {
                for(int i = r1; i <= r2; i++)
                    result += matrix[i][c2];
                c2--;
            } else if(rotate % 4 == 2) {
                for(int j = c2; j >= c1; j--)
                    result += matrix[r2][j];
                r2--;
            } else {
                for(int i = r2; i >= r1; i--)
                    result += matrix[i][c1];
                c1++;
            }

            rotate++;
        }

        return result;
    }
}
