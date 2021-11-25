import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class SerializeAndDeserializeBST {

    public static void main(String[] args) {
        SerializeAndDeserializeBST t = new SerializeAndDeserializeBST();
        t.test();
    }

    private void test() {
        String s = "8,5,1,7,10,12";
        TreeNode root = deserialize(s);
        System.out.println("DONE");
    }

    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }

    private void serialize(TreeNode root, StringBuilder sb) {
        if (root == null) return;
        sb.append(root.val).append(",");
        serialize(root.left, sb);
        serialize(root.right, sb);
    }

    public TreeNode deserialize(String data) {
        if (data.isEmpty()) return null;
        Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(",")));
        return deserialize(queue, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    // 这里lower和upper很巧妙
    private TreeNode deserialize(Queue<String> q, int lower, int upper) {
        if (q.isEmpty()) return null;
        int cur = Integer.parseInt(q.peek());
        if (cur < lower || cur > upper) return null;
        q.poll();
        TreeNode root = new TreeNode(cur);
        root.left = deserialize(q, lower, cur);
        root.right = deserialize(q, cur, upper);
        return root;
    }
}
