package Gold4;

import java.io.*;
import java.util.*;

public class G1186 { // 직사각형 색칠하기
    static List<List<int[]>> rects = new ArrayList<>();
    static int[] newRect;

    private static void compare(int idx) { // 기존의 idx번 째 직사각형과 새로운 직사각형 newRect 비교
        List<int[]> listRect = new ArrayList<>();

        for(int[] rect : rects.get(idx)) {
            // 새로운 직사각형에 rect 직사각형이 포함되는 경우
            if(newRect[0] <= rect[0] && newRect[1] <= rect[1] && newRect[2] >= rect[2] && newRect[3] >= rect[3]) continue;
            // 두 직사각형이 겹치지 않는 경우
            if(newRect[0] >= rect[2] || newRect[1] >= rect[3] || newRect[2] <= rect[0] || newRect[3] <= rect[1]) {
                listRect.add(rect);
            }
            // 두 직사각형이 일부만 겹치는 경우
            else {
                // 겹치는 부분의 x1, y1, x2, y2 좌표
                int max_x1 = Math.max(rect[0], newRect[0]);
                int max_y1 = Math.max(rect[1], newRect[1]);
                int min_x2 = Math.min(rect[2], newRect[2]);
                int min_y2 = Math.min(rect[3], newRect[3]);
                // 겹치는 부분을 제외한 rect 직사각형을 다시 직사각형으로 나누기
                if(rect[1] < max_y1) listRect.add(new int[]{rect[0], rect[1], rect[2], max_y1});
                if(min_y2 < rect[3]) listRect.add(new int[]{rect[0], min_y2, rect[2], rect[3]});
                if(rect[0] < max_x1 && max_y1 < min_y2) listRect.add(new int[]{rect[0], max_y1, max_x1, min_y2});
                if(min_x2 < rect[2] && max_y1 < min_y2) listRect.add(new int[]{min_x2, max_y1, rect[2], min_y2});
            }
        }
        rects.set(idx, listRect);
    }

    private static List<Area> sumArea() { // 나눠진 작은 직사각형들을 합쳐 면적 구하기
        List<Area> areas = new ArrayList<>();

        for(int i = 0; i < rects.size(); i++) {
            int sum = 0;
            for(int[] loc : rects.get(i)) sum += (loc[2] - loc[0]) * (loc[3] - loc[1]);
            areas.add(new Area(i + 1, sum));
        }
        return areas;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());

        for(int i = 0; i < N; i++) {
            newRect = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for(int j = 0; j < rects.size(); j++) compare(j);
            rects.add(List.of(newRect));
        }
        List<Area> areas = sumArea();
        Collections.sort(areas);
        List<Integer> answer = new ArrayList<>();
        for(int i = 0; i < K; i++) answer.add(areas.get(i).num);
        Collections.sort(answer);
        System.out.println(answer.toString().replaceAll("[^0-9\\s]",""));
    }

    static class Area implements Comparable<Area> {
        int num, area;

        public Area(int num, int area) {
            this.num = num;
            this.area = area;
        }

        @Override
        public int compareTo(Area o) {
            return o.area - this.area;
        }
    }
}