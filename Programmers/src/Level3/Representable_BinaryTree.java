package Level3;

// Divide and Conquer 분할 정복

public class Representable_BinaryTree { // 표현 가능한 이진 트리
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];

        for(int i = 0; i < numbers.length; i++) {
            String binaryStr = makeFullBinary(Long.toBinaryString(numbers[i]));

            if(checkBinaryTree(binaryStr)) answer[i] = 1; // 이진트리로 표현할 수 있는 경우
            else answer[i] = 0; // 표현할 수 없는 경우
        }

        return answer;
    }

    private String makeFullBinary(String binaryStr) { // full binary tree 만들기
        int cnt = 1;
        int level = 1;
        while(binaryStr.length() > cnt) {
            level *= 2;
            cnt += level;
        }

        int offset = cnt - binaryStr.length(); // 추가로 필요한 0의 개수
        return "0".repeat(offset) + binaryStr;
    }

    private boolean checkBinaryTree(String binaryStr) { // binary tree가 되는지 확인하기
        if(binaryStr.length() == 0) return true; // 리프 노드까지 도달한 경우

        int root = binaryStr.length() / 2;
        String leftSubTree = binaryStr.substring(0, root);
        String rightSubTree = binaryStr.substring(root + 1);

        // 루트 노드가 0이면 서브 트리도 모두 0이어야 함
        if(binaryStr.charAt(root) == '0')
            return checkZeroTree(leftSubTree) && checkZeroTree(rightSubTree);

        return checkBinaryTree(leftSubTree) && checkBinaryTree(rightSubTree); // 서브트리도 검사
    }

    private boolean checkZeroTree(String binaryStr) { // 모두 0인 트리인지 확인
        if(binaryStr.length() == 0) return true; // 리프 노드까지 도달한 경우

        int root = binaryStr.length() / 2;
        if(binaryStr.charAt(root) == '1') return false;

        String leftSubTree = binaryStr.substring(0, root);
        String rightSubTree = binaryStr.substring(root + 1);

        return checkZeroTree(leftSubTree) && checkZeroTree(rightSubTree); // 서브트리도 검사
    }
}
