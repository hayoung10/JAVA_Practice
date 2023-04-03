package Level3;

import java.util.*;

// Union-Find 유니온 파인드

public class Merge_Tables { // 표 병합
    int[] parent = new int[2501];
    String[] values = new String[2501];

    public String[] solution(String[] commands) {
        // 초기화
        for(int i = 1; i <= 2500; i++) {
            parent[i] = i;
            values[i] = "";
        }

        List<String> result = new ArrayList<>();
        for(String command : commands) {
            StringTokenizer st = new StringTokenizer(command);
            String type = st.nextToken();

            if(type.equals("UPDATE")) {
                if(st.countTokens() == 3) { // UPDATE r c value
                    int r = Integer.parseInt(st.nextToken());
                    int c = Integer.parseInt(st.nextToken());
                    String value = st.nextToken();
                    int num = convertNum(r, c);
                    values[find(num)] = value;
                } else { // UPDATE value1 value2
                    String value1 = st.nextToken();
                    String value2 = st.nextToken();
                    for(int i = 0; i <= 2500; i++)
                        if(value1.equals(values[i]))
                            values[i] = value2; // value1 -> value2 로 변경
                }
            } else if(type.equals("MERGE")) { // MERGE r1 c1 r2 c2
                int r1 = Integer.parseInt(st.nextToken());
                int c1 = Integer.parseInt(st.nextToken());
                int r2 = Integer.parseInt(st.nextToken());
                int c2 = Integer.parseInt(st.nextToken());

                int num1 = convertNum(r1, c1);
                int num2 = convertNum(r2, c2);
                int root1 = find(num1);
                int root2 = find(num2);

                if(root1 == root2) continue; // 두 위치의 셀이 같은 셀일 경우

                String rootValue = values[root1].isBlank() ? values[root2] : values[root1];
                values[root1] = rootValue;
                values[root2] = "";
                union(root1, root2); // 병합
            } else if(type.equals("UNMERGE")) { // UNMERGE r c
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                int num = convertNum(r, c);
                int root = find(num);

                String rootValue = values[root];
                values[root] = "";
                values[num] = rootValue;

                // 병합 해제
                List<Integer> deleteList = new ArrayList<>();
                for(int i = 1; i <= 2500; i++) {
                    if(find(i) == root)
                        deleteList.add(i); // root를 찾아가는 경로가 끊기지 않게 삭제할 부분을 저장
                }
                for(Integer t : deleteList) parent[t] = t;
            } else if(type.equals("PRINT")) { // PRINT r c
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                int num = convertNum(r, c);
                int root = find(num);

                if(values[root].isBlank()) result.add("EMPTY");
                else result.add(values[root]);
            }
        }

        return result.toArray(new String[0]);
    }

    private int find(int x) {
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    private void union(int x, int y) {
        x = find(x);
        y = find(y);
        if(x == y) return;
        parent[y] = x;
    }

    private int convertNum(int r, int c) {
        return 50 * (r - 1) + c;
    }
}
