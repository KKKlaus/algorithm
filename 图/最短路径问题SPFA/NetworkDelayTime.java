import java.util.*;

public class NetworkDelayTime {


    // 注意审题，求所有节点收到信号并不是求最长路径，而是利用最短路径： 用Dijkstra
    // 标准dijkstra写法
    public int networkDelayTime(int[][] times, int n, int k) {
        HashMap<Integer, List<int[]>> map = new HashMap<>();
        for (int[] time : times) {
            int from = time[0], to = time[1], cost = time[2];
            if (!map.containsKey(from)) {
                map.put(from, new ArrayList<>());
            }
            map.get(from).add(new int[]{to, cost});
        }

        int[] distance = new int[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[k] = 0;

        Queue<int[]> queue = new LinkedList<>(); // int[]{to, time}
        queue.offer(new int[]{k, 0});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int node = cur[0], time = cur[1];
            if (!map.containsKey(node)) continue;
            for (int[] next : map.get(node)) {
                int nextNode = next[0], t = next[1];
                if (time + t < distance[nextNode]) {
                    distance[nextNode] = time + t;
                    queue.offer(new int[]{nextNode, distance[nextNode]});
                }
            }
        }

        int res = 0;
        for (int i = 1; i < distance.length; i++) {
            if (distance[i] == Integer.MAX_VALUE) return -1;
            res = Math.max(res, distance[i]);
        }

        return res;
    }


    // 用pq
    public int networkDelayTime_2(int[][] times, int n, int k) {

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
