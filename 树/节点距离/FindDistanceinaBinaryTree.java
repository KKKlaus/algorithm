import java.util.ArrayList;
import java.util.List;

// 类似题目比如找离某个点最远的，反正想法就是把树看成图，用bfs做
public class FindDistanceinaBinaryTree {

    public static void main(String[] args) {
        FindDistanceinaBinaryTree t = new FindDistanceinaBinaryTree();
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

        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t2.right = t5;
        t4.left = t6;
        t4.right = t7;
        t5.left = t8;

//        System.out.println(shortPath(t1, t3, t7));
        printPath(t1, t3, t7);
    }

    public int shortPath(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode lca = lca(root, p, q);
        int leftLen = getDistance(lca, p);
        int rightLen = getDistance(lca, q);
        return leftLen + rightLen - 2; // 算了两遍lca的那个点
    }

    private TreeNode lca(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (root == p || root == q) return root;
        TreeNode left = lca(root.left, p , q);
        TreeNode right = lca(root.right, p, q);
        return left == null ? right : right == null ? left : root;
    }


    // getDistance 可以dfs也可以bfs做
    private int getDistance(TreeNode root, TreeNode target) {
        if (root == null) return 0;

        int left = getDistance(root.left, target);
        int right = getDistance(root.right, target);

        if (root == target) return 1;

        if (left == 0 && right == 0) return 0;

        return left == 0 ? right + 1 : left + 1;
    }

//    public int findDistance(TreeNode lca, int node) {
//        Queue<TreeNode> q = new LinkedList<>();
//        int distance = 0;
//        q.offer(lca);
//
//        while(!q.isEmpty()) {
//            int sz = q.size();
//            for(int i = 0; i < sz; i++) {
//                TreeNode cur = q.poll();
//                if(cur.val == node) return distance;
//                else {
//                    if(cur.left != null) q.offer(cur.left);
//                    if(cur.right != null) q.offer(cur.right);
//                }
//            }
//            distance++;
//        }
//
//        return -1;
//    }

    public void printPath(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode lca = lca(root, p, q);
        List<Integer> leftList = new ArrayList<>();
        List<Integer> rightList = new ArrayList<>();
        getPath(lca, p, new ArrayList<>(), leftList);
        getPath(lca, q, new ArrayList<>(), rightList);
        List<Integer> res = new ArrayList<>();
        for (int i = leftList.size() - 1; i >= 0; i--) {
            res.add(leftList.get(i));
        }
        for (int i = 1; i < rightList.size(); i++) {
            res.add(rightList.get(i));
        }

        for (int n : res) {
            System.out.print(n + " ");
        }
    }

    private void getPath(TreeNode root, TreeNode target, List<Integer> temp, List<Integer> res) {
        if (root == null) return;
        temp.add(root.val);

        if (target == root) {
            res.addAll(temp);
            return;
        }

        getPath(root.left, target, temp, res);
        getPath(root.right, target, temp, res);
        temp.remove(temp.size() - 1);
    }
}
