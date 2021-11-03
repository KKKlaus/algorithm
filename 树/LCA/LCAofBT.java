import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LCAofBT {


    // recursive 解析：https://www.youtube.com/watch?v=13m9ZCB8gjw
    // 其实就是每个节点往上返回，然后各种情况讨论
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return root;
        if (root == p || root == q) return root;      // 注意这一行放置位置，放在这里可以更快，因为遇到就返回，不会再往下走。
        // 如果放到14，15行后面，就会从底开始，但是适用于lowestCommonAncestorII这道题，因为需要确定p q存不存在这个树中
        TreeNode left = lowestCommonAncestor(root.left, p , q);
        TreeNode right = lowestCommonAncestor(root.right, p , q);
        if (left != null && right != null) return root;
        if (left == null && right == null) return null;
        return left == null ? right : left;
    }


    // 非recursion做法: 获得其中一个节点的所有parent list，然后再第二个遍历
    HashMap<TreeNode, TreeNode> map = new HashMap<>();
    public TreeNode lowestCommonAncestor_2(TreeNode root, TreeNode p, TreeNode q) {
        dfs(null, root);
        List<TreeNode> list = new ArrayList<>();
        while (p != null) {
            list.add(p);
            p = map.get(p);
        }
        while (!list.contains(q)) {
            q = map.get(q);
        }
        return q;
    }

    private void dfs(TreeNode parent, TreeNode cur) {
        if (cur == null) return;
        map.put(cur, parent);
        dfs(cur, cur.left);
        dfs(cur, cur.right);
    }

    class TreeNode {
        int val;
        TreeNode left, right;
        public TreeNode(int val) {
            this.val = val;
        }
    }
}
