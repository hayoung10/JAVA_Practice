package Level4;

import java.util.*;

public class Drop_123 { // 1,2,3 떨어트리기
    public int[] solution(int[][] edges, int[] target) {
        // 트리 생성
        int N = target.length;
        ArrayList<Integer>[] tree = new ArrayList[N];
        for(int i = 0; i < N; i++) tree[i] = new ArrayList<>();
        for(int i = 0; i < edges.length; i++) tree[edges[i][0] - 1].add(edges[i][1] - 1);
        for(int i = 0; i < N; i++) Collections.sort(tree[i]); // 자식 노드들을 오름차순으로 정렬

        int cases = 0;
        int[] pass = new int[N]; // 각 노드를 지나간 횟수
        int[] cnt = new int[N]; // 각 노드에 쌓인 숫자의 수
        boolean[] visited = new boolean[N];
        for(int i = 0; i < N; i++) if(tree[i].isEmpty() && target[i] > 0) cases++;

        List<Integer> leafNodeOrder = new ArrayList<>(); // 리프 노드를 방문한 순서
        while(cases > 0) {
            int node = 0;
            while (!tree[node].isEmpty())
                node = tree[node].get(pass[node]++ % tree[node].size()); // (현재 노드를 지난 횟수 % 자식 노드의 개수)번째 자식 노드와 연결된 간선 선택

            cnt[node]++;
            leafNodeOrder.add(node);

            if(cnt[node] > target[node]) return new int[]{-1}; // target대로 숫자의 합을 만들 수 없는 경우

            if(!visited[node] && target[node] <= 3 * cnt[node]) { // target 값을 만들 수 있는 경우
                visited[node] = true;
                cases--;
            }
        }

        List<Integer> answer = new ArrayList<>();
        for(int node : leafNodeOrder) { // 1,2,3을 사전 순으로 대입
            cnt[node]--;
            for(int num = 1; num <= 3; num++) {
                if(cnt[node] <= target[node] - num && target[num] - num <= 3 * cnt[node]) {
                    answer.add(num);
                    target[node] -= num;
                    break;
                }
            }
        }
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}