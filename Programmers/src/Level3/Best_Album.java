package Level3;

import java.util.*;

// Hash 해시

public class Best_Album { // 베스트앨범
    public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};

        HashMap<String, Integer> map = new HashMap<>(); // 장르별 재생횟수
        HashMap<String, HashMap<Integer, Integer>> music = new HashMap<>();
        for(int i = 0; i < plays.length; i++) {
            if()
        }
        for(int i = 0; i < genres.length; i++)
            map.put(genres[i], map.getOrDefault(genres[i], 0)+ plays[i]);

        List<String> keySet = new ArrayList<>(map.keySet());
        Collections.sort(keySet, (a, b) -> map.get(b) - map.get(a));

        return answer;
    }
}
