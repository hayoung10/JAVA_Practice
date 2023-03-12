package Level4;

public class Buy_Cookies { // 쿠키 구입
    public int solution(int[] cookie) {
        int answer = 0;

        for(int m = 0; m < cookie.length - 1; m++) {
            int lSum = cookie[m]; // left sum
            int rSum = cookie[m + 1]; // right sum

            // l <= m < m+1 <= r
            int l = m;
            int r = m + 1;

            while(true) {
                if(lSum == rSum)
                    answer = Math.max(answer, lSum);

                if(lSum < rSum) {
                    if(l == 0) break;
                    lSum += cookie[--l];
                } else {
                    if(r == cookie.length - 1) break;
                    rSum += cookie[++r];
                }
            }
        }

        return answer;
    }
}
