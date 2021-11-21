import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class ClosestBinarySearchTreeValue2 {

    // 方法1： 我的方法O(NlogK), 但没有利用BST的性质，所有应用在BT也可以，复杂度不是最优的
    PriorityQueue<Pair> maxHeap = new PriorityQueue<Pair>((a, b) -> a.gap > b.gap ? -1 : 1);

    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        dfs(root, target, k);
        List<Integer> res = new ArrayList<>();
        while (!maxHeap.isEmpty()) {
            res.add(0, maxHeap.poll().val);
        }
        return res;
    }

    private void dfs(TreeNode root, double target, int k) {
        if (root == null) return;
        maxHeap.offer(new Pair(root.val, (double) (Math.abs(target - root.val))));
        if (maxHeap.size() > k) maxHeap.poll();
        dfs(root.left, target, k);
        dfs(root.right, target, k);
    }

    class Pair {
        int val;
        double gap;
        Pair(int val, double gap) {
            this.val = val;
            this.gap = gap;
        }
    }

    // 方法2 O(n)利用BST性质
    public List<Integer> closestKValues2(TreeNode root, double target, int k) {
        LinkedList<Integer> res = new LinkedList<>();
        dfs(root, target, k, res);
        return res;
    }

    private void dfs(TreeNode root, double target, int k, LinkedList<Integer> res) {
        if (root == null) return;

        dfs(root.left, target, k, res);

        if (res.size() == k) {
            if (Math.abs(target - root.val) < Math.abs(target - res.peekFirst())) {   // 这一行太难想到
                res.removeFirst();
            } else return;
        }
        res.add(root.val);
        dfs(root.right, target, k, res);
    }
}
