public class LCAofDeepestLeaves {

    // 跟LCA1一样的想法，但是利用height创建pair非常巧妙
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        Pair res = helper(root, 0);
        return res.node;
    }

    public Pair helper(TreeNode root, int level) {
        if (root == null) return new Pair(null, level);
        Pair left = helper(root.left, level + 1);
        Pair right = helper(root.right, level + 1);
        if (left.height == right.height) return new Pair(root, left.height);
        return left.height > right.height ? left : right;
    }

    class Pair {
        TreeNode node;
        int height;
        public Pair(TreeNode node, int height) {
            this.node = node;
            this.height = height;
        }
    }

    class TreeNode {
        int val;
        TreeNode left, right;
        public TreeNode(int val) {
            this.val = val;
        }
    }
}
