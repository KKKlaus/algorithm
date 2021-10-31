import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// 方法3最好
public class ValidateBinarySearchTree {

    // 方法1： 最直接的inorder序列比较前后大小
    public boolean isValidBST(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inOrder(root, list);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) <= list.get(i - 1)) return false;
        }
        return true;
    }

    private void inOrder(TreeNode root, List<Integer> list) {
        if (root == null) return;
        inOrder(root.left, list);
        list.add(root.val);
        inOrder(root.right, list);
    }


    // 方法2：传入边界 递归
    public boolean isValidBST_2(TreeNode root) {
        return helper(root, null, null);
    }

    private boolean helper(TreeNode root, Integer max, Integer min) {
        if (root == null) return true;
        if (max != null && root.val >= max) return false;
        if (min != null && root.val <= min) return false;
        return helper(root.left, root.val, min) && helper(root.right, max, root.val);
    }

    // 方法3：（重要！！！！！！） 利用iterative得到inorder的方法
    public boolean isValidBST_3(TreeNode root) {
        if (root == null) return true;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if(pre != null && root.val <= pre.val) return false;
            pre = root;
            root = root.right;
        }
        return true;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) {
            this.val = val;
        }
    }
}
