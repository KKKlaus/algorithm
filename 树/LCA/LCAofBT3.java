import java.util.HashSet;
import java.util.Set;

public class LCAofBT3 {

    // 方法1： 跟LCA1一样
    public Node lowestCommonAncestor(Node p, Node q) {
        Set<Node> set = new HashSet<>();
        while (p != null) {
            set.add(p);
            p = p.parent;
        }
        while (!set.contains(q)) {
            q = q.parent;
        }
        return q;
    }

    // 方法2： 跟160. Intersection of Two Linked Lists 一模一样的做法
    public Node lowestCommonAncestor_2(Node p, Node q) {
        Node p2 = p;
        Node q2 = q;
        while (p2 != q2) {
            p2 = p2 == null ? q : p2.parent;
            q2 = q2 == null ? p : q2.parent;
        }
        return p2;
    }

    class Node {
        int val;
        Node left;
        Node right;
        Node parent;
    }
}
