package Gold4;

import java.io.*;
import java.util.*;

public class G2044 { // windows
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        String[] info = new String[N]; // screen-shot을 나타내 주는 정보
        for(int i = 0; i < M; i++) info[i] = br.readLine();

        // window 정보 정리
        ArrayList<Window> windows = new ArrayList<>();
        for(int i = 0; i < M; i++) {
            String line = info[i];

            if(line.contains("-|")) {
                String title = line.replaceAll("[^a-z]", "");
                int s = line.indexOf("+");
                int e = line.indexOf("+", s + 1);
                int n = e - s + 1;
                int m = 0;
                for(int j = i + 1; j < M; j++) {
                    if(info[j].charAt(s) == '+') {
                        m = (j - i + 1);
                        break;
                    }
                }

                windows.add(new Window(title, m, n));
            }
        }
        Collections.sort(windows); // window 정렬

        // cascade mode로 정리
        char[][] answer = new char[M][N];
        for(int i = 0 ; i < answer.length; i++)
            Arrays.fill(answer[i], '.');
        for(int k = 0; k < windows.size(); k++) {
            Window window = windows.get(k);
            int x1 = k, y1 = k;
            int x2 = window.m + x1 - 1, y2 = window.n + y1 - 1;

            answer[x1][y1] = answer[x1][y2] = answer[x2][y1] = answer[x2][y2] = '+'; // 모서리
            for(int i = x1 + 1; i < x2; i++) {
                answer[i][y1] = answer[i][y2] = '|'; // 세로
                Arrays.fill(answer[i], y1 + 1, y2, '.'); // 창 안
            }
            for(int j = y1 + 1; j < y2; j++) answer[x1][j] = answer[x2][j] = '-'; // 가로
            // 제목
            String str = "|" + window.title + "|";
            int tmp = (window.n - str.length()) / 2;
            for(int i = 0; i < str.length(); i++)
                answer[x1][y1 + tmp + i] = str.charAt(i);
        }

        // 결과 출력
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < M; i++) {
            for(int j = 0; j < N; j++)
                sb.append(answer[i][j]);
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static class Window implements Comparable<Window> {
        String title;
        int m, n;

        public Window(String title, int m, int n) {
            this.title = title;
            this.m = m;
            this.n = n;
        }

        @Override
        public int compareTo(Window o) {
            return this.title.compareTo(o.title);
        }
    }
}