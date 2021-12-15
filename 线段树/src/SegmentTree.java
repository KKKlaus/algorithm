public class SegmentTree {

    class SegmentTreeNode {
        int start, end, sum;
        SegmentTreeNode left, right;

        public SegmentTreeNode(int start, int end, int sum) {
            this.start = start;
            this.end = end;
            this.sum = sum;
            left = null;
            right = null;
        }
    }

    private SegmentTreeNode buildHelper(int[] A, int start, int end) {
        if (start > end) return null;
        SegmentTreeNode node = new SegmentTreeNode(start, end, 0);
        if (start == end) {
            node.sum = A[start];
        } else {
            int mid = start + (end - start) / 2;
            node.left = buildHelper(A, start, mid);
            node.right = buildHelper(A, mid + 1, end);
            node.sum = node.left.sum + node.right.sum;
        }
        return node;
    }

    private long query(SegmentTreeNode root, int start, int end) {
        if (start == root.start && end == root.end) {
            return root.sum;
        }
        int mid = root.start + (root.end - root.start) / 2;

        long res = 0;
        if (start <= mid && mid < end) {
            res += query(root.left, start, mid);
            res += query(root.right, mid + 1, end);
        } else if (mid < start) {
            res += query(root.right, start, end);
        } else {
            res += query(root.left, start, end);
        }
        return res;
    }

    private void modify(SegmentTreeNode root, int index, int value) {
        if (root.start == index && root.end == index) {
            root.sum = value;
            return;
        }

        int mid = root.start + (root.end - root.start) / 2;
        if (index <= mid) {
            modify(root.left, index, value);
        } else {
            modify(root.right, index, value);
        }
        root.sum = root.left.sum + root.right.sum;
    }
}
