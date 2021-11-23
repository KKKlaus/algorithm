import java.util.LinkedList;
import java.util.Queue;

public class ShortestDistancefromAllBuildings {


    // 思路： 从房子开始构建distance每次叠加
    // 还要主要reach防止到不了的情况
    public int shortestDistance(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        int[][] distance = new int[m][n];
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, - 1}, {0, 1}};
        int[][] reach = new int[m][n];
        int buildingNum = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    buildingNum++;
                    Queue<int[]> q = new LinkedList<>();
                    boolean[][] visited = new boolean[m][n];
                    q.offer(new int[]{i, j});
                    visited[i][j] = true;
                    int level = 0;
                    while (!q.isEmpty()) {
                        int size = q.size();
                        level++;
                        for (int t = 0; t < size; t++) {
                            int[] cur = q.poll();
                            for (int[] dir : dirs) {
                                int nx = cur[0] + dir[0], ny = cur[1] + dir[1];
                                if (nx < 0 || nx >= m || ny < 0 || ny >= n || grid[nx][ny] == 1 || grid[nx][ny] == 2 || visited[nx][ny]) continue;
                                distance[nx][ny] += level;
                                q.offer(new int[]{nx, ny});
                                visited[nx][ny] = true;
                                reach[nx][ny]++;
                            }
                        }
                    }
                }
            }
        }

        int shortest = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0 && reach[i][j] == buildingNum) {
                    shortest = Math.min(shortest, distance[i][j]);
                }
            }
        }

        return shortest == Integer.MAX_VALUE ? -1 : shortest;
    }
}
