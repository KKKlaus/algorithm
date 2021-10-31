import java.util.*;
import java.util.stream.Collectors;

public class BinaryTreeVerticalOrderTraversal {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode (){}
        TreeNode(int val) {
            this.val = val;
        }
    }

    // BFS + hashMap
//    public List<List<Integer>> verticalOrder(TreeNode root) {
//        List<List<Integer>> res = new ArrayList<>();
//        if (root == null) return res;
//        Map<Integer, List<Integer>> map = new HashMap<>();
//        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
//        int min = 0, max = 0, column = 0;
//        queue.offer(new Pair(root, column));
//
//        while (!queue.isEmpty()) {
//            Pair<TreeNode, Integer> p = queue.poll();
//            TreeNode node = p.getKey();
//            column = p.getValue();
//            if (!map.containsKey(column)) {
//                map.put(column, new ArrayList<>());
//            }
//            map.get(column).add(node.val);
//            min = Math.min(min, column);
//            max = Math.max(max, column);
//            if (node.left != null) queue.offer(new Pair(node.left, column - 1));
//            if (node.right != null) queue.offer(new Pair(node.right, column + 1));
//        }
//        for (int i = min; i <= max; i++) {
//            res.add(map.get(i));
//        }
//        return res;
//    }


    class TreeNode_v2 {
        int offset;
        TreeNode node;
        TreeNode_v2() {}
        TreeNode_v2(int offset, TreeNode node) {
            this.offset = offset;
            this.node = node;
        }
    }

    // BFS + TreeMap
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        TreeMap<Integer, List<Integer>> map = new TreeMap<>(); // offset - Node list
        Queue<TreeNode_v2> queue = new LinkedList<>();
        queue.offer(new TreeNode_v2(0, root));
        while (!queue.isEmpty()) {
            TreeNode_v2 node_v2 = queue.poll();
            TreeNode node  = node_v2.node;
            int offset = node_v2.offset;
            if (!map.containsKey(offset)) {
                map.put(offset, new ArrayList<>());
            }
            map.get(offset).add(node.val);
            if (node.left != null) queue.offer(new TreeNode_v2(offset - 1, node.left));
            if (node.right != null) queue.offer(new TreeNode_v2(offset + 1, node.right));
        }

        for (int k : map.keySet()) {
            res.add(map.get(k));
        }
        return res;
    }



    // dfs + TreeMap      复杂度高了
    // 还需要对height进行排序，否则错误
    //Input:
    //[3,9,8,4,0,1,7,null,null,null,2,5]
    //Output:
    //[[4],[9,5],[3,0,1],[2,8],[7]]
    //Expected:
    //[[4],[9,5],[3,0,1],[8,2],[7]]
    public List<List<Integer>> verticalOrder_dfs(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        TreeMap<Integer, List<int[]>> map = new TreeMap<>(); //offset - [node.val, height]
        dfs(root, 0, 0, map);
        for (int k : map.keySet()) {
            List<int[]> temp = map.get(k);
            temp.sort((a, b) -> a[1] - b[1]);
            res.add(temp.stream().map(a -> a[0]).collect(Collectors.toList()));
        }
        return res;
    }

    private void dfs(TreeNode root, int offset, int height, TreeMap<Integer, List<int[]>> map) {
        if (root == null) return;
        if (!map.containsKey(offset)) map.put(offset, new ArrayList<>());
        map.get(offset).add(new int[]{root.val, height});
        dfs(root.left, offset - 1, height + 1, map);
        dfs(root.right, offset + 1, height + 1, map);
    }
}
