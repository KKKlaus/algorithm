public class ClosestBinarySearchTreeValue {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) {
            this.val = val;
        }
    }

    // 时间复杂度： 因为不一定是平衡的，举例为一直是左子树。复杂度O(h)， h = height
    // 如果是平衡二叉树，那就是平常我们熟知的O(logN) N = 节点数

    private int res = Integer.MAX_VALUE;
    public int closestValue(TreeNode root, double target) {
        search(root, target);
        return res;
    }

    private void search(TreeNode root, double target) {
        if (root == null) return;
        double minDiff = Math.abs(Double.valueOf(res) - target);
        double curDiff = Math.abs(Double.valueOf(root.val) - target);
        if (curDiff < minDiff) res = root.val;
        if (target < root.val) search(root.left, target);
        else search(root.right, target);
    }
}
