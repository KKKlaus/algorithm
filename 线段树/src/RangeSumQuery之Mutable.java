public class RangeSumQuery之Mutable {

    // 方法2：线段树：构造器O(N), update: O(logN), sumRange: O(logN)
    class NumArray {

        STNode root = null;
        public NumArray(int[] nums) {
            root = buildTree(nums, 0, nums.length - 1);
        }

        public void update(int index, int val) {
            updateHelper(root, index, val);
        }

        public int sumRange(int left, int right) {
            return sumRangeHelper(root, left, right);
        }

        public STNode buildTree(int[] nums, int start, int end) {
            if (start > end) return null;
            STNode node = new STNode(start, end, 0);
            if (start == end) {
                node.sum = nums[start];
            } else {
                int mid = start + (end - start) / 2;
                node.left = buildTree(nums, start, mid);
                node.right = buildTree(nums, mid + 1, end);
                node.sum = node.left.sum + node.right.sum;
            }
            return node;
        }

        public void updateHelper(STNode root, int index, int val) {
            if (root.start == index && root.end == index) {
                root.sum = val;
                return;
            }

            int mid = root.start + (root.end - root.start) / 2;
            if (index <= mid) {
                updateHelper(root.left, index, val);
            } else {
                updateHelper(root.right, index, val);
            }
            root.sum = root.left.sum + root.right.sum;
        }

        public int sumRangeHelper(STNode root, int start, int end) {
            if (root.start == start && root.end == end) return root.sum;
            int mid = root.start + (root.end - root.start) / 2;

            int res = 0;
            if (start <= mid && mid < end) {
                res += sumRangeHelper(root.left, start, mid);
                res += sumRangeHelper(root.right, mid + 1, end);
            } else if (mid < start) {
                res += sumRangeHelper(root.right, start, end);
            } else {
                res += sumRangeHelper(root.left, start, end);
            }
            return res;
        }

        class STNode {
            int start, end, sum;
            STNode left, right;

            public STNode(int start, int end, int sum) {
                this.start = start;
                this.end = end;
                this.sum = sum;
            }
        }
    }


    // 方法1：prefixSum: 构造器O(N), update: O(n), sumRange:O(1)
    class NumArray2 {

        int[] prefixSum;
        int[] nums;
        public NumArray2(int[] nums) {
            this.nums = nums;
            int n = nums.length;
            prefixSum = new int[n + 1];
            for (int i = 0; i < n; i++) {
                prefixSum[i + 1] = prefixSum[i] + nums[i];
            }
        }

        public void update(int index, int val) {
            int delta = val - nums[index];
            for (int i = index + 1; i < prefixSum.length; i++) {
                prefixSum[i] += delta;
            }
            nums[index] = val;
        }

        public int sumRange(int left, int right) {
            return prefixSum[right + 1] - prefixSum[left];
        }
    }
}
