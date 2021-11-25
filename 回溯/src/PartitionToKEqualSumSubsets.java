public class PartitionToKEqualSumSubsets {

    // O（k * 2^n） 个人认为会稍微小一点，因为每找到一个，后面不是2^n,但最坏情况是每次就一个
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int num : nums) sum += num;
        if (sum % k != 0) return false;
        if (nums.length < k) return false;
        int target = sum / k;
        return backtrack(nums, new boolean[nums.length], 0, k, 0, target);
    }

    private boolean backtrack(int[] nums, boolean[] used, int start, int k, int curSum, int target) {
        if (k == 0) return true;  // 其实k = 1就可以了，最后一块肯定是等于target的
        if (curSum > target) return false;
        if (curSum == target) return backtrack(nums, used, 0, k - 1, 0, target);

        for (int i = start; i < nums.length; i++) {
            if (used[i]) continue;
            used[i] = true;
            if (backtrack(nums, used, i + 1, k, curSum + nums[i], target)) return true;
            used[i] = false;
        }

        return false;
    }
}
