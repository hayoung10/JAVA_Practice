package Gold5;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class G1092 { // 배
    static int[] crane;
    static List<Integer> box = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        // 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Integer> crane = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        int M = Integer.parseInt(br.readLine());
        List<Integer> box = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());

        // 내림차순으로 정렬
        Collections.sort(crane, Collections.reverseOrder());
        Collections.sort(box, Collections.reverseOrder());
        if(crane.get(0) < box.get(0)) { // 가장 무거운 무게를 드는 크레인이 가장 무거운 박스를 옮길 수 없는 경우
            System.out.println(-1); return;
        }

        int answer = 0;
        while(!box.isEmpty()) {
            int idx = 0;
            for(int i = 0; i < N; ) {
                if(crane.get(i) >= box.get(idx)) { // 크레인이 박스를 옮길 수 있는 경우
                    box.remove(idx); i++;
                } else idx++;

                if(idx == box.size()) break;
            }
            answer++;
        }
        System.out.println(answer);
    }
}