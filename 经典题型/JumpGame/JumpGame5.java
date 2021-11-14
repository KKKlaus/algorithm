import java.util.Arrays;

// 不错的中文解释：https://www.youtube.com/watch?v=y5hRO6NvOHg
// 本质上是个有向图的问题
// 跟329是类似题！！！！！！！
public class JumpGame5 {

    // 递归dp (top-down DP)
    // 时间复杂度 O(nd)
    // 空间复杂度 O(n)
    public int maxJumps(int[] arr, int d) {
        int[] dp = new int[arr.length];
        int res = 1;
        for (int i = 0; i < arr.length; i++) {
            res = Math.max(res, recursion(dp, arr, i, d));
        }
        return res;
    }

    private int recursion(int[] dp, int[] arr, int cur, int d) {
        if (dp[cur] != 0) return dp[cur];
        int sum = 1;
        for (int i = cur + 1; i <= cur + d && i < arr.length && arr[i] < arr[cur]; i++) {
            sum = Math.max(sum, 1 + recursion(dp, arr, i, d));
        }
        for (int i = cur - 1; i >= cur - d && i >= 0 && arr[i] < arr[cur]; i--) {
            sum = Math.max(sum, 1 + recursion(dp, arr, i, d));
        }

        dp[cur] = sum;
        return dp[cur];
    }


    //  bottom-up DP: 每个dp都依赖于子问题，而子的逻辑是最低的优先，所以先按高度排序
    // 时间复杂度 O(nlogn排序 + nd)
    // 空间复杂度 O(2n)
    public int maxJumps_dp(int[] arr, int d) {
        int n = arr.length;
        int[][] arr2 = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr2[i][0] = arr[i];
            arr2[i][1] = i;
        }
        Arrays.sort(arr2, (a, b) -> (a[0] - b[0]));

        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int res = 1;
        for (int[] a : arr2) {
            int val = a[0], index = a[1];
            for (int j = index + 1; j <= index + d && j < n && arr[j] < arr[index]; j++) {
                dp[index] = Math.max(dp[index], 1 + dp[j]);
            }
            for (int j = index - 1; j >= index - d && j >= 0 && arr[j] < arr[index]; j--) {
                dp[index] = Math.max(dp[index], 1 + dp[j]);
            }
            res = Math.max(res, dp[index]);
        }

        return res;
    }
}
