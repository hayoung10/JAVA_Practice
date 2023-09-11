package Gold3;

import java.io.*;
import java.util.*;

public class G14956 { // Philosopher’s Walk
    private static int[] divide(int n, int m) { // 분할 정복 이용
        int[] loc = new int[2]; // {x, y}

        if(n == 2) {
            if(m == 0) { loc[0] = 1; loc[1] = 1; }
            else if(m == 1) { loc[0] = 1; loc[1] = 2; }
            else if(m == 2) { loc[0] = 2; loc[1] = 2; }
            else if(m == 3) { loc[0] = 2; loc[1] = 1; }
            return loc;
        }

        n /= 2;
        int area = m / (n * n);
        m %= (n * n);

        loc = divide(n, m);
        if(area == 0) { // 좌측하단
            // swap (시계 방향으로 90도 회전하므로)
            int tmp = loc[0];
            loc[0] = loc[1];
            loc[1] = tmp;
        } else if(area == 1) { // 좌측상단
            loc[1] += n;
        } else if(area == 2) { // 우측상단
            loc[0] += n;
            loc[1] += n;
        } else if(area == 3) { // 우측하단
            loc = new int[]{2 * n - loc[1] + 1, n - loc[0] + 1}; // (반시계 방향으로 90도 회전하므로)
        }

        return loc;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken()); // 걸음수

        int[] loc = divide(n, m - 1);
        System.out.println(loc[0] + " " + loc[1]);
    }
}