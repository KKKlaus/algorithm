public class BinaryTreeMaximumPathSum {

    //跟DiameterOfBT一样的做法，唯一的区别是这里带权值，但其实一样
    int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        maxPathDown(root);
        return max;
    }

    private int maxPathDown(TreeNode root) {
        if (root == null) return 0;
        int maxLeft = Math.max(0, maxPathDown(root.left));
        int maxRight = Math.max(0, maxPathDown(root.right));
        max = Math.max(max, root.val + maxLeft + maxRight);
        return Math.max(maxLeft, maxRight) + root.val;
    }
}
