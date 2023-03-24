package Level4;

import java.util.*;

// Trie

public class Autocomplete { // 자동완성
    public int solution(String[] words) {
        int answer = 0;

        Arrays.sort(words); // 사전식 정렬

        Trie root = new Trie();
        for(String w : words)
            root.insert(w, 0);

        for(String w : words)
            answer += root.find(w, 0);

        return answer;
    }

    class Trie {
        int count; // 자식 수
        Trie[] child;

        Trie() {
            count = 0;
            child = new Trie[26];
        }

        void insert(String word, int idx) {
            count++;
            if(idx == word.length()) return;

            int cur = word.charAt(idx) - 'a';
            if(child[cur] == null)
                child[cur] = new Trie();
            child[cur].insert(word, idx + 1);
        }

        int find(String word, int idx) {
            if(this.count == 1 || idx == word.length()) return idx; // 입력해야 할 문자의 수

            int cur = word.charAt(idx) - 'a';
            if(child[cur] == null) return 0;
            return child[cur].find(word, idx + 1);
        }
    }
}
