package Gold5;

import java.io.*;
import java.util.*;

public class G1717 { // 집합의 표현
    static int[] parent;
    
    private static int findParent(int a) {
        if(parent[a] == a) return a;
        return parent[a] = findParent(parent[a]);
    }
    
    private static void unionParent(int a, int b) {
        int p_a = findParent(a);
        int p_b = findParent(b);

        if(p_a != p_b)
            parent[p_a] = p_b;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 초기화
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        parent = new int[n + 1];
        for(int i = 0; i <= n; i++) parent[i] = i;

        // 연산 수행
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int ops = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(ops == 1) {
                if(findParent(a) == findParent(b))
                    System.out.println("YES");
                else
                    System.out.println("NO");
            } else {
                unionParent(a, b);
            }
        }
    }
}