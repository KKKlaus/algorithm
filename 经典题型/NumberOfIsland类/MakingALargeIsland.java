import java.util.HashSet;
import java.util.Set;

public class MakingALargeIsland {


    // 跟 Longest Consecutive Sequence 一个做法，注意Unionfind里的union方法和size[]
    public static void main(String[] args) {
        MakingALargeIsland t = new MakingALargeIsland();
        t.test();
    }

    public void test() {
        int[][] grid = new int[][]{{0,1},{1,1}};
        largestIsland(grid);
    }

    int[][] dirs = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
    public int largestIsland(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        UnionFind uf = new UnionFind(grid);
        int maxArea = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) continue;
                for (int[] dir : dirs) {
                    int nx = i + dir[0], ny = j + dir[1];
                    if (nx < 0 || nx >= m || ny < 0 || ny >= n || grid[nx][ny] == 0) continue;
                    uf.union(i * n + j, nx * n + ny);
                    int root = uf.find(i * n + j);
                    maxArea = Math.max(maxArea, uf.area[root]);
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) continue;
                int count = 1;
                Set<Integer> root_set = new HashSet<>();
                for (int[] dir : dirs) {
                    int nx = i + dir[0], ny = j + dir[1];
                    if (nx < 0 || nx >= m || ny < 0 || ny >= n || grid[nx][ny] == 0) continue;
                    int root = uf.find(nx * n + ny);
                    root_set.add(root);
                }
                for (int root : root_set) {
                    count += uf.area[root];
                }
                maxArea = Math.max(maxArea, count);
            }
        }
        return maxArea;
    }

    class UnionFind {

        int[] parent;
        int[] area;

        public UnionFind(int[][] grid) {
            int m = grid.length, n = grid[0].length;
            parent = new int[m * n];
            area = new int[m * n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    parent[i * n + j] = i * n + j;
                    if (grid[i][j] == 1) area[i * n + j] = 1;
                }
            }
        }

        public int find(int x) {
            if (parent[x] == x) return x;
            return find(parent[x]);
        }

        public void union(int x, int y) {
            int root_x = find(x);
            int root_y = find(y);
            if (root_x == root_y) return;
            if (area[root_x] >= area[root_y]) {
                parent[root_y] = root_x;
                area[root_x] += area[root_y];
            } else {
                parent[root_x] = root_y;
                area[root_y] += area[root_x];
            }
        }
    }
}
