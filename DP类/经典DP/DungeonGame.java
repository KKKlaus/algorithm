public class DungeonGame {

    // dp 从右下角到左上角， 为什么：We are trying to find minHP at [0][0]; this is the end goal.
    // The base case occurs at [m -1][n - 1], so DP must begin there and work its way to the end goal.
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length, n = dungeon[0].length;
        int[][] dp = new int[m + 1][n + 1];
        for (int j = 0; j < n + 1; j++) {
            dp[m][j] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < m + 1; i++) {
            dp[i][n] = Integer.MAX_VALUE;
        }
        dp[m][n - 1] = 1;
        dp[m - 1][n] = 1;
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                dp[i][j] = Math.max(1, Math.min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j]);
            }
        }

        return dp[0][0];
    }



    // 自己写的dfs -> TLE
    int maxVal = Integer.MIN_VALUE;
    public int calculateMinimumHP_me(int[][] dungeon) {
        dfs(dungeon, 0, 0, 0, 0);

        if (maxVal >= 0) return 1;
        else return -maxVal + 1;
    }

    private void dfs(int[][] dungeon, int x, int y, int pathSum, int pathMin) {
        if (x < 0 || x >= dungeon.length || y < 0 || y >= dungeon[0].length || pathMin < maxVal) return;

        pathSum += dungeon[x][y];
        pathMin = Math.min(pathMin, pathSum);

        if (x == dungeon.length - 1 && y == dungeon[0].length - 1) {
            maxVal = Math.max(maxVal, pathMin);
        }

        dfs(dungeon, x + 1, y, pathSum, pathMin);
        dfs(dungeon, x, y + 1, pathSum, pathMin);
    }
}
