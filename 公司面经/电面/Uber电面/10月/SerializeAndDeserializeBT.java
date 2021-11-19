import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class SerializeAndDeserializeBT {

//    public String serialize(TreeNode root) {
//        StringBuilder sb = new StringBuilder();
//        serialize(root, sb);
//        return sb.toString();
//    }
//
//    private void serialize(TreeNode root, StringBuilder sb) {
//        if (root == null) {
//            sb.append("#").append(",");
//            return;
//        }
//        sb.append(root.val).append(",");
//        serialize(root.left, sb);
//        serialize(root.right, sb);
//    }
//
//    // Decodes your encoded data to tree.
//    public TreeNode deserialize(String data) {
//        if (data.isEmpty()) return null;
//        Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(",")));
//        return deserialize(queue);
//    }
//
//    private TreeNode deserialize(Queue<String> q) {
//        if (q.isEmpty()) return null;
//        String cur = q.poll();
//        if (cur.equals("#")) return null;
//        TreeNode root = new TreeNode(Integer.parseInt(cur));
//        root.left = deserialize(q);
//        root.right = deserialize(q);
//        return root;
//    }
}
