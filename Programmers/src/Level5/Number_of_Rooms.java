package Level5;

import java.security.KeyPair;
import java.util.*;

// Graph 그래프

public class Number_of_Rooms {
    public int solution(int[] arrows) {
        int answer = 0;

        // 방향 (0~7)
        int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
        int[] dy = {1, 1, 0, -1, -1, -1, 0, 1};

        HashMap<Point, List<Point>> visited = new HashMap<>(); // 방문 여부 확인
        Point cur = new Point(0, 0); // 현 위치

        for(int a : arrows) {
            for(int i = 0 ; i <= 1; i++) { // 스케일업 (2배)
                Point next = new Point(cur.x + dx[a], cur.y + dy[a]); // 다음 위치

                if(!visited.containsKey(next)) { // 처음 방문한 점인 경우
                    visited.put(next, makeList(cur)); // 연결점 추가

                    if(visited.get(cur) == null)
                        visited.put(cur, makeList(next));
                    else
                        visited.get(cur).add(next);
                } else if(!visited.get(next).contains(cur)) { // 처음 통과하는 간선인 경우
                    visited.get(cur).add(next);
                    visited.get(next).add(cur);
                    answer++;
                }
                cur = next;
            }
        }

        return answer;
    }

    public List<Point> makeList(Point point) { // Point 리스트 생성
        List<Point> list = new ArrayList<>();
        list.add(point);
        return list;
    }

    class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public boolean equals(Object obj) {
            Point p = (Point) obj;
            return this.x == p.x && this.y == p.y;
        }
    }
}
