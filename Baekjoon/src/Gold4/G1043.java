package Gold4;

import java.io.*;
import java.util.*;

public class G1043 { // 거짓말
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
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 초기화
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        boolean[] true_ppl = new boolean[N + 1]; // 진실을 아는 사람들
        parent = new int[N + 1];
        for(int i = 0; i <= N; i++) parent[i] = i;

        st = new StringTokenizer(br.readLine());
        int known_ppl = Integer.parseInt(st.nextToken()); // 진실은 아는 사람의 수
        for(int i = 0; i < known_ppl; i++)
            true_ppl[Integer.parseInt(st.nextToken())] = true;

        ArrayList<Integer>[] people = new ArrayList[M]; // 사람들의 관계 정보
        for(int i = 0; i < M; i++) people[i] = new ArrayList<>();
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int party_ppl = Integer.parseInt(st.nextToken()); // 해당 파티에 오는 사람의 수

            int parent = Integer.parseInt(st.nextToken());
            people[i].add(parent);
            for(int j = 1; j < party_ppl; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                people[i].add(tmp);
                unionParent(parent, tmp); // 같은 파티에 있는 사람들 연결
                parent = tmp;
            }
        }

        for(int i = 1; i < true_ppl.length; i++) // 함께 진실을 아는 사람들
            if(true_ppl[i])
                true_ppl[findParent(i)] = true;

        int answer = 0;
        for(int i = 0; i < M; i++) {
            if(people[i].size() == 0) continue;
            int parent = findParent(people[i].get(0));
            if(!true_ppl[parent]) answer++; // 진실을 아는 사람들과 파티를 함께 하지 않음
        }
        System.out.println(answer);
    }
}