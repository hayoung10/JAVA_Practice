package Level3;

import java.util.*;

// DFS 깊이 우선 탐색

public class Sheep_n_Wolf { // 양과 늑대
    int[] info;
    ArrayList<Integer>[] tree;
    int maxSheepCnt = 0;

    public int solution(int[] info, int[][] edges) {
        this.info = info;

        // tree 만들기
        tree = new ArrayList[info.length];
        for(int[] e : edges) {
            int parent = e[0];
            if(tree[parent] == null) tree[parent] = new ArrayList<>();
            tree[parent].add(e[1]);
        }

        List<Integer> list = new ArrayList<>(); // 방문할 노드들을 담은 리스트
        list.add(0);
        DFS(0, 0, 0, list);

        return maxSheepCnt;
    }

    private void DFS(int idx, int sheep, int wolves, List<Integer> list) {
        if(info[idx] == 0) sheep++;
        else wolves++;

        if(sheep <= wolves) return; // 늑대가 모든 양을 잡아먹어 버린 경우

        maxSheepCnt = Math.max(maxSheepCnt, sheep);

        List<Integer> nextList = new ArrayList<>();
        nextList.addAll(list);
        nextList.remove(Integer.valueOf(idx)); // 현 노드는 방문함 -> 리스트에서 제거
        if(tree[idx] != null)
            for(int node : tree[idx]) nextList.add(node);

        // 순회
        for(int node : nextList)
            DFS(node, sheep, wolves, nextList);
    }
}
