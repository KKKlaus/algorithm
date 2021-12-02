public class FlipBinaryTree {

    //https://leetcode.com/discuss/interview-question/371640/google-onsite-flip-binary-tree
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode parent;
        public TreeNode(int val) {
            this.val = val;
        }
    }

    // 如果没有parent, 用dfs跑一遍存在map里
    public TreeNode flip(TreeNode root, TreeNode leaf) {
        if (root == null || root == leaf) return root;
        TreeNode l = flip(root.left, leaf);
        if (l != null) {
            TreeNode cur = l;
            while (cur.parent != null) {
                cur.parent.left = null;
                cur.right = cur.parent;
                cur = cur.parent;
            }
            return l;
        }
        TreeNode r = flip(root.right, leaf);
        if (r != null) {
            TreeNode cur = r;
            while (cur.parent != null) {
                cur.parent.right = null;
                cur.left = cur.parent;
                cur = cur.parent;
            }
            return r;
        }
        return null;
    }


    public static void main(String[] args) {
        FlipBinaryTree t = new FlipBinaryTree();
        t.test();
    }

    private void test() {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);
        TreeNode t7 = new TreeNode(7);
        TreeNode t8 = new TreeNode(8);
        TreeNode t9 = new TreeNode(9);

        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t2.right = t5;
        t3.right = t6;
        t4.left = t7;
        t4.right = t8;
        t5.right = t9;
        //还要定义parent

        flip(t1, t6);
    }
}
