public class BST_Search {

    // BST: 左 < 根 < 右

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        int x  = 1;
        double t = 2;
        if (x  < t) {

        }
    }

    // recursive
    public TreeNode search(TreeNode root, int target) {
        if (root == null) return null;
        if (root.val == target) return root;
        if (root.val < target) return search(root.right, target);
        else return search(root.left, target);
    }

    public TreeNode search_2(TreeNode root, int target) {
        TreeNode cur = root;
        if (cur == null) return null;
        while (true) {
            if (cur.val == target) return cur;
            if (cur.val < target) cur = cur.right;
            else cur = cur.left;
        }
    }
}
