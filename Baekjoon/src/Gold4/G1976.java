package Gold4;

import java.io.*;
import java.util.*;

public class G1976 { // 여행 가자
    static int[] parent;
    
    private static int findParent(int a) {
        if(parent[a] == a) return a;
        return parent[a] = findParent(parent[a]);
    }
    
    private static void unionParent(int a, int b) {
        int p_a = findParent(a);
        int p_b = findParent(b);
        
        if(p_a != p_b) parent[p_a] = p_b;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 초기화
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        parent = new int[N + 1];
        for(int i = 1; i <= N; i++) parent[i] = i;

        StringTokenizer st;
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++) {
                if(st.nextToken().equals("1")) // 연결된 경우
                    unionParent(i, j);
            }
        }

        // 여행 계획이 가능한지 판별
        st = new StringTokenizer(br.readLine());
        boolean flag = true;
        int curParent = findParent(Integer.parseInt(st.nextToken()));
        for(int i = 1; i < M; i++) {
            int nextParent = findParent(Integer.parseInt(st.nextToken()));
            if(curParent != nextParent) { // 불가능한 경우
                System.out.println("NO");
                flag = false;
                break;
            }
        }
        if(flag) System.out.println("YES"); // 가능한 경우
    }
}