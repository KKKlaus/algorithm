package Google;

// 绳索树：用来处理超长字符串
// 特点是：叶节点存字符串，非叶节点存左字符串长度(weight)

// 参考博客： https://iq.opengenus.org/rope-data-structure/
// 参考维基百科：https://en.wikipedia.org/wiki/Rope_(data_structure)
public class RopesTree {

    // Search(index): O(logN)
    public char search(RopeNode node, int index) {
        if (node.left == null) { // 到叶节点了,  因为rope必然是要么都有左右子树，要么就是叶节点
            if (index < 0 || index > node.data.length()) throw new NullPointerException();
            return node.data.charAt(index);
        }
        if (index > node.weight) {
            return search(node.right, index - node.weight);
        } else {
            return search(node.left, index);
        }
    }

    // Concatenation(S1+S2): O(1)
    public RopeNode concat(RopeNode node1, RopeNode node2) {
        if (node1 == null) return node2;
        if (node2 == null) return node1;
        RopeNode newNode = new RopeNode(node1.weight);
        newNode.left = node1;
        newNode.right = node2;
        return newNode;
    }

    // Split : O(logN)
//    public void split(RopeNode node, int index) {
//        if (node.left == null) {
//            Pair pair = new Pair();
//            if (index == 0)
//        }
//    }
//
//    public void delete(RopeNode node, ) {
//
//    }

    public void print(RopeNode root) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            System.out.println(root.data);
        }
        print(root.left);
        print(root.right);
    }

    class RopeNode {
        RopeNode left, right;
        int weight;
        String data;
        public RopeNode(int weight) {
            this.weight = weight;
        }
        public RopeNode(int weight, String data) {
            this.weight = weight;
            this.data = data;
        }
    }

    class Pair {
        RopeNode one;
        RopeNode two;
        public Pair() {}
        public Pair(RopeNode one, RopeNode two) {
            this.one = one;
            this.two = two;
        }
    }
}
