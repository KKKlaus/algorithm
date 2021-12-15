package Google.线段树绳索树脐带树;

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
    public Pair split(RopeNode node, int index) {
        if (node.left == null) {
            Pair pair = new Pair();
            if (index == 0) {
                pair.one = null;
                pair.two = node;
            } else if (index == node.weight) {
                pair.one = node;
                pair.two = null;
            } else {
                String s1 = node.data.substring(0, index);
                String s2 = node.data.substring(index, node.weight);
                pair.one = new RopeNode(s1.length(), s1);
                pair.two = new RopeNode(s2.length(), s2);
            }
            return pair;
        } else if (index == node.weight) {
            return new Pair(node.left, node.right);
        } else if (index < node.weight) {
            Pair pair = split(node.left, index);
            return new Pair(pair.one, concat(pair.two, node.right));
        } else {
            Pair pair = split(node.right, index);
            return new Pair(concat(node.left, pair.one), pair.two);
        }
    }

    // Insert: O(logN)
    public RopeNode insert(RopeNode root, int index, String insertContent) {
        Pair pair = split(root, index);
        RopeNode insertNode = new RopeNode(insertContent.length(), insertContent);
        return concat(concat(pair.one, insertNode), pair.two);
    }

    // Delete: O(logN)
    public RopeNode delete(RopeNode root, int start, int end) {
        Pair pair1 = split(root, start);
        Pair pair2 = split(root, end);
        return concat(pair1.one, pair2.two);
    }

    // print Substring (i, j)
    public String report(RopeNode root, int start, int end) {
        // 方法1： 利用split
//        Pair pair1 = split(root, start);
//        Pair pair2 = split(pair1.two, end - start); // 注意这里end - start
//        return print(pair2.one);
        // 方法2：跟线段树/脐带树同理recursion
        if (root.left == null && root.right == null) {
            return root.data.substring(start, end);
        }
        int leftLen = root.weight;
        String res = "";
        if (end <= leftLen) {
            res += report(root.left, start, end);
        } else if (start > leftLen) {
            res += report(root.right, start - leftLen, end - leftLen);
        } else {
            res += report(root.left, start, leftLen);
            res += report(root.right, leftLen, end - leftLen);
        }
        return res;
    }

    public String print(RopeNode node) {
        if (node == null) {
            return node.data;
        }
        return print(node.left) + print(node.right);
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
