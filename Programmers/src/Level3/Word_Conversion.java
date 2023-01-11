package Level3;

// DFS 깊이 우선 탐색

public class Word_Conversion { // 단어 변환
    private int answer = 50;

    public int solution(String begin, String target, String[] words) {
        DFS(begin, target, words, new boolean[words.length], 0);

        if(answer == 50) return 0; // 변환할 수 없는 경우
        return answer;
    }

    private void DFS(String begin, String target, String[] words, boolean[] visited, int cnt) {
        if(begin.equals(target)) {
            answer = Math.min(answer, cnt);
            return;
        }

        int len = begin.length();
        for(int i = 0; i < words.length; i++) {
            int digits = 0;
            if(!visited[i])
                for(int j = 0; j < len; j++)
                    if(begin.charAt(j) == words[i].charAt(j)) digits++;

            if(digits == len - 1) { // 변환할 수 있는 경우
                visited[i] = true;
                DFS(words[i], target, words, visited, cnt + 1);
                visited[i] = false;
            }
        }
    }
}
