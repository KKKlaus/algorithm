import java.util.LinkedList;
import java.util.Queue;

public class NumberOfIslands {

    public int numIslands_bfs(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        int[][] dirs = new int[][]{{1,0}, {-1,0},{0,1},{0,-1}};
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '0' || visited[i][j]) continue;
                queue.offer(new int[]{i,j});
                visited[i][j] = true;
                while (!queue.isEmpty()) {
                    int[] node = queue.poll();
                    for (int[] dir : dirs) {
                        int next_x = node[0] + dir[0], next_y = node[1] + dir[1];
                        if (next_x < 0 || next_x >= m || next_y < 0 || next_y >= n || visited[next_x][next_y] || grid[next_x][next_y] == '0') continue;
                        queue.offer(new int[]{next_x, next_y});
                        visited[next_x][next_y] = true;
                    }
                }
                count++;
            }
        }
        return count;
    }

    public int numIslands_dfs(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '0' || visited[i][j]) continue;
                dfs(grid, i, j, visited);
                count++;
            }
        }
        return count;
    }

    public void dfs(char[][] grid, int x, int y, boolean[][] visited) {
        if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length) return;
        if (grid[x][y] == '0' || visited[x][y]) return;
        visited[x][y] = true;
        dfs(grid, x + 1, y, visited);
        dfs(grid, x - 1, y, visited);
        dfs(grid, x, y + 1, visited);
        dfs(grid, x, y - 1, visited);
    }

    // 方法3：UnionFind， 注意m * n穿过去，每次x * N + y, N为列
    public int numIslands_3(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        int[][] dirs = new int[][]{{-1,0}, {1,0}, {0,1}, {0,-1}};
        UnionFind uf = new UnionFind(grid);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '0') continue;
                for (int[] dir : dirs) {
                    int next_x = i + dir[0], next_y = j + dir[1];
                    if (next_x < 0 || next_x >= m || next_y < 0 || next_y >= n || grid[next_x][next_y] == '0') continue;
                    int id1 = i*n+j;
                    int id2 = next_x*n+next_y;
                    uf.union(id1, id2);
                }
            }
        }
        return uf.count;
    }

    class UnionFind {

        int[] parent;
        int count;
        UnionFind(char[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            parent = new int[m*n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == '1') {
                        int id = i * n + j;
                        parent[id] = id;
                        count++;
                    }
                }
            }
        }

        public int find(int x) {
            if (parent[x] == x) return x;
            return find(parent[x]);
        }

        public void union(int x, int y) {
            int parent_x = find(x);
            int parent_y = find(y);
            if (parent_x == parent_y) return;
            parent[parent_x] = parent_y;
            count--;
        }

    }
}
