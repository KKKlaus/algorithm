public class MinimumCostForTickets {

    public int mincostTickets(int[] days, int[] costs) {
        int maxDays = days[days.length - 1];
        int[] dp = new int[maxDays + 1];
        boolean[] travel = new boolean[maxDays + 1];
        for (int day : days) travel[day] = true;
        dp[0] = 0;

        for (int i = 1; i < maxDays + 1; i++) {
            if (!travel[i]) {
                dp[i] = dp[i - 1];
                continue;
            }
            dp[i] = costs[0] + dp[i - 1];
            dp[i] = Math.min(dp[i], dp[Math.max(i - 7, 0)] + costs[1]);
            dp[i] = Math.min(dp[i], dp[Math.max(i - 30, 0)] + costs[2]);
        }
        return dp[maxDays];
    }

    // 滚动
    public int mincostTickets_2(int[] days, int[] costs) {
        int maxDays = days[days.length - 1];
        int[] dp = new int[30];
        boolean[] travel = new boolean[maxDays + 1];
        for (int day : days) travel[day] = true;
        dp[0] = 0;

        for (int i = 1; i < maxDays + 1; i++) {
            if (!travel[i]) {
                dp[i % 30] = dp[(i - 1) % 30];
                continue;
            }
            dp[i % 30] = costs[0] + dp[(i - 1) % 30];
            dp[i % 30] = Math.min(dp[i % 30], dp[Math.max(i - 7, 0) % 30] + costs[1]);
            dp[i % 30] = Math.min(dp[i % 30], dp[Math.max(i - 30, 0) % 30] + costs[2]);
        }
        return dp[maxDays % 30];
    }
}
