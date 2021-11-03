import java.util.*;

public class ConnectingCitiesWithMinimumCost {

    public static void main(String[] args) {

        System.out.println(25 >>> 3);
//        ConnectingCitiesWithMinimumCost t = new ConnectingCitiesWithMinimumCost();
//        t.test();
    }

    private void test() {
        minimumCost(3, new int[][]{{1,2,5}, {1,3,6}, {2,3,1}});
    }

    // 方法1：Prim算法 最小生成树 ： PQ记录邻居
    // Time complexity： O(ElogV)
    // Space   ....   : O(V + E)
    public int minimumCost(int n, int[][] connections) {
        HashMap<Integer, List<int[]>> map = new HashMap<>(); // key: from. value: [to, cost];
        for (int[] item : connections) {
            int from = item[0];
            int to = item[1];
            int cost = item[2];
            if (!map.containsKey(from)) {
                map.put(from, new ArrayList<>());
            }
            if (!map.containsKey(to)) {
                map.put(to, new ArrayList<>());
            }
            map.get(from).add(new int[]{to, cost});
            map.get(to).add(new int[]{from, cost});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[2] - b[2]));
        pq.offer(new int[]{1,1,0});
        Set<Integer> visited = new HashSet<>();
        int res = 0;
        while(!pq.isEmpty()) {
            int[] node = pq.poll();
            int from = node[0], to = node[1], cost = node[2];
            if (!visited.contains(to)) {
                visited.add(to);
                res += cost;
                for (int[] nei : map.get(to)) {
                    pq.offer(new int[]{to, nei[0], nei[1]});
                }
            }
        }
        return visited.size() == n ? res : -1;
    }

    // 方法2： Kruskal算法，用unionfind
    // 时间复杂度： O(ElogE): https://leetcode.com/explore/learn/card/graph/621/algorithms-to-construct-minimum-spanning-tree/3856/
    public int minimumCost_v2(int n, int[][] connections) {
        Arrays.sort(connections, (a,b) -> (a[2] - b[2]));
        int count = 1, res = 0;
        UnionFind UF = new UnionFind(n + 1);
        for (int[] connection : connections) {
            int from = connection[0], to = connection[1], cost = connection[2];
            int parent_from = UF.find(from), parent_to = UF.find(to);
            if (parent_from != parent_to) {
                UF.union(from, to);
                count++;
                res += cost;
            }
        }
        return count == n ? res : -1;
    }

    class UnionFind {
        int[] parent;
        public UnionFind(int size) {
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
            parent[parent_x] = parent_y;
        }
    }
}
