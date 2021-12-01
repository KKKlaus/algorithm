public class DecodeWays {


    // 方法1：自己写的recursion， O(2^n): A char may be decoded alone or by pairing with the next char.
    int ans = 0;
    public int numDecodings(String s) {
        backtrack(s, 0);
        return ans;
    }


    private void backtrack(String s, int start) {
        if (start == s.length()) {
            ans++;
            return;
        }

        for (int i = start; i <= start + 1 && i < s.length(); i++) {
            String str = s.substring(start, i + 1);
            if (str.charAt(0) == '0') continue;
            int num = Integer.valueOf(str);
            if (num >= 1 && num <= 26) {
                backtrack(s, i + 1);
            }
        }
    }


    // 方法2：dp
    public int numDecodings_2(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1;

        for (int i = 2; i <= n; i++) {
            int first = Integer.valueOf(s.substring(i - 1, i));
            int second = Integer.valueOf(s.substring(i - 2, i));
            if (first != 0) {
                dp[i] += dp[i - 1];
            }
            if (second >= 10 && second <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[n];
    }

    // 滚动数组: 注意第61行，因为这里dp是+=所以每次要置0 : dp[i % 3] = 0
    // 之前都是dp = ,所以 dp[i % 2] = dp[(i-1) % 2]就不用置0
    public int numDecodings_3(String s) {
        int n = s.length();
        int[] dp = new int[3];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1;

        for (int i = 2; i <= n; i++) {
            int first = Integer.valueOf(s.substring(i - 1, i));
            int second = Integer.valueOf(s.substring(i - 2, i));
            dp[i % 3] = 0;
            if (first != 0) {
                dp[i % 3] += dp[(i - 1) % 3];
            }
            if (second >= 10 && second <= 26) {
                dp[i % 3] += dp[(i - 2) % 3];
            }
        }
        return dp[n % 3];
    }
}
