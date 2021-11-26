import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class PathWithMinimumEffort {
    //O(m*n*log(m*n))
    // 利用的就是Dijkstra的思想：每次都是取权重最小的，而这里的权重不是简单的路径权重，而是最大的effort = Math.max(cur[2], Math.abs(heights[nx][ny] - heights[cur[0]][cur[1]])
    int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    public int minimumEffortPath(int[][] heights) {
        int m = heights.length, n = heights[0].length;
        int[][] minEffort = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(minEffort[i], Integer.MAX_VALUE);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[2] - b[2]));
        pq.offer(new int[]{0, 0, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            if (cur[0] == m - 1 && cur[1] == n - 1) return cur[2];
            for (int[] dir : dirs) {
                int nx = cur[0] + dir[0], ny = cur[1] + dir[1];
                if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
                int effort = Math.max(cur[2], Math.abs(heights[nx][ny] - heights[cur[0]][cur[1]]));   // 这一行注意取最大值
                if (effort < minEffort[nx][ny]) {
                    minEffort[nx][ny] = effort;
                    pq.offer(new int[]{nx, ny, effort});
                }
            }
        }
        return -1;
    }

    // 方法2： BS + BFS  O(m*n log1e6)
    public int minimumEffortPath_2(int[][] heights) {
        int m = heights.length, n = heights[0].length;
        int start = 0, end = (int) 1e6;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (bfs(heights, mid)) {
                end = mid;
            } else {
                start = mid;
            }
        }
        return bfs(heights, start) ? start : end;
    }

    private boolean bfs(int[][] heights, int min) {
        int m = heights.length, n = heights[0].length;

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
                if (Math.abs(heights[nx][ny] - heights[x][y]) <= min) {
                    q.offer(new int[]{nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }

        return false;
    }
}
