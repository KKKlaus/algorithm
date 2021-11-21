import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class FindLeavesOfBinaryTree {

    class Pair {
        int val;
        double gap;
        Pair(int val, double gap) {
            this.val = val;
            this.gap = gap;
        }
    }

    PriorityQueue<Pair> maxHeap = new PriorityQueue<Pair>((a, b) -> (int) (a.gap - b.gap));
    public static void main(String[] args) {
        double target = 0.231;
        int s = 1;
        double x = s - target;
    }

    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        height(root, res);
        return res;
    }

    private int height(TreeNode root, List<List<Integer>> res) {
        if (root == null) return -1;
        int height = 1 + Math.max(height(root.left, res), height(root.right, res));
        if (height == res.size()) {
            res.add(new ArrayList<>());
        }
        res.get(height).add(root.val);
        return height;
    }
}
