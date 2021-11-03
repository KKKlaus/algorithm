public class NumberOfProvinces {

    // 方法1： UnionFind. 方法2：DFS
    public int findCircleNum(int[][] isConnected) {
        int m = isConnected.length;
        UnionFind UF = new UnionFind(m);
        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m; j++) {
                if (isConnected[i][j] == 1) {
                    UF.union(i,j);
                }
            }
        }
        return UF.size;
    }

    class UnionFind {

        int[] parent;
        int size;
        public UnionFind(int size) {
            parent = new int[size];
            this.size = size;
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
            this.size--;
        }

    }
}
