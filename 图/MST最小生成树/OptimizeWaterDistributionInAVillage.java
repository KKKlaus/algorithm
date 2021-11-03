import java.util.*;

public class OptimizeWaterDistributionInAVillage {

    // 非常tricky的方法：本身的打水井的花费就相当于有个虚拟水井0往这些节点的边的权值


    // 1. Prim
    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        HashMap<Integer, List<int[]>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int from = 0, to = i + 1, cost = wells[i];
            if (!graph.containsKey(from)) {
                graph.put(from, new ArrayList<>());
            }
            graph.get(from).add(new int[]{to, cost});
        }
        for (int[] pipe : pipes) {
            int from = pipe[0], to = pipe[1], cost = pipe[2];
            if (!graph.containsKey(from)) graph.put(from, new ArrayList<>());
            if (!graph.containsKey(to)) graph.put(to, new ArrayList<>());
            graph.get(from).add(new int[]{to, cost});
            graph.get(to).add(new int[]{from, cost});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[1] - b[1]));
        Set<Integer> visited = new HashSet<>();
        pq.offer(new int[]{0,0}); // curNode, cost
        int res = 0;
        while (!pq.isEmpty()) {
            int[] edge = pq.poll();
            int cur = edge[0], cost = edge[1];
            if (visited.contains(cur)) continue;
            res += cost;
            visited.add(cur);
            if (!graph.containsKey(cur)) continue;
            for (int[] nei : graph.get(cur)) {
                pq.offer(new int[]{nei[0], nei[1]});
            }
        }
        return res;
    }

    // Kruskal
    public int minCostToSupplyWater_2(int n, int[] wells, int[][] pipes) {
        int m = wells.length, t = pipes.length;
        int[][] edges = new int[t + m][3];
        for (int i = 0; i < m; i++) {
            int cost = wells[i];
            edges[i] = new int[]{0, i + 1, cost};
        }
        for (int i = m; i < m + t; i++) {
            edges[i] = pipes[i - m];
        }
        UnionFind UF = new UnionFind(n + 1);
        int res = 0;
        Arrays.sort(edges, (a,b) -> (a[2] - b[2]));
        for (int[] edge : edges) {
            int from = edge[0], to = edge[1], cost = edge[2];
            int parent_from = UF.find(from), parent_to = UF.find(to);
            if (parent_from != parent_to) {
                res += cost;
                UF.union(from, to);
            }
        }
        return res;
    }

    class UnionFind {

        public int[] parent;

        public UnionFind(int size) {
            parent = new int[size];
            for (int i = 0; i< size; i++) {
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
