import java.util.Arrays;

public class CoinChange {

    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        int[] dp = new int[amount + 1]; // dp[i] = min number of coins to get the amount i
        // dp[i] = for j : coints -> dp[i - j] + 1

        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for (int i = 1; i < amount + 1; i++) {
            for (int coin : coins) {
                if (i >= coin) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }
}
