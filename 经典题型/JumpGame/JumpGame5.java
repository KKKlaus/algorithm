import java.util.*;

// 不错的中文解释：https://www.youtube.com/watch?v=y5hRO6NvOHg
// 本质上是个有向无环图的问题: 可以画一个有向图，其实就是求最长路径
// 跟329是类似题！！！！！！！
public class JumpGame5 {

    // 递归dp (top-down DP): dfs + memo
    // 时间复杂度 O(nd)
    // 空间复杂度 O(n)
    public int maxJumps(int[] arr, int d) {
        int n = arr.length;
        int[] memo = new int[n];
        int res = 1;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, dfs(arr, d, memo, i));
        }
        return res;
    }

    private int dfs(int[] arr, int d, int[] memo, int cur) {
        if (memo[cur] != 0) return memo[cur];
        int res = 1;
        for (int i = cur + 1; i < arr.length && i <= cur + d && arr[i] < arr[cur]; i++) {
            res = Math.max(res, 1 + dfs(arr, d, memo, i));
        }
        for (int i = cur - 1; i >= 0 && i >= cur - d && arr[i] < arr[cur]; i--) {
            res = Math.max(res, 1 + dfs(arr, d, memo, i));
        }

        memo[cur] = res;
        return res;
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


    // 方法3：跟329一样可用拓扑排序
    public int maxJumps_2(int[] arr, int d) {
        int n = arr.length;
        int[] indegree = new int[n];
        HashMap<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            map.put(i, new ArrayList<>());
            for (int j = i + 1; j < n && j <= i + d && arr[i] > arr[j]; j++) {
                indegree[j]++;
                map.get(i).add(j);
            }
            for (int j = i - 1; j >= 0 && j >= i - d && arr[i] > arr[j]; j--) {
                indegree[j]++;
                map.get(i).add(j);
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int curIndex = queue.poll();
                for (int nei : map.get(curIndex)) {
                    indegree[nei]--;
                    if (indegree[nei] == 0) {
                        queue.offer(nei);
                    }
                }
            }
            step++;
        }

        return step;
    }
}
