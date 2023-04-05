package Level3;

import java.util.*;

// DFS 깊이 우선 탐색

public class Lighthouse { // 등대
    int result = 0;
    List<List<Integer>> tree = new ArrayList<>();

    public int solution(int n, int[][] lighthouse) {
        // 초기화
        for(int i = 0; i <= n; i++)
            tree.add(new ArrayList<>());
        for(int i = 0; i < lighthouse.length; i++) {
            tree.get(lighthouse[i][0]).add(lighthouse[i][1]);
            tree.get(lighthouse[i][1]).add(lighthouse[i][0]);
        }
        dfs(1, 0);

        return result;
    }

    private int dfs(int curNode, int preNode) {
        // 리프 노드인 경우
        if(tree.get(curNode).size() == 1 && tree.get(curNode).get(0) == preNode) return 1; // 등대를 키지 않음

        int cnt = 0;
        for(int i = 0; i < tree.get(curNode).size(); i++) {
            int nextNode = tree.get(curNode).get(i);
            if(nextNode == preNode) continue;

            cnt += dfs(nextNode, curNode);
        }

        // 해당 노드의 등대를 키지 않아도 되는 경우
        if(cnt == 0) return 1;

        // 해당 노드의 등대를 켜야 하는 경우
        result++;
        return 0;
    }
}
