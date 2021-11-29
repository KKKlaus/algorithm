public class BestTimeToBuyAndSellStock4 {
    // 如果不懂请看解析https://www.youtube.com/watch?v=oDhu5uGq_ic&t=112s

    // k 是transaction的意思，所以dp[k][0] 都等于0，因为完成一次transaction至少需要2天
    public int maxProfit(int K, int[] prices) {
        int n = prices.length;
        if (K == 0 || n == 0) return 0;
        int[][] dp = new int[K + 1][n];
        // dp[k][i] = max {dp[k][i - 1], max{dp[k - 1][j] + prices[i] - prices[j]}}

        for (int k = 1; k <= K; k++) {
            int maxVal = dp[k - 1][0] - prices[0];
            for (int i = 1; i < n; i++) {
                // int maxVal = 0;
                // for (int j = 0; j < i; j++) {
                //     maxVal = Math.max(maxVal, dp[k - 1][j] - prices[j] + prices[i]);
                // }
                dp[k][i] = Math.max(dp[k][i - 1], maxVal + prices[i]);
                maxVal = Math.max(maxVal, dp[k - 1][i] - prices[i]);
            }
        }
        return dp[K][n - 1];
    }
}
