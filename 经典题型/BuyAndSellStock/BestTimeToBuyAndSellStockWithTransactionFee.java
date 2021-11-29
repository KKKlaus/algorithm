public class BestTimeToBuyAndSellStockWithTransactionFee {

    // dp[i][j]: i = 天数， j = 0 or 1表示有无股票
    // 这里可以考虑用两个变量代替dp减小空间复杂度
    public int maxProfit(int[] prices, int fee) {
        if (prices == null || prices.length == 0) return 0;
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0] - fee;
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i] - fee);
        }
        return dp[n - 1][0];
    }


    // 滚动数组
    public int maxProfit_2(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[2][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0] - fee;

        for (int i = 1; i < n; i++) {
            dp[i % 2][1] = Math.max(dp[(i - 1) % 2][1], dp[(i - 1) % 2][0] - prices[i] - fee);
            dp[i % 2][0] = Math.max(dp[(i - 1) % 2][0], dp[(i - 1) % 2][1] + prices[i]);
        }
        return dp[(n - 1) % 2][0];
    }
}
