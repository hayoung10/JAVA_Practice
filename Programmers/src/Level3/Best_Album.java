package Level3;

import java.util.*;

// Hash 해시

public class Best_Album { // 베스트앨범
    public int[] solution(String[] genres, int[] plays) {
        ArrayList<Integer> answer = new ArrayList<>();

        HashMap<String, Integer> genres_plays = new HashMap<>(); // (장르, 총 재생 횟수)
        HashMap<String, HashMap<Integer, Integer>> music_plays = new HashMap<>(); // (장르, (고유 번호, 재생 횟수))

        // 초기화
        for(int i = 0; i < plays.length; i++) {
            if(!genres_plays.containsKey(genres[i])) {
                HashMap<Integer, Integer> map = new HashMap<>();
                map.put(i, plays[i]);
                music_plays.put(genres[i], map);
            } else {
                music_plays.get(genres[i]).put(i, plays[i]);
            }
            genres_plays.put(genres[i], genres_plays.getOrDefault(genres[i], 0) + plays[i]);
        }

        List<String> genre_keySet = new ArrayList(genres_plays.keySet());
        Collections.sort(genre_keySet, (a, b) -> genres_plays.get(b) - genres_plays.get(a)); // 내림차순 정렬

        for(String genre : genre_keySet) {
            HashMap<Integer, Integer> map = music_plays.get(genre);
            List<Integer> music_keySet = new ArrayList(map.keySet());
            Collections.sort(music_keySet, (a, b) -> map.get(b) - map.get(a)); // 내림차순 정렬

            answer.add(music_keySet.get(0));
            if(music_keySet.size() > 1)
                answer.add(music_keySet.get(1));
        }

        return answer.stream().mapToInt(Integer::intValue).toArray(); // ArrayList -> Array
    }
}
