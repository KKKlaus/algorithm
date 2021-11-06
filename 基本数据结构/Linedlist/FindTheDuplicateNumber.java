public class FindTheDuplicateNumber {

    // 利用LinkedListCycle2的想法做。 time: O(n), space:O(1)
    public int findDuplicate(int[] nums) {
        int fast = nums[0], slow = nums[0], n = nums.length;
        while (fast < n && nums[fast] < n) {
            slow = nums[slow];
            fast = nums[nums[fast]];
            if (slow == fast) {
                int node = nums[0];
                while (node != slow) {
                    slow = nums[slow];
                    node = nums[node];
                }
                return node;
            }
        }
        return -1;
    }

    //   方法            时间复杂度         空间复杂度
    //   排序             O(nlogn)          O(1)
    //   HashSet         O(n)               O(n)
    //    二分            O(nlogn)          O(1)

    // 二分法：比较难想：如果count < mid
    //证明数较小的数字比预期要少，有更大的数字挤掉了较小的数字
    public int findDuplicate2(int[] nums) {
        int low = 1, high = nums.length - 1;
        while (low <= high) {
            int mid = (int) (low + (high - low) * 0.5);
            int cnt = 0;
            for (int a : nums) {
                if (a <= mid) ++cnt;
            }
            if (cnt <= mid) low = mid + 1;
            else high = mid - 1;
        }
        return low;
    }
}
