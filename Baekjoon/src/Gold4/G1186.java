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
            // rect 직사각형이 한 개로 나눠지는 경우
            else if(newRect[0] <= rect[0] && (rect[1] <= newRect[1] && newRect[1] <= rect[3]) && newRect[2] >= rect[2] && newRect[3] >= rect[3]) {
                listRect.add(new int[]{rect[0], rect[1], rect[2], newRect[1]}); // 새로운 직사각형이 위쪽에 있는 경우
            } else if((rect[0] <= newRect[0] && newRect[0] <= rect[2]) && newRect[1] <= rect[1] && newRect[2] >= rect[2] && newRect[3] >= rect[3]) {
                listRect.add(new int[]{rect[0], rect[1], newRect[0], rect[3]}); // 새로운 직사각형이 오른쪽에 있는 경우
            } else if(newRect[0] <= rect[0] && newRect[1] <= rect[1] && newRect[2] >= rect[2] && (rect[1] <= newRect[3] && newRect[3] <= rect[3])) {
                listRect.add(new int[]{rect[0], newRect[3], rect[2], rect[3]}); // 새로운 직사각형이 아래쪽에 있는 경우
            } else if(newRect[0] <= rect[0] && newRect[1] <= rect[1] && (rect[0] <= newRect[2] && newRect[2] <= rect[2]) && newRect[3] >= rect[3]) {
                listRect.add(new int[]{newRect[2], rect[1], rect[2], rect[3]}); // 새로운 직사각형이 왼쪽에 있는 경우
            }
            // 새로운 직사각형이 rect 직사각형 중앙을 지나는 경우
            else if(newRect[0] >= rect[0] && newRect[1] <= rect[1] && newRect[2] <= rect[2] && newRect[3] >= rect[3]) { // 세로로 지나는 경우
                listRect.add(new int[]{rect[0], rect[1], newRect[0], rect[3]});
                listRect.add(new int[]{newRect[2], rect[1], rect[2], rect[3]});
            } else if(newRect[0] <= rect[0] && newRect[1] >= rect[1] && newRect[2] >= rect[2] && newRect[3] <= rect[3]) { // 가로로 지나는 경우
                listRect.add(new int[]{rect[0], rect[1], rect[2], newRect[1]});
                listRect.add(new int[]{rect[0], newRect[3], rect[2], rect[3]});
            }
            // rect 직사각형이 네 개로 나눠지는 경우 (= rect 직사각형에 새로운 직사각형이 포함되는 경우)
            else if(newRect[0] >= rect[0] && newRect[1] >= rect[1] && newRect[2] <= rect[2] && newRect[3] <= rect[3]) {
                listRect.add(new int[]{rect[0], newRect[3], newRect[2], rect[3]});
                listRect.add(new int[]{newRect[2], newRect[1], rect[2], rect[3]});
                listRect.add(new int[]{newRect[0], rect[1], rect[2], newRect[1]});
                listRect.add(new int[]{rect[0], rect[1], newRect[0], newRect[3]});
            }
            else {
                boolean left = (rect[0] <= newRect[2] && newRect[2] <= rect[2]); // 새로운 직사각형이 rect 직사각형 왼쪽과 겹치는 경우
                boolean top = (rect[1] <= newRect[1] && newRect[1] <= rect[3]); // 새로운 직사각형이 rect 직사각형 위쪽과 겹치는 경우
                boolean right = (rect[0] <= newRect[0] && newRect[0] <= rect[2]); // 새로운 직사각형이 rect 직사각형 오른쪽과 겹치는 경우
                boolean bottom = (rect[1] <= newRect[3] && newRect[3] <= rect[3]); // 새로운 직사각형이 rect 직사각형 아래쪽과 겹치는 경우

                if(left) {
                    if(top) { // rect 직사각형의 좌측 상단과 겹치는 경우
                        listRect.add(new int[]{rect[0], rect[1], newRect[2], newRect[1]});
                        listRect.add(new int[]{newRect[2], rect[1], rect[2], rect[3]});
                    } else if(bottom) { // rect 직사각형의 좌측 하단과 겹치는 경우
                        listRect.add(new int[]{rect[0], newRect[3], newRect[2], rect[3]});
                        listRect.add(new int[]{newRect[2], rect[1], rect[2], rect[3]});
                    } else { // rect 직사각형의 좌측 중앙만 겹치는 경우
                        listRect.add(new int[]{rect[0], newRect[3], newRect[2], rect[3]});
                        listRect.add(new int[]{rect[0], rect[1], newRect[2], newRect[1]});
                        listRect.add(new int[]{newRect[2], rect[1], rect[2], rect[3]});
                    }
                } else if(right) { // rect 직사각형의 우측 상단과 겹치는 경우
                    if(top) {
                        listRect.add(new int[]{rect[0], rect[1], newRect[0], rect[3]});
                        listRect.add(new int[]{newRect[0], rect[1], rect[2], newRect[1]});
                    } else if(bottom) { // rect 직사각형의 우측 하단과 겹치는 경우
                        listRect.add(new int[]{rect[0], rect[1], newRect[0], rect[3]});
                        listRect.add(new int[]{newRect[0], newRect[3], rect[2], rect[3]});
                    } else { // rect 직사각형의 우측 중앙만 겹치는 경우
                        listRect.add(new int[]{newRect[0], newRect[3], rect[2], rect[3]});
                        listRect.add(new int[]{newRect[0], rect[1], rect[2], newRect[1]});
                        listRect.add(new int[]{rect[0], rect[1], newRect[0], rect[3]});
                    }
                } else if(top) { // rect 직사각형의 상단 중앙만 겹치는 경우
                    listRect.add(new int[]{rect[0], newRect[1], newRect[0], rect[3]});
                    listRect.add(new int[]{newRect[2], newRect[1], rect[2], rect[3]});
                    listRect.add(new int[]{rect[0], rect[1], rect[2], newRect[1]});
                } else if(bottom) { // rect 직사각형의 하단 중앙만 겹치는 경우
                    listRect.add(new int[]{rect[0], rect[1], newRect[0], newRect[3]});
                    listRect.add(new int[]{newRect[2], rect[1], rect[2], newRect[3]});
                    listRect.add(new int[]{rect[0], newRect[3], rect[2], rect[3]});
                }
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