public class BestTimeToBuyAndSellStock2 {

    // 没有规定transaction次数k -> 画曲线图
    public int maxProfit(int[] prices) {
        int res = 0;
        for (int i = 1; i < prices.length; i++) {
            res += Math.max(0 , prices[i] - prices[i - 1]);
        }
        return res;
    }
}
