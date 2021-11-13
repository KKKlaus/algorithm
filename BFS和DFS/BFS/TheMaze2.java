import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class TheMaze2 {


    // 构造distance[][] 来实时表示目前最短距离

    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int m = maze.length, n = maze[0].length;
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        int[][] dist = new int[m][n];
        for (int[] a : dist) {
            Arrays.fill(a, - 1);
        }
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(start);
        dist[start[0]][start[1]] = 0;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0], y = cur[1];
            for (int i = 0; i < 4; i++) {
                int nx = x, ny = y;
                int count = dist[x][y];
                while (nx >= 0 && nx < m && ny >= 0 && ny < n && maze[nx][ny] == 0) {   // 这里非常巧妙
                    nx += dirs[i][0];
                    ny += dirs[i][1];
                    count++;
                }
                nx -= dirs[i][0];
                ny -= dirs[i][1];
                count--;
                if (dist[nx][ny] == -1 || dist[nx][ny] > count) {
                    queue.offer(new int[]{nx, ny});
                    dist[nx][ny] = count;
                }
            }
        }
        return dist[destination[0]][destination[1]];
    }
}
