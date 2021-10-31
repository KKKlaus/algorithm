import java.util.HashMap;

public class unionFind_op {


    class UnionFind_int {
        private int[] father = null;
        private int count;

        public UnionFind_int(int n) {
            father = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                father[i] = i;
            }
        }

        public int find(int x) {
            if (father[x] == x) {
                return father[x];
            }
            father[x] = find(father[x]);
            return father[x];
        }

        public void connect(int a, int b) {
            int root_a = find(a);
            int root_b = find(b);
            if (root_a != root_b) {
                father[root_a] = root_b;
                count--;
            }
        }
    }
}
