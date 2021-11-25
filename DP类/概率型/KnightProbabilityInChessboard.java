public class KnightProbabilityInChessboard {

    public double knightProbability(int n, int K, int row, int column) {
        // dp[i][j][k] = dp[i'][j'][k-1]
        double[][][] dp = new double[n][n][K + 1];
        int[][] dirs = new int[][]{{1, 2}, {1, -2}, {-1, 2}, {-1, -2}, {2, 1}, {2, -1}, {-2, 1}, {-2, -1}};
        dp[row][column][0] = 1;

        for (int k = 1; k <= K; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    for (int[] dir : dirs) {
                        int lastX = i - dir[0];
                        int lastY = j - dir[1];
                        if (lastX < 0 || lastX >= n || lastY < 0 || lastY >= n) continue;
                        dp[i][j][k] += dp[lastX][lastY][k - 1] / 8;
                    }
                }
            }
        }

        double res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                res += dp[i][j][K];
            }
        }
        return res;
    }

    // 因为k只跟k-1有关，所以可以滚动数组
    public double knightProbability_optimized(int n, int K, int row, int column) {
        double[][] dp = new double[n][n];
        int[][] dirs = new int[][]{{1, 2}, {1, -2}, {-1, 2}, {-1, -2}, {2, 1}, {2, -1}, {-2, 1}, {-2, -1}};
        dp[row][column] = 1;

        for (int k = 1; k <= K; k++) {
            double[][] nextDP = new double[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    for (int[] dir : dirs) {
                        int lastX = i - dir[0];
                        int lastY = j - dir[1];
                        if (lastX < 0 || lastX >= n || lastY < 0 || lastY >= n) continue;
                        nextDP[i][j] += dp[lastX][lastY] / 8;
                    }
                }
            }
            dp = nextDP;
        }

        double res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                res += dp[i][j];
            }
        }
        return res;
    }
}
