import java.util.LinkedList;
import java.util.Queue;

public class ShortestBridge {

    // 注意number of islands类的题一定要注意把visited set true 不然会有TLE -> 需要看看visited之后有没有set true

    // 方法：先用dfs把第一个岛全部进队且标记visited，然后bfs算距离
    int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    public int shortestBridge(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];
        boolean foundFirst = false;
        for (int i = 0; i < m; i++) {
            if (foundFirst) break;
            for (int j = 0; j < n; j++) {
                if (grid[i][j] ==1) {
                    dfs(grid, i, j, visited, queue);
                    foundFirst = true;
                    break;
                }
            }
        }

        int res = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                for (int[] dir : dirs) {
                    int nx = cur[0] + dir[0], ny = cur[1] + dir[1];
                    if (nx < 0 || nx >= n || ny < 0 || ny >= n || visited[nx][ny]) continue;
                    if (grid[nx][ny] == 1) return res;
                    else {
                        visited[nx][ny] = true;
                        queue.offer(new int[]{nx, ny});
                    }
                }
            }
            res++;
        }
        return -1;
    }

    private void dfs(int[][] grid, int x, int y, boolean[][] visited, Queue<int[]> queue) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || visited[x][y] || grid[x][y] == 0) return;
        visited[x][y] = true;
        queue.offer(new int[]{x,y});
        dfs(grid, x + 1, y, visited, queue);
        dfs(grid, x - 1, y, visited, queue);
        dfs(grid, x, y + 1, visited, queue);
        dfs(grid, x, y - 1, visited, queue);
    }
}
