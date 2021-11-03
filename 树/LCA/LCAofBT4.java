import java.util.HashSet;
import java.util.Set;

public class LCAofBT4 {


    // 跟1一样的做法
    Set<TreeNode> set = new HashSet<>();
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
        for (TreeNode node : nodes) {
            set.add(node);
        }
        return helper(root);
    }

    private TreeNode helper(TreeNode root) {
        if (root == null) return root;
        if (set.contains(root)) return root;
        TreeNode left = helper(root.left);
        TreeNode right = helper(root.right);
        if (left != null && right != null) return root;
        if (left == null && right == null) return null;
        return left == null ? right : left;
    }

    class TreeNode {
        int val;
        TreeNode left, right;
        public TreeNode(int val) {
            this.val = val;
        }
    }
}
