import java.util.*;

public class BusRoutes {

    // 两个关键点
    // 1. map key存stop， value存对应的bus号，然后通过原本的routes数组和bus号来获取邻居stops
    // 2. visited查bus是否重复，而不是stop，后者会超时，前者是更早更优化的剪枝


    // 时间复杂度: O(routes大小 + O(N*N)) N = bus number -> Our BFS search is on N nodes, and each node could have N edges,
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (target == source) return 0;
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < routes.length; i++) {
            for (int j = 0; j < routes[i].length; j++) {
                int stop = routes[i][j];
                if (!map.containsKey(stop)) map.put(stop, new ArrayList<>());
                map.get(stop).add(i);
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(source);
        HashSet<Integer> visited = new HashSet<>();

        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            level++;
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                for (int bus : map.get(cur)) {
                    if (visited.contains(bus)) continue;
                    visited.add(bus);
                    for (int stop : routes[bus]) {
                        if (stop == target) return level;
                        queue.offer(stop);

                    }
                }
            }
        }

        return -1;
    }
}
