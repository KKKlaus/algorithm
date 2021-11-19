public class SerializeAndDeserializeNaryT {

    //public String serialize(Node root) {
    //        StringBuilder sb = new StringBuilder();
    //        serialize(root, sb);
    //        return sb.toString();
    //    }
    //
    //    private void serialize(Node root, StringBuilder sb) {
    //        if (root == null) return;
    //        sb.append(root.val).append(",");
    //        sb.append(root.children.size()).append(",");
    //        for (Node child : root.children) {
    //            serialize(child, sb);
    //        }
    //    }
    //
    //    // Decodes your encoded data to tree.
    //    public Node deserialize(String data) {
    //        if (data.isEmpty()) return null;
    //        Queue<String> q = new LinkedList<>(Arrays.asList(data.split(",")));
    //        return deserialize(q);
    //    }
    //
    //    private Node deserialize(Queue<String> q) {
    //        if (q.isEmpty()) return null;
    //        Node root = new Node();
    //        root.val = Integer.parseInt(q.poll());
    //        int size = Integer.parseInt(q.poll());
    //        root.children = new ArrayList<>(size);
    //        for (int i = 0; i < size; i++) {
    //            root.children.add(deserialize(q));
    //        }
    //        return root;
    //    }
}
