public class 前和后序建树 {

    public static void main(String[] args) {
        前和后序建树 t = new 前和后序建树();

    }

    int[] preorder, postorder;
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        this.preorder = preorder;
        this.postorder = postorder;
        return helper(0, 0, preorder.length);
    }

    // i 和 j分别为preorder, postorder的初始位置，n为当前数组的长度
    private TreeNode helper(int i, int j, int n) {
        if (n <= 0) return null;
        TreeNode root = new TreeNode(preorder[i]);
        if (n == 1) return root;
        int k = j; // k to find left root in postorder
        while (postorder[k] != preorder[i + 1]) k++;
        int l = k - j + 1;
        root.left = helper(i + 1, j, l);
        root.right = helper(i + 1 + l, k + 1, n - l - 1);
        return root;
    }
}
