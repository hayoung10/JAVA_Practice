package Level3;

// DP 동적계획법

public class Sum_of_Continuous_Pluse_Subsequences { // 연속 펄스 부분 수열의 합
    public long solution(int[] sequence) {
        long[] DP = new long[sequence.length + 1];
        for(int i = 1; i < DP.length; i++) {
            if(i % 2 == 0) DP[i] = DP[i - 1] + sequence[i - 1] * -1;
            else DP[i] = DP[i - 1] + sequence[i - 1];
        }

        long max_sum = Long.MIN_VALUE;
        long min_sum = Long.MAX_VALUE;
        for(int i = 0; i < DP.length; i++) {
            if(max_sum < DP[i]) max_sum = DP[i];
            if(min_sum > DP[i]) min_sum = DP[i];
        }

        return Math.abs(max_sum - min_sum);
    }
}
