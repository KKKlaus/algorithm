import java.util.Stack;

public class RecoverBST {

    // iterative
    public void recoverTree(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode first = null;
        TreeNode second = null;
        TreeNode prev = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (prev != null && prev.val > root.val) {
//                System.out.print("prev = " + prev.val);
//                System.out.print("root = " + root.val + "\n");
                if (first == null) {
                    first = prev;
                    second = root; // [3,1,4,null,null,2]为例，有可能第一个的顺序错误就是那两个
                }
                else second = root;
            }
            prev = root;
            root = root.right;
        }

        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }


    // Recursive
    TreeNode first = null;
    TreeNode second = null;
    TreeNode prev = null;

    public void recoverTree_2(TreeNode root) {
        inOrder(root);
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    private void inOrder(TreeNode root) {
        if (root == null) return;
        inOrder(root.left);
        // do
        if (prev != null && prev.val >= root.val) {
            if (first == null) {
                first = prev;
                second = root;
            } else {
                second = root;
            }
        }
        prev = root;
        inOrder(root.right);
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
