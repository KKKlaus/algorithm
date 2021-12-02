import java.util.*;

public class AllNodesDistanceKinBinaryTree {

    HashMap<Integer, List<Integer>> graph = new HashMap<>();
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        buildGraph(root, null);

//         for (int key : graph.keySet()) {
//             System.out.print("key : " + key + " value: ");
//             for (int val : graph.get(key)) {
//                 System.out.print(" " + val);
//             }
//             System.out.println();
//         }

        List<Integer> res = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        q.offer(target.val);
        Set<Integer> visited = new HashSet<>();
        visited.add(target.val);

        while (!q.isEmpty()) {
            int size = q.size();
            if (k == 0) res.addAll(q);
            for (int i = 0; i < size; i++) {
                int cur = q.poll();
                if (!graph.containsKey(cur)) continue;
                for (int nei : graph.get(cur)) {
                    if (visited.contains(nei)) continue;
                    q.offer(nei);
                    visited.add(nei);
                }
            }
            k--;
        }
        return res;
    }

    private void buildGraph(TreeNode root, TreeNode parent) {
        if (root == null) return;

        if (parent != null) {
            if (!graph.containsKey(root.val)) {
                graph.put(root.val, new ArrayList<>());
            }
            graph.get(root.val).add(parent.val);

            if (!graph.containsKey(parent.val)) {
                graph.put(parent.val, new ArrayList<>());
            }
            graph.get(parent.val).add(root.val);
        }


        buildGraph(root.left, root);
        buildGraph(root.right, root);
    }
}
