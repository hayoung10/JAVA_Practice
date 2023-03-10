package Level4;

// Trie 트라이

public class Lyrics_Search { // 가사 검색
    Trie[] root = new Trie[10001]; // 접미사
    Trie[] rev_root = new Trie[10001]; // 접두사 -> 뒤집어서 검색

    public int[] solution(String[] words, String[] queries) {
        int[] answer = new int[queries.length];

        for(String w : words) { // Trie 생성
            int len = w.length();

            if(root[len] == null)
                root[len] = new Trie();
            root[len].insert(w, 0);

            String rw = (new StringBuffer(w)).reverse().toString();
            if(rev_root[len] == null)
                rev_root[len] = new Trie();
            rev_root[len].insert(rw, 0);
        }

        int idx = 0;
        for(String q : queries) {
            int len = q.length();

            if(q.charAt(len - 1) == '?') {
                if(root[len] == null) { idx++; continue; }
                else answer[idx] = root[len].find(q, 0);
            } else {
                String rq = (new StringBuffer(q)).reverse().toString();
                if(rev_root[len] == null) { idx++; continue; }
                else answer[idx] = rev_root[len].find(rq, 0);
            }
            idx++;
        }

        return answer;
    }

    class Trie { // Trie Node
        int count;
        Trie[] child;

        Trie() {
            count = 0;
            child = new Trie[26]; // 알파벳
        }

        void insert(String word, int idx) {
            count++;
            if(idx == word.length()) return;

            int cur = word.charAt(idx) - 'a';
            if(child[cur] == null)
                child[cur] = new Trie();
            child[cur].insert(word, idx + 1);
        }
        int find(String query, int idx) {
            if(query.charAt(idx) == '?') return count;

            int cur = query.charAt(idx) - 'a';
            if(child[cur] == null) return  0; // 매치되는 단어가 없음
            return child[cur].find(query, idx + 1);
        }
    }
}