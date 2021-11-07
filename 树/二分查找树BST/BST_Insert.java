public class BST_Insert {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) {
            this.val = val;
        }
    }

    // recursive
    public TreeNode insert(TreeNode root, int target) {
        if (root == null) {
            return new TreeNode(target);
        }
        if (root.val < target) root.right = insert(root.right, target);
        else if (root.val > target) root.left = insert(root.left, target);
        return root;
    }

    // iterativec
    public TreeNode insert_2(TreeNode root, int target) {
        TreeNode cur = root;
        if (cur == null) return new TreeNode(target);
        TreeNode prev = null;
        while (cur != null) {
            prev = cur;
            if (cur.val < target) cur = cur.right;
            else cur = cur.left;
        }
        if (prev.val < target) prev.right = new TreeNode(target);
        else prev.left = new TreeNode(target);
        return root;
    }
}
