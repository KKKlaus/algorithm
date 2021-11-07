import java.util.HashMap;

public class OptimizedBST {

    public static void main(String[] args) {
        OptimizedBST t = new OptimizedBST();
        t.test();
    }

    public void test() {
        BST bst = new BST();
        bst.insert(5);
        bst.insert(3);
        bst.insert(8);
        bst.insert(1);
        bst.insert(9);
        bst.insert(4);
        bst.insert(7);
        System.out.println(bst.NodeToMeanMap.get(bst.root));
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

    class BST {

        TreeNode root;
        HashMap<TreeNode, Integer> NodeToCountMap;
        HashMap<TreeNode, Double> NodeToMeanMap;

        public BST() {
            root = null;
            NodeToCountMap = new HashMap<>();
            NodeToMeanMap = new HashMap<>();
        }

        // iterative
        public TreeNode insert(int target) {
            if (root == null) {
                root= new TreeNode(target);
                NodeToCountMap.put(root, 1);
                NodeToMeanMap.put(root, (double) root.val);
                return root;
            }
            TreeNode cur = root;
            TreeNode prev = null;
            while (cur != null) {
                prev = cur;
                reCalculate(prev, target);
                if (cur.val < target) cur = cur.right;
                else cur = cur.left;
            }
            TreeNode newNode = new TreeNode(target);
            NodeToCountMap.put(newNode, 1);
            NodeToMeanMap.put(newNode, (double) newNode.val);
            if (prev.val < target) prev.right = newNode;
            else prev.left = newNode;
            return root;
        }

        public double getMean(TreeNode node) {
            return 0;
        }

        private void reCalculate(TreeNode prev, int target) {
            int curCount = NodeToCountMap.get(prev);
            Double curMean = NodeToMeanMap.get(prev);
            int nextCount = curCount + 1;
            Double nextMean = (curCount * curMean + target) / nextCount;
            NodeToCountMap.put(prev, nextCount);
            NodeToMeanMap.put(prev, nextMean);
        }
    }


}
