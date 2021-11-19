import java.util.Stack;

public class 中和后序建树 {

    public static void main(String[] args) {
//        中和后序建树 t = new 中和后序建树();
//        t.test();
        System.out.println(Integer.hashCode(1));
    }

    private void test() {
        TreeNode root = buildTree(new int[]{9,3,15,20,7}, new int[]{9,15,7,20,3});
        System.out.println("DONE");
    }

    int[] inorder; int[] postorder;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.inorder = inorder;
        this.postorder = postorder;
        return helper(0, 0, inorder.length);
    }

    private TreeNode helper(int i, int j, int n) {
        if (n <= 0) return null;
        TreeNode root = new TreeNode(postorder[j + n - 1]);
        if (n == 1) return root;
        int k = 0;
        while (inorder[k] != root.val) k++;
        int l = k - i;
        root.left = helper(i, j, l);
        root.right = helper(k + 1, j + l, n - l -1);
        return root;
    }
}
