public class JumpGame {

    // 方法2：贪心
    // 这里i < nums.length 或 nums.length - 1都可以
    public boolean canJump(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i <= max) {
                max = Math.max(max, i + nums[i]);
            }
        }
        return max >= nums.length - 1;
    }


    // 方法1：TLE  O(2^n): 我们从起点0到终点n一共有2^n种不同的路线，因为每个石头可以选择踩或者不睬.
    public boolean canJump2(int[] nums) {
        return helper(nums, 0);
    }

    public boolean helper(int[] nums, int pos) {
        if (pos == nums.length - 1) return true;
        int furthest = Math.min(pos + nums[pos], nums.length - 1);
        for (int next = pos + 1; next <= furthest; next++) {
            if (helper(nums, next)) {
                return true;
            }
        }
        return false;
    }
}
