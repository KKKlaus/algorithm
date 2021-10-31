import java.util.Stack;

public class BSTIterator {

    // 同样还是用iterative中序的方法
    // 空间复杂度: O(h) h=height
    // 时间复杂度： hasNext() : O(1)
    //            next() : O(1) -----The average time complexity of next() function is O(1) indeed in your case.
    //            As the next function can be called n times at most, and the number of right nodes in
    //            self.pushAll(tmpNode.right) function is maximal n in a tree which has n nodes,
    //            so the amortized time complexity is O(1).
    //            极端情况是全是左子树，第一次的next()时间复杂度为O(n)，但是之后的每一次都是O(1)了，
    //            也就是说超过1次的next()其实都是在给其他的子节点帮忙(就会有其他子节点不需要pushAllLeft),所以分摊时间复杂度是O(1)
    Stack<TreeNode> stack;
    public BSTIterator(TreeNode root) {
        stack = new Stack<>();
        pushAllLeft(root);
    }

    public int next() {
        TreeNode node = stack.pop();
        pushAllLeft(node.right);
        return node.val;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    private void pushAllLeft(TreeNode node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
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
}
