public class PartitionEqualSubsetSum {

    // 0-1背包问题，dp[i][j]为前i个元素，刚好能装满j的空间

    // 这个代码理解的清楚点
    public boolean canPartition_3(int[] nums) {
        int n = nums.length, sum = 0;
        for (int num : nums) sum += num;
        if (sum % 2 != 0 || n < 2) return false;
        int target = sum / 2;
        boolean[][] dp = new boolean[n + 1][target + 1]; // the first n element -> can their sum equal to target ?
        for (int i = 1; i < n+1; i++) {
            dp[i][0] = true;
        }
        for (int j = 1; j < target+1; j++) {
            dp[0][j] = false;
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < target + 1; j++) {
                if (j >= nums[i - 1]) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][target];
    }

    // 注意跟 Partition to K Equal Sum Subsets也可以采用回溯，但是这里会超时
    public boolean canPartition(int[] nums) {
        int n = nums.length, sum = 0;
        for (int num : nums) sum += num;
        if (sum % 2 != 0 || n < 2) return false;
        int target = sum / 2;
        boolean[][] dp = new boolean[n + 1][target + 1]; // the first n element -> can their sum equal to target ?
        dp[0][0] = true;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= target; j++) {
                dp[i][j] = dp[i - 1][j] | (j >= nums[i - 1] && dp[i - 1][j - nums[i - 1]]);
            }
        }

        return dp[n][target];
    }

    // 滚动数组一下
    public boolean canPartition_2(int[] nums) {
        int n = nums.length, sum = 0;
        for (int num : nums) sum += num;
        if (sum % 2 != 0 || n < 2) return false;
        int target = sum / 2;
        boolean[][] dp = new boolean[2][target + 1]; // the first n element -> can their sum equal to target ?
        dp[0][0] = true;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= target; j++) {
                dp[i % 2][j] = dp[(i - 1) % 2][j] | (j >= nums[i - 1] && dp[(i - 1) % 2][j - nums[i - 1]]);
            }
        }

        return dp[n % 2][target];
    }
}
