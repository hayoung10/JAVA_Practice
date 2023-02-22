package Level3;

import java.util.*;

// Binary Tree 이진 트리

public class PathFinding_Game { // 길 찾기 게임
    private int[][] answer;
    private int idx;

    public int[][] solution(int[][] nodeinfo) {
        answer = new int[2][nodeinfo.length];

        ArrayList<Node> nodeList = new ArrayList<>();
        for(int i = 0; i < nodeinfo.length; i++)
            nodeList.add(new Node(nodeinfo[i][0], nodeinfo[i][1], i + 1, null, null));
        Collections.sort(nodeList);

        Node root = nodeList.get(0);
        for(int i = 1; i < nodeList.size(); i++)
            makeTree(root, nodeList.get(i));

        idx = 0;
        preorder(root); // 전위 순회
        idx = 0;
        postorder(root); // 후위 순회

        return answer;
    }

    private void makeTree(Node parent, Node child) { // 트리 만들기
        if(parent.x < child.x) {
            if(parent.right == null) parent.right = child;
            else makeTree(parent.right, child);
        } else {
            if(parent.left == null) parent.left = child;
            else makeTree(parent.left, child);
        }
    }

    private void preorder(Node node) { // 전위 순회
        if(node == null) return;
        answer[0][idx++] = node.data;
        preorder(node.left);
        preorder(node.right);
    }

    private void postorder(Node node) { // 후위 순회
        if(node == null) return;
        postorder(node.left);
        postorder(node.right);
        answer[1][idx++] = node.data;
    }

    class Node implements Comparable<Node> {
        int x, y; // 좌표
        int data; // 노드 번호
        Node left; // 왼쪽 자식
        Node right; // 오른쪽 자식

        public Node(int x, int y, int data, Node left, Node right) {
            this.x = x;
            this.y = y;
            this.data = data;
            this.left = left;
            this.right = right;
        }

        @Override
        public int compareTo(Node o) { // 트리의 루트부터 좌측에서 우측으로 내려오기 위함
            if(this.y == o.y)
                return this.x - o.x; // 작은 x좌표

            return o.y - this.y; // 큰 y좌표
        }
    }
}
