package Level4;

// DP 동적계획법

public class Four_Arithmetic_Operations {
    private int[] numbers;
    private String[] operations;
    private int[][][] dp = new int[201][201][2];

    public int solution(String arr[]) {
        int n = arr.length / 2;

        numbers = new int[n + 1];
        operations = new String[n];
        for(int i = 0; i < n + 1; i++) {
            for(int j = 0; j < n + 1; j++) {
                dp[i][j][0] = Integer.MIN_VALUE;
                dp[i][j][1] = Integer.MAX_VALUE;
            }
        }

        for(int i = 0; i < arr.length; i++) {
            if(i % 2 == 0) numbers[i / 2] = Integer.parseInt(arr[i]);
            else operations[i / 2] = arr[i];
        }

        return func(0, n, 0);
    }

    private int func(int start, int end, int flag) { // flag == 0 : 최대, 1 : 최소
        if(start == end)
            return dp[start][end][flag] = numbers[start];

        if(dp[start][end][flag] != Integer.MIN_VALUE && dp[start][end][flag] != Integer.MAX_VALUE)
            return dp[start][end][flag];

        dp[start][end][flag] = 0; // visited

        int result = flag == 0 ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for(int i = start; i < end; i++) {
            if(operations[i].equals("-")) {
                result = flag == 0 ? Math.max(result, func(start, i, 0) - func(i + 1, end, 1)) : Math.min(result, func(start, i, 1) - func(i + 1, end, 0));
            } else {
                result = flag == 0 ? Math.max(result, func(start, i, 1) + func(i + 1, end, 1)) : Math.min(result, func(start, i, 0) + func(i + 1, end, 0));
            }
        }

        return dp[start][end][flag] = result;
    }
}
