package Gold3;

import java.io.*;
import java.util.*;

public class G1379 { // 강의실 2
    static int answer = 0; // 필요한 최소 강의실의 수
    static Lecture[] lectures;
    static int[] rooms; // 배정할 강의실 번호

    private static void solution(int N) { // greedy algorithm 이용
        PriorityQueue<Lecture> pq = new PriorityQueue<>((o1, o2) -> {
            return o1.etime - o2.etime;
        }); // 강의가 일찍 끝나는 순으로 정렬

        for(int i = 0; i < N; i++) {
            if(!pq.isEmpty() && (pq.peek().etime <= lectures[i].stime)) { // 해당 강의실을 사용할 수 있는 경우
                rooms[lectures[i].num] = rooms[pq.peek().num];
                pq.remove();
            } else { // 강의실 추가
                answer++;
                rooms[lectures[i].num] = answer;
            }
            pq.add(lectures[i]);
        }
    }

    public static void main(String[] args) throws IOException {
        // 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        lectures = new Lecture[N]; rooms = new int[N + 1];

        StringTokenizer st;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            lectures[i] = new Lecture(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(lectures, new Comparator<Lecture>() {
            @Override
            public int compare(Lecture o1, Lecture o2) {
                return o1.stime - o2.stime;
            }
        }); // 강의가 일찍 시작하는 순으로 정렬

        solution(N);
        System.out.println(answer);
        for(int i = 1; i <= N; i++) System.out.println(rooms[i]);
    }

    static class Lecture {
        int num; // 강의 번호
        int stime, etime; // start time, end time

        public Lecture(int num, int stime, int etime) {
            this.num = num;
            this.stime = stime;
            this.etime = etime;
        }
    }
}