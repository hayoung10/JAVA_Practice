package Level3;

public class Base_station_Installation { // 기지국 설치
    public int solution(int n, int[] stations, int w) {
        int answer = 0;

        int range = 2 * w + 1; // 전파를 전달할 수 있는 범위
        int apart = stations[0] - w - 1; // 연속적으로 전파가 도달하지 않는 아파트의 수
        answer += number(apart, range);

        for(int i = 1; i < stations.length; i++) {
            apart = stations[i] - stations[i - 1] - range;
            answer += number(apart, range);
        }
        apart = n - stations[stations.length - 1] - w;
        answer += number(apart, range);

        return answer;
    }

    private int number(int apart, int range) {
        if(apart > 0) {
            int quotient = (int)(apart / range);
            if(apart % range > 0) quotient++;
            return quotient; // 증설해야 하는 기지국 개수
        }
        return 0; // 증설 안 함
    }
}
