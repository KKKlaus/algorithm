public class NumberOfClosedIslands {


    public int closedIsland(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 || visited[i][j]) continue;
                if (dfs(grid, i, j, visited)) count++;
            }
        }
        return count;
    }

    public boolean dfs(int[][] grid, int x, int y, boolean[][] visited) {
        if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length) return false; // 出界肯定错
        if (x == 0 || y == 0 || x == grid.length - 1 || y == grid[0].length - 1) {    // 最边缘必须是1
            if (grid[x][y] == 0) return false;
        }
        if (grid[x][y] == 1 || visited[x][y]) return true;  // 内部就随便了
        visited[x][y] = true;
        // "&" will evaluate both side even the left part is false
        // "&&" will ignore the right part if the left part is false
        // && 第一个为false后，后面就不跑了
        return dfs(grid, x + 1, y, visited)
                & dfs(grid, x - 1, y, visited)
                & dfs(grid, x, y + 1, visited)
                & dfs(grid, x, y - 1, visited);
    }
}
