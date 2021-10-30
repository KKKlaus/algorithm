package SubArray;

public class MinimumSizeSubarraySum {

    // 二分答案：跟Shortest Subarray with Sum at Least K 这道题可以一样的思路，只不过那道题有负数，这道题都是正数
    // 导致isValid()那道题需要维护一个堆来做
    public int minSubArrayLen(int target, int[] nums) {
        int start = 1, end = nums.length;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (isValid(nums, mid, target)) {
                end = mid;
            } else {
                start = mid;
            }
        }

        if (isValid(nums, start, target)) return start;
        if (isValid(nums, end, target)) return end;

        return 0;
    }

    private boolean isValid(int[] nums, int len, int target) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (i >= len) {
                sum -= nums[i - len];
            }
            if (sum >= target) return true;
        }
        return false;
    }

    // 第二个方法直接有效
    public int minSubArrayLen_2(int target, int[] nums) {
        int preSum = 0, start = 0;
        int minLen = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            preSum += nums[i];
            while (preSum >= target) {
                minLen = Math.min(minLen, i - start + 1);
                preSum -= nums[start];
                start++;
            }
        }

        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
}
