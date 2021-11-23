import java.util.*;

// 先提前做下LCA1 / 4 对最小公共祖先有感谢后再做这道恶心题
public class LCAofNary {


    public static void main(String[] args) {
        LCAofNary t = new LCAofNary();
        t.test();
    }

    private void test() {
        Node root = new Node(1);
        List<Node> list1 = Arrays.asList(new Node(2), new Node(3), new Node(4));
        List<Node> list2 = Arrays.asList(new Node(5), new Node(6), new Node(7));
        List<Node> list3 = Arrays.asList(new Node(8), new Node(9), new Node(10));
        root.children = list1;
        list1.get(0).children = list2;
        list2.get(2).children = list3;

//        Node[] nodes = new Node[3];
//        nodes[0] = list2.get(0);
//        nodes[1] = list3.get(2);
//        nodes[2] = list1.get(1);
//        Node ancestor = lowestCommonAncestorVersionN(root,nodes);
//        System.out.println(ancestor.val);

        Node[] nodes = new Node[3];
        nodes[0] = list3.get(0);
        nodes[1] = list3.get(1);
        nodes[2] = list3.get(2);
        Node ancestor = lowestCommonAncestorVersionN(root,nodes);
        System.out.println(ancestor.val);
    }

    // LCAOf n-ray tree with Set, 好难
    Set<Node> nset = new HashSet<>();
    public Node lowestCommonAncestorVersionN(Node root, Node[] nodes) {
        for (Node node : nodes) {
            nset.add(node);
        }
        return lca(root);
    }

    private Node lca(Node root) {
        if (root == null) return null;
        if (nset.contains(root)) return root;
        if (root.children == null) return null;
        int count = 0;
        Node temp = null; // 在count = 1的时候才有用
        for (Node child : root.children) {
            Node node = lca(child);
            if (node != null) {
                count++;
                temp = node;
            }
        }
        if (count == 0) return null;
        if (count == 1) return temp;
        return root;
    }


    // LCAOfBT
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

    //LCAofBT4 : WithSet
    Set<TreeNode> set = new HashSet<>();
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
        for (TreeNode node : nodes) {
            set.add(node);
        }
        return helper(root);
    }

    private TreeNode helper(TreeNode root) {
        if (root == null) return root;
        if (set.contains(root)) return root;
        TreeNode left = helper(root.left);
        TreeNode right = helper(root.right);
//        if (left != null && right != null) return root;
//        if (left == null && right == null) return null;
//        return left == null ? right : left;
        return left == null ? right : right == null ? left : root;
    }

    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };
}
