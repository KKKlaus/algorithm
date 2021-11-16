public class InorderSuccessorInBST {

    //https://www.youtube.com/watch?v=5cPbNCrdotA

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (p.right != null) {
            return findMin(p.right);
        } else {
            TreeNode ancestor = root;
            TreeNode successor = null;
            while (ancestor != p) {   // 这个情况求解非常巧妙
                if (p.val < ancestor.val) {
                    successor = ancestor;
                    ancestor = ancestor.left;
                } else {
                    ancestor = ancestor.right;
                }
            }
            return successor;
        }
    }

    private TreeNode findMin(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) {
            this.val = val;
        }
    }
}
