package Level3;

import java.util.*;

// Greedy 탐욕법

public class Nplus1_CardGame { // n + 1 카드 게임
    public int solution(int coin, int[] cards) {
        int answer = 1;

        // 초기화
        int n = cards.length;
        Set<Integer> curCard = new HashSet<>(); // 처음에 카드 뭉치에서 뽑은 카드 (n/3)장
        Set<Integer> newCard = new HashSet<>(); // 라운드에서 뽑은 카드
        for(int i = 0; i < n / 3; i++) curCard.add(cards[i]);

        for(int i = n / 3; i < n; i += 2) {
            newCard.add(cards[i]);
            newCard.add(cards[i + 1]);

            // 처음 가지고 있는 카드에서 해결할 수 있는지 판단
            if(matchCards(curCard, curCard, n + 1)) {
                // 코인 사용 X
            }

            // 처음 가지고 있는 카드와 추가 카드 1장을 이용해서 해결할 수 있는지 판단
            else if(coin >= 1 && matchCards(curCard, newCard, n + 1)) {
                coin -= 1; // 코인 1개 사용
            }

            // 추가 카드 2장을 이용해서 해결할 수 있는지 판단
            else if(coin >= 2 && matchCards(newCard, newCard, n + 1)) {
                coin -= 2; // 코인 2개 사용
            }

            // 모두 불가능한 경우
            else { break; } // 게임 종료

            answer++;
        }

        return answer;
    }

    private boolean matchCards(Set<Integer> cards1, Set<Integer> cards2, int target) { //
        for(int num : cards1) {
            if(cards2.contains(target - num)) {
                cards1.remove(num);
                cards2.remove(target - num);
                return true;
            }
        }

        return false;
    }
}
