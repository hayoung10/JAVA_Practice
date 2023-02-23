package Level3;

import java.util.*;

public class Jewelry_Shopping { // 보석 쇼핑
    public int[] solution(String[] gems) {
        int[] answer = {};

        HashSet<String> type = new HashSet<>();
        for(int i = 0; i < gems.length; i++)
            type.add(gems[i]);

        int total = type.size(); // 보석 종류
        int dis = gems.length + 1; // 구간 길이 (최대)
        int start = 0, end = 0;

        HashMap<String, Integer> map = new HashMap<>();
        while(true) { // 짧은 구간 찾기
            if(map.size() == total) {
                if(end - start < dis) {
                    answer = new int[]{start + 1, end};
                    dis = end - start;
                }

                if(map.get(gems[start]) > 1) map.put(gems[start], map.get(gems[start]) - 1);
                else map.remove(gems[start]);
                start++;
            } else if(end == gems.length) {
                break;
            } else if(map.size() < total) {
                map.put(gems[end], map.getOrDefault(gems[end], 0) + 1);
                end++;
            }
        }

        return answer;
    }
}
