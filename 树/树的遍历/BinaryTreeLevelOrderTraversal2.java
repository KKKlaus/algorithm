import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeLevelOrderTraversal2 {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode (){}
        TreeNode(int val) {
            this.val = val;
        }
    }

    // BFS
    public List<List<Integer>> levelOrder(BinaryTreeLevelOrderTraversal.TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<BinaryTreeLevelOrderTraversal.TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                BinaryTreeLevelOrderTraversal.TreeNode node = queue.poll();
                temp.add(node.val);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            res.add(0, temp);    // 改下这行即可
        }
        return res;
    }


    // DFS
    public List<List<Integer>> levelOrder_2(BinaryTreeLevelOrderTraversal.TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(root, res, 0);
        return res;
    }

    private void dfs(BinaryTreeLevelOrderTraversal.TreeNode root, List<List<Integer>> res, int height) {
        if (root == null) return;
        if (height == res.size()) res.add(0, new ArrayList<>());     // 改下这两行
        res.get(res.size() - height - 1).add(root.val);                    //
        dfs(root.left, res, height + 1);
        dfs(root.right, res, height + 1);
    }
}
