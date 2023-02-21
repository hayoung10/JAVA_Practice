package Level3;

import java.util.*;

// DFS 깊이 우선 탐색

public class Bad_User { // 불량 사용자
    public String[] userIds;
    public String[] bannedIds;
    public HashSet<HashSet<String>> result = new HashSet<>();

    public int solution(String[] user_id, String[] banned_id) {
        userIds = user_id;
        bannedIds = banned_id;

        DFS(new HashSet<>(), 0);

        return result.size();
    }

    private void DFS(HashSet<String> set, int depth) {
        if(depth == bannedIds.length) {
            result.add(set);
            return;
        }

        for(int i = 0; i < userIds.length; i++) {
            if(set.contains(userIds[i])) continue;

            if(check(userIds[i], bannedIds[depth])) {
                set.add(userIds[i]);
                DFS(new HashSet<>(set), depth + 1);
                set.remove(userIds[i]); // dfs를 빠져나오면 다시 리셋
            }
        }
    }

    private boolean check(String id, String bannedId) { // 불량 사용자에 매핑될 수 있는 아이디인지 확인
        if(id.length() != bannedId.length()) return false;

        for(int i = 0; i < bannedId.length(); i++) {
            if(bannedId.charAt(i) != '*' && bannedId.charAt(i) != id.charAt(i))
                return false;
        }

        return true;
    }
}
