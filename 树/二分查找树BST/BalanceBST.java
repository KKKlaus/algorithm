import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BalanceBST {

    // 两道题的结合： InOrder BST + ConvertSortedArrayToBinarySearchTree

    public TreeNode balanceBST(TreeNode root) {
        List<Integer> inOrder = inOrderHelper(root);
        return getBST(inOrder, 0, inOrder.size() - 1);
    }

    private TreeNode getBST(List<Integer> list, int start, int end) {
        if (start > end) return null;
        int mid = start + (end - start) / 2;
        TreeNode root = new TreeNode(list.get(mid));
        root.left = getBST(list, start, mid - 1);
        root.right = getBST(list, mid + 1, end);
        return root;
    }

    private List<Integer> inOrderHelper(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> res = new ArrayList<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            res.add(root.val);
            root = root.right;
        }
        return res;
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
