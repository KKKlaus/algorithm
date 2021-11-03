import java.util.HashMap;

public class unionFind_op {


    // 一般时间复杂度为O(logN),以最小生成树(MST)Kruskal算法为例，每次unionFind之后相当于把两个节点处理了
    class UnionFind_int {
        private int[] parent = null;
        private int count;

        public UnionFind_int(int n) {
            parent = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (parent[x] == x) {
                return parent[x];
            }
            parent[x] = find(parent[x]);
            return parent[x];
        }

        public void connect(int a, int b) {
            int root_a = find(a);
            int root_b = find(b);
            if (root_a != root_b) {
                parent[root_a] = root_b;
                count--;
            }
        }
    }
}
