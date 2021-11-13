import java.util.LinkedList;
import java.util.Queue;


// BFS是用来搜索最短径路的解是比较合适的。 DFS适合搜索全部的解
public class TheMaze {

    // 主要就是注意第22行巧妙方法算接下来4个点
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        int m = maze.length, n = maze[0].length;
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        Queue<int[]> queue = new LinkedList<>();
        // Set<String> set = new HashSet<>(); set.add(start[0] + "," + start[1]); 字符串的处理非常慢
        boolean[][] visited = new boolean[m][n];
        queue.offer(start);
        visited[start[0]][start[1]] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0], y = cur[1];
            if (x == destination[0] && y == destination[1]) return true;
            for (int i = 0; i < 4; i++) {
                int nx = x, ny = y;
                while (nx >= 0 && nx < m && ny >= 0 && ny < n && maze[nx][ny] == 0) {   // 这里非常巧妙
                    nx += dirs[i][0];
                    ny += dirs[i][1];
                }
                nx -= dirs[i][0];
                ny -= dirs[i][1];
                if (!visited[nx][ny]) {
                    queue.offer(new int[]{nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }
        return false;
    }

}
