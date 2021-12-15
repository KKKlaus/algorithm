package Google;

import java.util.HashMap;

public class test {

    HashMap<Integer, HashMap<Integer, Character>> graph;
    String res = "";
    public String getDirections(TreeNode root, int startValue, int destValue) {
        buildGraph(root);
        dfs(startValue, destValue, "", graph);
        return res;
    }

    private void dfs(int curNode, int target, String path, HashMap<Integer, HashMap<Integer, Character>> graph) {
        if (curNode == target) {
            res = new String(path);
            return;
        }

        HashMap<Integer, Character> nei = graph.get(curNode);
        for (int key : nei.keySet()) {
            dfs(key, target, path + nei.get(key), graph);
        }
    }

    private void buildGraph(TreeNode root) {
        if (root == null) return;

        if (root.left != null) {
            if (!graph.containsKey(root.val)) graph.put(root.val, new HashMap<>());
            if (!graph.containsKey(root.left.val)) graph.put(root.left.val, new HashMap<>());
            graph.get(root.val).put(root.left.val, 'L');
            graph.get(root.left.val).put(root.val, 'U');
            buildGraph(root.left);
        }

        if (root.right != null) {
            if (!graph.containsKey(root.val)) graph.put(root.val, new HashMap<>());
            if (!graph.containsKey(root.right.val)) graph.put(root.right.val, new HashMap<>());
            graph.get(root.val).put(root.right.val, 'R');
            graph.get(root.right.val).put(root.val, 'U');
            buildGraph(root.right);
        }
    }

     class TreeNode {
      int val;
      TreeNode left;
     TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }

    public static void main(String[] args) {
        test t = new test();
        t.tt();
    }

    private void tt () {
        TreeNode node = new TreeNode(2);
        TreeNode node2 = new TreeNode(1);
        node.left = node2;
        System.out.println(getDirections(node, 2, 1));
    }
}
