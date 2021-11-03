public class LCAofBT2 {


    // 看LCAofBT的解析就懂这个方法了，关键就是14和15行放置位置。 强制改成postOder遍历所有节点
    // pFound和qFound也可以写成count == 2？
    boolean pFound = false, qFound = false;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode res = helper(root, p, q);
        return pFound && qFound ? res : null;
    }

    private TreeNode helper(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return root;
        TreeNode left = helper(root.left, p , q);
        TreeNode right = helper(root.right, p , q);
        if (root == p) {
            pFound = true;
            return root;
        }
        if (root == q) {
            qFound = true;
            return root;
        }
        return left == null ? right : right == null ? left : root;
    }


    class TreeNode {
        int val;
        TreeNode left, right;
        public TreeNode(int val) {
            this.val = val;
        }
    }
}
