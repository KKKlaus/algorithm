import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeZigzagLevelOrderTraversal {

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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (res.size() % 2 == 0) temp.add(node.val);       // 改这里奇偶行的判断
                else temp.add(0, node.val);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            res.add(temp);
        }
        return res;
    }


    // DFS
    public List<List<Integer>> zigzagLevelOrder_dfs(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(root, res, 0);
        return res;
    }


    private void dfs(TreeNode root, List<List<Integer>> res, int height) {
        if (root == null) return;
        if (height == res.size()) res.add(new ArrayList<>());
        if (height % 2 == 0) res.get(height).add(root.val);       // 改这里奇偶行的判断
        else res.get(height).add(0, root.val);               //
        dfs(root.left, res, height + 1);
        dfs(root.right, res, height + 1);
    }
}
