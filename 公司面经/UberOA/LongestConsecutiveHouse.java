import java.util.HashMap;

public class LongestConsecutiveHouse {

    // LC 128变形
    // 题目：给一个array，[2, 4, 6, 3, 1]，代表依次在num的编号位置建房子，return一个同样长度的array，每个元素代表建造到当前房子的时候，连续邻居房屋的最长个‍‌‌‌‌‌‌‍‌‌‌‍‍‌‍‌‍‌‌‌数。这里应该赶回[1, 1, 1, 3, 4].
    //解释：建造完2号房子，只有1个连续，建造完4号房子，还是最长1个连续，建造完6号房子，还是最长1个连续，建造完3号房子，现在最长为[1,2,3]三家连续。
    //## 思路：判断连续的时候，如果用排序NlogN就会超时。可以用hashset的方式O(N)时间来判断连续的个数

    public static void main(String[] args) {
        LongestConsecutiveHouse t = new LongestConsecutiveHouse();
        int[] res = t.getHouse(new int[]{2, 4, 6, 3, 1});
        for (int x : res) System.out.print(x + " ");
    }

    public int[] getHouse(int[] arr) {
        int n = arr.length;
        int[] res = new int[n];
        UnionFind uf = new UnionFind(n);
        HashMap<Integer, Integer> map = new HashMap<>(); // value-index
        for (int i = 0; i < n; i++) {
            int val = arr[i];
            if (map.containsKey(val)) continue;
            map.put(val, i);
            if (map.containsKey(val + 1)) {
                uf.union(i, map.get(val + 1));
            }
            if (map.containsKey(val - 1)) {
                uf.union(i, map.get(val - 1));
            }
            res[i] = uf.count[uf.find(i)];
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
