package Gold5;

import java.io.*;
import java.util.*;

public class G1374 { // 강의실
    static PriorityQueue<Lecture> pq = new PriorityQueue<>();
    
    private static int solution(int N) { // greedy algorithm 이용
        int minCnt = 1; // 필요한 강의실 수
        int[] rooms = new int[N + 1];
        rooms[minCnt - 1] = pq.remove().etime;

        while(!pq.isEmpty()) {
            Lecture lec = pq.remove();

            boolean check = false; // 해당 강의 시작 시간에 맞춰 강의실을 사용할 수 있는지 여부
            for(int i = 0; i < minCnt; i++) {
                if(rooms[i] <= lec.stime) { // 해당 강의실을 사용할 수 있는 경우
                    rooms[i] = lec.etime;
                    check = true;
                    break;
                }
            }
            if(!check)
                rooms[minCnt++] = lec.etime; // 사용할 수 있는 강의실이 없으므로 강의실 추가
        }

        return minCnt; // 필요한 최소 강의실의 수
    }
    
    public static void main(String[] args) throws IOException {
        // 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine()); st.nextToken();
            pq.add(new Lecture(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        System.out.println(solution(N));
    }

    static class Lecture implements Comparable<Lecture> {
        int stime, etime; // start time, end time

        public Lecture(int stime, int etime) {
            this.stime = stime;
            this.etime = etime;
        }

        @Override
        public int compareTo(Lecture o) {
            return this.stime == o.stime ? this.etime - o.etime : this.stime - o.stime;
        }
    }
}