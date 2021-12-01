package Lyft电面;

import java.util.Stack;

public class ConvertBinarySearchTreeToSortedDoublyLinkedList {

    public static void main(String[] args) {
        ConvertBinarySearchTreeToSortedDoublyLinkedList t = new ConvertBinarySearchTreeToSortedDoublyLinkedList();
        t.test();
    }

    public void test() {
        Node root = new Node(4);
        root.left = new Node(2);
        root.right = new Node(5);
        root.left.left = new Node(1);
        root.left.right = new Node(3);
        treeToDoublyList(root);
    }

    public Node treeToDoublyList(Node root) {
        if (root == null) return root;
        Stack<Node> stack = new Stack<>();
        Node head = null;
        Node prev = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (head == null) head = root;
            if (prev != null) {
                prev.right = root;
                root.left = prev;
            }
            prev = root;
            root = root.right;
        }
        head.left = prev;
        prev.right = head;
        return head;
    }

    class Node {
        int val;
        Node left;
        Node right;
        Node() {}
        Node(int val) {
            this.val = val;
        }
    }
}
