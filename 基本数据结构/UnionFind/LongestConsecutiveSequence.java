import java.util.HashMap;

public class LongestConsecutiveSequence {

    public int longestConsecutive(int[] nums) {
        int n = nums.length;
        UnionFind uf = new UnionFind(n);
        HashMap<Integer, Integer> map = new HashMap<>();  // value-index
        int res = 0;
        for (int i = 0; i < n; i++) {
            int val = nums[i];
            if (map.containsKey(val)) continue;
            map.put(val, i);
            if (map.containsKey(val + 1)) {
                uf.union(i, map.get(val + 1));
            }
            if (map.containsKey(val - 1)) {
                uf.union(i, map.get(val - 1));
            }
            res = Math.max(res, uf.count[uf.find(i)]);
        }
        return res;
    }

    class UnionFind {

        int[] parent;
        int[] count;

        public UnionFind(int size) {
            parent = new int[size];
            count = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
                count[i] = 1;
            }
        }

        public int find(int x) {
            if (parent[x] == x) return x;
            return find(parent[x]);
        }

        public void union(int x, int y) {
            int root_x = find(x), root_y = find(y);
            if (root_x == root_y) return;
            if (count[root_x] >= count[root_y]) {
                parent[root_y] = root_x;
                count[root_x] += count[root_y];
            } else {
                parent[root_x] = root_y;
                count[root_y] += count[root_x];
            }
        }
    }
}
