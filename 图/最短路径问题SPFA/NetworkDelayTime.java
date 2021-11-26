import java.util.*;

public class NetworkDelayTime {


    // 注意审题，求所有节点收到信号并不是求最长路径，而是利用最短路径： 用Dijkstra
    public int networkDelayTime(int[][] times, int n, int k) {

        // build graph
        HashMap<Integer, List<int[]>> map = new HashMap<>();
        for (int[] time : times) {
            int from = time[0], to = time[1], cost = time[2];
            if (!map.containsKey(from)) {
                map.put(from, new ArrayList<>());
            }
            map.get(from).add(new int[]{to, cost});
        }

        HashSet<Integer> visited = new HashSet<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[1] - b[1]));
        pq.offer(new int[]{k, 0});

        int res = 0;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int node = cur[0], time = cur[1];
            if (visited.contains(node)) continue;
            visited.add(node);
            res = time;
            n--;
            if (n == 0) return res;
            if (map.containsKey(node)) {
                for (int[] next : map.get(node)) {
                    pq.offer(new int[]{next[0], next[1] + time});
                }
            }
        }

        return n == 0 ? res : -1;
    }
}
