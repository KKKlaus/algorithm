public class LongestCommonSubsequence {

    // 空间和时间都是O(m * n)
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) dp[i][j] = dp[i - 1][j - 1] + 1;
                else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[m][n];
    }


    // 空间优化为O(Math.min(m,n))
    public int longestCommonSubsequence_2(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        if (n > m) return longestCommonSubsequence_2(text2, text1);
        int[][] dp = new int[2][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) dp[i % 2][j] = dp[(i - 1) % 2][j - 1] + 1;
                else dp[i % 2][j] = Math.max(dp[(i - 1) % 2][j], dp[i % 2][j - 1]);
            }
        }
        return dp[m % 2][n];

    }
}
