import java.util.ArrayList;
import java.util.List;

public class NumberOfIslands2 {


    //  uf.count的使用
    //  定义一个val数组相当于visited[][]
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> res = new ArrayList<>();
        UnionFind uf = new UnionFind(m * n);
        int[][] dirs = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
        int[] val = new int[m*n];      // 定义一个数组相当于visited[][]
        for (int[] pos : positions) {
            int x = pos[0], y = pos[1];
            if (val[x * n + y] != 1) {
                uf.count++;
                val[x * n + y] = 1;
            }
            for (int[] dir : dirs) {
                int nx = x + dir[0], ny = y + dir[1];
                if (nx < 0 || nx >= m || ny < 0 || ny >= n || val[nx * n + ny] == 0) continue;
                uf.union(x * n + y, nx * n + ny);
            }
            res.add(uf.count);
        }
        return res;
    }


    class UnionFind {

        int[] parent;
        int count;
        UnionFind(int size) {
            parent = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
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
