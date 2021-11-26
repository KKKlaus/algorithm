import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class PathWithMaximumMinimumValue {
    //O(m*n*log(m*n))
    // 方法1 ： pq 贪心，这个应该不算dijkstra算法，只不过很像
    int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    public int maximumMinimumPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[2] - a[2]);
        pq.offer(new int[]{0, 0, grid[0][0]});

        boolean[][] visited = new boolean[m][n];

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int row = cur[0], col = cur[1], val = cur[2];
            if (row == m - 1 && col == n - 1) return val;
            for (int[] dir : dirs) {
                int nx = row + dir[0], ny = col + dir[1];
                if (nx < 0 || nx >= m || ny < 0 || ny >= n || visited[nx][ny]) continue;
                pq.offer(new int[]{nx, ny, Math.min(val, grid[nx][ny])});
                visited[nx][ny] = true;
            }
        }

        return -1;
    }

    // 方法2： BS + BFS  O(m*n log1e9)
    public int maximumMinimumPath_2(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int start = 0, end = (int) 1e9;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (bfs(grid, mid)) {
                start = mid;
            } else {
                end = mid;
            }
        }
        return bfs(grid, start) ? start : end;
    }

    private boolean bfs(int[][] grid, int min) {
        if (grid[0][0] < min) return false;   // 注意这个边界情况 [[0,1,0,0,0,1],[0,1,1,0,0,0],[0,0,1,1,0,1],[0,1,1,1,1,0],[1,1,1,1,1,1]]
        int m = grid.length, n = grid[0].length;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0});

        boolean[][] visited = new boolean[m][n];
        visited[0][0] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1];
            if (x == m - 1 && y == n - 1) return true;
            for (int[] dir : dirs) {
                int nx = x + dir[0], ny = y + dir[1];
                if (nx < 0 || nx >= m || ny < 0 || ny >= n || visited[nx][ny]) continue;
                if (grid[nx][ny] >= min) {
                    q.offer(new int[]{nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }

        return false;
    }
}
