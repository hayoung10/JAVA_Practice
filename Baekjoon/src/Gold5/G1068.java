package Gold5;

import java.io.*;
import java.util.*;

public class G1068 { // 트리
    static int N, dnode;
    static ArrayList<Integer>[] tree;
    static int answer = 0;

    private static void dfs(int node, int parent) {
        if(node == dnode) {
            if(tree[parent].size() == 1) // 현재 노드가 제거되어 부모 노드가 리프 노드가 된 경우
                answer++;
            return;
        }

        if(tree[node].size() == 0) { // 현재 노드가 리프 노드인 경우
            answer++;
            return;
        }

        for(Integer n : tree[node])
            dfs(n, node);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 초기화
        N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N];
        for(int i = 0; i < N; i++)
            tree[i] = new ArrayList<>();

        int root = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            int node = Integer.parseInt(st.nextToken());

            if(node == -1) root = i;
            else tree[node].add(i);
        }
        dnode = Integer.parseInt(br.readLine()); // 지울 노드의 번호

        if(root != dnode)
            dfs(root, -1);

        System.out.println(answer);
    }
}
