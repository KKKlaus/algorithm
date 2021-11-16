import java.util.HashSet;
import java.util.Set;

public class NumberOfDistinctIslands_21年10月26号 {

    // 时间复杂度 O(M*N)每个点访问一次
    // 空间复杂度 O(M*N) visited

    int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    public int numDistinctIslands(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        Set<String> set = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0 || visited[i][j]) continue;
                StringBuilder sb = new StringBuilder();
                dfs(grid, i, j, 0, 0, visited, sb);

                set.add(new String(sb));
            }
        }
        return set.size();
    }

    private void dfs(int[][] grid, int x, int y, int posX, int posY, boolean[][] visited, StringBuilder sb) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] == 0 || visited[x][y]) return;

        visited[x][y] = true;
        sb.append(posX + "," + posY);
        for (int[] dir : dirs) {
            dfs(grid, x + dir[0], y + dir[1], posX + dir[0], posY + dir[1], visited, sb);
        }
    }


    // 不用visited，省一些空间复杂度 < O(M*N)看set装了多少了，最坏情况就是岛里面布满了尽可能多的不同形状的岛屿
    public int numDistinctIslands_2(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        Set<String> set = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) continue;
                StringBuilder sb = new StringBuilder();
                dfs(grid, i, j, 0, 0, sb);

                set.add(new String(sb));
            }
        }
        return set.size();
    }

    private void dfs(int[][] grid, int x, int y, int posX, int posY, StringBuilder sb) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] == 0) return;

        grid[x][y] = 0;
        sb.append(posX + "," + posY);
        for (int[] dir : dirs) {
            dfs(grid, x + dir[0], y + dir[1], posX + dir[0], posY + dir[1], sb);
        }
    }
}
