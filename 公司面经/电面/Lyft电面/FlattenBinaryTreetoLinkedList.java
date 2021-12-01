package Lyft电面;

import java.util.Stack;

public class FlattenBinaryTreetoLinkedList {

    // 利用前序
//    public void flatten(TreeNode root) {
//        Stack<TreeNode> stack = new Stack<>();
//        if (root != null) stack.push(root);
//        TreeNode prev = null;
//        while (!stack.isEmpty()) {
//            TreeNode node = stack.pop();
//            if (prev != null) {
//                prev.left = null;
//                prev.right = node;
//            }
//            prev = node;
//            if (node.right != null) stack.push(node.right);
//            if (node.left != null) stack.push(node.left);
//        }
//    }
}
