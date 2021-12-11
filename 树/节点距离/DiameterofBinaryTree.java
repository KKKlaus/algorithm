public class DiameterofBinaryTree {

    // 这道题是找树中最远的两个点
    // 类似题目比如找离某个点最远的，想法就是把树看成图，用bfs做
    int max = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        maxDepth(root);
        return max;
    }

    private int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        max = Math.max(max, left + right);
        return 1 + Math.max(left, right);

    }
}



