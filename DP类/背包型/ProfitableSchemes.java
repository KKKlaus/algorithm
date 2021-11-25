public class ProfitableSchemes {

    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        int m = group.length;
        int mod = (int)1e9 + 7;
        int[][][] dp = new int[m + 1][n + 1][minProfit + 1]; //  group - people = profit
        // for first i crime(group) with j people and at least k profit, what is total schemes can be chosen.
        // dp[i][j][k] = dp[i - 1][j][k] + dp[i - 1][j - group[i - 1]][k - profit[i - 1]]
        dp[0][0][0] = 1; // dp[0][x][y] = 0;

        for (int i = 1; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                for (int k = 0; k <= minProfit; k++) {
                    dp[i][j][k] = dp[i - 1][j][k];
                    if (j >= group[i - 1]) {
                        int prevK = Math.max(k - profit[i - 1], 0);
                        dp[i][j][k] = (dp[i][j][k] + dp[i - 1][j - group[i - 1]][prevK]) % mod;
                    }
                }
            }
        }

        int res = 0;
        for (int i = 0; i <= n; i++) {
            res = (res + dp[m][i][minProfit]) % mod;
        }
        return res;
    }

    //滚动数组， 只有dp里面带i的都%2
    public int profitableSchemes_2(int n, int minProfit, int[] group, int[] profit) {
        int m = group.length;
        int mod = 1000000007;
        int[][][] dp = new int[2][n + 1][minProfit + 1]; //  group - people = profit
        // for first i crime(group) with j people and at least k profit, what is total schemes can be chosen.
        // dp[i][j][k] = dp[i - 1][j][k] + dp[i - 1][j - group[i - 1]][k - profit[i - 1]]
        dp[0][0][0] = 1; // dp[0][x][y] = 0;

        for (int i = 1; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                for (int k = 0; k <= minProfit; k++) {
                    dp[i % 2][j][k] = dp[(i - 1) % 2][j][k];
                    if (j >= group[i - 1]) {
                        int prevK = Math.max(k - profit[i - 1], 0);
                        dp[i % 2][j][k] = (dp[i % 2][j][k] + dp[(i - 1) % 2][j - group[i - 1]][prevK]) % mod;
                    }
                }
            }
        }

        int res = 0;
        for (int i = 0; i <= n; i++) {
            res = (res + dp[m % 2][i][minProfit]) % mod;
        }
        return res;
    }
}
