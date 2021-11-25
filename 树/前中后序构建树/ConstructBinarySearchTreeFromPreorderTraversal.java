import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ConstructBinarySearchTreeFromPreorderTraversal {

    // 方法跟SerializeAndDeserializeBST一模一样
    public static void main(String[] args) {
        int[] arr = new int[]{8,5,1,7,10,12};
        TreeNode root = bstFromPreorder(arr);
        System.out.println("DONE");
    }

    static int i = 0;
    public static TreeNode bstFromPreorder(int[] preorder) {
        return helper(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static TreeNode helper(int[] preorder, int min, int max) {
        if (i >= preorder.length) return null;
        int val = preorder[i];
        if (val < min || val > max) return null;
        TreeNode root = new TreeNode(val);
        i++;
        root.left = helper(preorder, min, val);
        root.right= helper(preorder, val, max);
        return root;
    }

    //这就是SerializeAndDeserializeBST 中的deserialize
    public TreeNode bstFromPreorder_2(int[] preorder) {
        Queue<Integer> queue = new LinkedList<>();
        for (int x : preorder) {
            queue.offer(x);
        }
        return helper(queue, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private TreeNode helper(Queue<Integer> q, int min, int max) {
        if (q.isEmpty()) return null;
        int val = q.peek();
        if (val < min || val > max) return null;
        TreeNode root = new TreeNode(q.poll());
        root.left = helper(q, min, val);
        root.right= helper(q, val, max);
        return root;
    }
}
