import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PreInPostOrder {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode (){}
        TreeNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {

    }

    // Recursion 非常简单
    public List<Integer> preorderTraversal_recursion(TreeNode root) {
        List<Integer> res= new ArrayList<>();
        helper(root, res);
        return res;
    }

    private void helper(TreeNode root, List<Integer> res) {
        if (root == null) return;
        res.add(root.val);           // 前中后序取决于这一行的位置
        helper(root.left, res);
        helper(root.right, res);
    }

    // 非recursion - stack
    // 前序
    public List<Integer> preorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> res = new ArrayList<>();
        if (root != null) stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            res.add(node.val);
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }
        return res;
    }

    // 中序  -- 重要！ 常用于BST！！！！！
    public List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> res = new ArrayList<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            res.add(root.val);
            root = root.right;
        }
        return res;
    }

    // 后序
    public List<Integer> postorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> res = new ArrayList<>();
        if (root != null) stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            res.add(0, node.val);
            if (node.left != null) stack.push(node.left);
            if (node.right != null) stack.push(node.right);
        }
        return res;
    }
}
