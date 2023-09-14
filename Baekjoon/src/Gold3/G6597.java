package Gold3;

import java.io.*;
import java.util.*;

public class G6597 { // 트리 복구
    static String preorder, inorder;
    static Map<Character, Integer> map;
    static int preIdx;
    static StringBuilder sb = new StringBuilder();

    private static Node makeTree(int start, int end) { // 바이너리 트리 만들기
        if(start > end) return null;

        char data = preorder.charAt(preIdx++);
        Node node = new Node(data);
        if(start == end) return node; // 자식 노드가 없는 경우

        int inIdx = map.get(data);
        node.left = makeTree(start, inIdx - 1); // 왼쪽 자식 노드
        node.right = makeTree(inIdx + 1, end); // 오른쪽 자식 노드

        return node;
    }

    private static void postOrder(Node node) { // 후위 순회
        if(node == null) return;
        postOrder(node.left);
        postOrder(node.right);
        sb.append(node.data);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String input;
        try {
            while (true) {
                input = br.readLine();
                if (input.split(" ").length == 0) break;

                st = new StringTokenizer(input);
                preorder = st.nextToken(); // 전위 순회
                inorder = st.nextToken(); // 중위 순회

                // 트리 만들기
                preIdx = 0;
                map = new HashMap<>();
                for(int i = 0; i < inorder.length(); i++) map.put(inorder.charAt(i), i);
                Node root = makeTree(0, inorder.length() - 1);

                // 후위 선회
                postOrder(root);
                sb.append("\n");
            }
        } catch (Exception e) {

        }
        System.out.println(sb.toString());
    }

    static class Node {
        char data;
        Node left, right;

        public Node(char data) {
            this.data = data;
            left = right = null;
        }
    }
}