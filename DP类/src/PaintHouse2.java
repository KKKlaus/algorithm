public class PaintHouse2 {

    // 无任何优化: 时间O(n*k*k)
    public int minCostII(int[][] costs) {
        int n = costs.length, k = costs[0].length;
        int[][] dp = new int[n][k];
        dp[0] = costs[0];

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < k; j++) {
                int min = Integer.MAX_VALUE;
                for (int t = 0; t < k; t++) {
                    if (t == j) continue;
                    min = Math.min(min, dp[i - 1][t]);
                }
                dp[i][j] = min + costs[i][j];
            }
        }

        int min = Integer.MAX_VALUE;
        for (int c : dp[n - 1]) {
            min = Math.min(min, c);
        }
        return min;
    }

    // 优化1：滚动数组 都%2
    // 优化2：直接用原数组costs，只不过会改动原数组

    // 优化3：利用第二小的数
    public int minCostII_2(int[][] costs) {
        int n = costs.length, k = costs[0].length;

        // all prevMin, curMin.... are index of color
        int prevSecondMin = -1, prevMin = -1;
        for (int j = 0; j < k; j++) {
            if (prevMin == -1 || costs[0][j] <= costs[0][prevMin]) {
                prevSecondMin = prevMin;
                prevMin = j;
            } else if (prevSecondMin == -1 || costs[0][j] <= costs[0][prevSecondMin]) {
                prevSecondMin = j;
            }
        }

        for (int i = 1; i < n; i++) {
            int curSecondMin = -1, curMin = -1;
            for (int j = 0; j < k; j++) {
                if (j == prevMin) {
                    costs[i][j] = costs[i - 1][prevSecondMin] + costs[i][j];
                } else {
                    costs[i][j] = costs[i - 1][prevMin] + costs[i][j];
                }

                if (curMin == -1 || costs[i][j] <= costs[i][curMin]) {
                    curSecondMin = curMin;
                    curMin = j;
                } else if (curSecondMin == -1 || costs[i][j] <= costs[i][curSecondMin]) {
                    curSecondMin = j;
                }
            }

            prevMin = curMin;
            prevSecondMin = curSecondMin;
        }


        int min = Integer.MAX_VALUE;
        for (int c : costs[n - 1]) {
            min = Math.min(min, c);
        }
        return min;
    }
}
