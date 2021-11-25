public class 前和中序建树 {

    // hashmap时间优化后为O(n)
    public static void main(String[] args) {
        前和中序建树 t = new 前和中序建树();
        t.test();
    }

    private void test() {
        TreeNode root = buildTree(new int[]{3,9,20,15,7}, new int[]{9,3,15,20,7});
        System.out.println("DONE");
    }


    // 跟前后序构建树一样的递归，关键就是k的确定(27行)
    int[] preorder; int[] inorder;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        this.inorder = inorder;
        return helper(0, 0, preorder.length);
    }

    private TreeNode helper(int i, int j, int n) {
        if (n <= 0) return null;
        TreeNode root = new TreeNode(preorder[i]);
        if (n == 1) return root;
        int k = 0;
        while (inorder[k] != root.val) k++;       // 一样可以时间优化，hashmap
        int l = k - j;
        root.left = helper(i + 1, j, l);
        root.right = helper(i + 1 + l, k + 1, n - l -1);
        return root;
    }
}
