public class LongestValidParentheses {

    // dp : https://leetcode.com/problems/longest-valid-parentheses/discuss/14133/My-DP-O(n)-solution-without-using-stack
    public int longestValidParentheses(String s) {
        int n = s.length();
        int[] dp = new int[n];
        int curMax = 0;

        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i - 2 >= 0) ? (dp[i - 2] + 2) : 2;
                } else {
                    // (())
                    int prevLeftIndex = i - dp[i - 1] - 1;
                    if (prevLeftIndex >= 0 && s.charAt(prevLeftIndex) == '(') {
                        dp[i] = prevLeftIndex - 1 >= 0 ? dp[i - 1] + 2 + dp[prevLeftIndex - 1] : dp[i - 1] + 2;
                    }
                }
                curMax = Math.max(curMax, dp[i]);
            }
        }
        return curMax;
    }
}
