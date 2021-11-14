import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 跟JumpGame5同题型
public class LongestIncreasingPathInAMatrix {

    // 时间复杂度： O(mn)

    // 递归DP：top-down DP
    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m][n];

        int res = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res = Math.max(res, dfs(matrix, dp, i, j));
            }
        }
        return res;
    }

    private int dfs(int[][] matrix, int[][] dp, int x, int y) {
        if (dp[x][y] != 0) return dp[x][y];
        int res = 1;

        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1},{0, 1}};
        for(int[] dir : dirs) {
            int nx = x + dir[0], ny = y + dir[1];
            if (nx < 0 || nx >= matrix.length || ny < 0 || ny >= matrix[0].length || matrix[nx][ny] >= matrix[x][y]) continue;
            res = Math.max(res, 1 + dfs(matrix, dp, nx, ny));
        }

        dp[x][y] = res;
        return dp[x][y];
    }

    // bottom up DP
    int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    public int longestIncreasingPath_2(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m][n];


        int res = 1;
        List<Node> list = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                list.add(new Node(i, j, matrix[i][j]));
            }
        }

        Collections.sort(list, (a, b) -> (a.val - b.val));
        for (Node node : list) {
            int x = node.x, y = node.y, val = node.val;
            dp[x][y] = 1;
            for(int[] dir : dirs) {
                int nx = x + dir[0], ny = y + dir[1];
                if (nx < 0 || nx >= matrix.length || ny < 0 || ny >= matrix[0].length || matrix[nx][ny] >= matrix[x][y]) continue;
                dp[x][y] = Math.max(dp[x][y], 1 + dp[nx][ny]);
            }
            res = Math.max(res, dp[x][y]);
        }

        return res;
    }

    class Node {
        int x;
        int y;
        int val;
        Node(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }
}
