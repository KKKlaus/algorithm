public class GraphValidTree {

    //Recall that a graph, G, is a tree iff the following two conditions are met:
    //
    //G is fully connected. In other words, for every pair of nodes in G, there is a path between them. ->全联通
    //G contains no cycles. In other words, there is exactly one path between each pair of nodes in G.  -> 无环

    public boolean validTree(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);
        for (int[] edge : edges) {
            if (!uf.union(edge[0], edge[1])) return false;
        }
        return uf.count == 1;
    }

    class UnionFind {
        int[] parent;
        int count;

        public UnionFind(int size) {
            parent = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
            }
            count = size;
        }

        public int find(int x) {
            if (parent[x] == x) return x;
            parent[x] = find(parent[x]);
            return parent[x];
        }

        public boolean union(int x, int y) {
            int root_x = find(x), root_y = find(y);
            if (root_x == root_y) return false;
            parent[root_x] = root_y;
            count--;
            return true;
        }
    }
}
