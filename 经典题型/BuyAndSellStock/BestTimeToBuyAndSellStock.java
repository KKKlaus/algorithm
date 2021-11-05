public class BestTimeToBuyAndSellStock {

    // 只有一次交易次数k = 1
    public int maxProfit(int[] prices) {
        int min = prices[0], maxProfit = 0;
        for (int price : prices) {
            min = Math.min(min, price); // 贪心
            maxProfit = Math.max(maxProfit, price - min);
        }
        return maxProfit;
    }
}
