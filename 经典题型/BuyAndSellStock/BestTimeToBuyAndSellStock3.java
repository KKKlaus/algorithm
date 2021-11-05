public class BestTimeToBuyAndSellStock3 {

    public int maxProfit(int[] prices) {
        // dp[k][i] = max{dp[k][i-1], max{dp[k-1][j] + prices[i] - prices[j]};
        // k = transaction, i = days
        int n = prices.length;
        int[][] dp = new int[3][n];
        for (int k = 1; k <= 2; k++) {
            int maxDiff = dp[k - 1][0] - prices[0];
            for (int i = 1; i < n; i++) {
                dp[k][i] = Math.max(dp[k][i - 1], maxDiff + prices[i]);
                maxDiff = Math.max(maxDiff, dp[k - 1][i] - prices[i]);
            }
        }
        return dp[2][n - 1];
    }


    // 巧妙方法
    //详情见评论第一条 https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/discuss/39611/Is-it-Best-Solution-with-O(n)-O(1).

    //Here, the oneBuy keeps track of the lowest price, and oneBuyOneSell keeps track of the biggest profit we could get.
    //Then the tricky part comes, how to handle the twoBuy? Suppose in real life, you have bought and sold a stock and made $100 dollar profit. When you want to purchase a stock which costs you $300 dollars, how would you think this? You must think, um, I have made $100 profit, so I think this $300 dollar stock is worth $200 FOR ME since I have hold $100 for free.
    //There we go, you got the idea how we calculate twoBuy!! We just minimize the cost again!! The twoBuyTwoSell is just making as much profit as possible.
    //Hope this explanation helps other people to understand this!
//var maxProfit = function(prices) {
//  let oneBuyOneSell = 0;
//  let twoBuyTwoSell = 0;
//  let oneBuy = Number.POSITIVE_INFINITY
//  let twoBuy = Number.POSITIVE_INFINITY;
//
//  for(let i = 0; i < prices.length; i++) {
//    const p = prices[i];
//    oneBuy = Math.min(oneBuy, p);
//    oneBuyOneSell = Math.max(oneBuyOneSell, p - oneBuy);
//    twoBuy = Math.min(twoBuy, p - oneBuyOneSell);
//    twoBuyTwoSell = Math.max(twoBuyTwoSell, p - twoBuy);
//  }
//
//  return twoBuyTwoSell;
//};
}
