import java.util.*;

public class CheapestFlightsWithinKStops {

    // 方法1： BFS 时间复杂度最坏为O(N^(k+1))   k = 0 无stop表示要走一层
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        HashMap<Integer, List<int[]>> map = new HashMap<>();
        for (int[] flight : flights) {
            int from = flight[0], to = flight[1], cost = flight[2];
            if (!map.containsKey(from)) {
                map.put(from, new ArrayList<>());
            }
            map.get(from).add(new int[]{to, cost});
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{src, 0}); // from , curCost
        int ans = Integer.MAX_VALUE;

        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                int from = cur[0], curCost = cur[1];
                if (from == dst) ans = Math.min(ans, curCost);
                if (!map.containsKey(from)) continue;
                for (int[] nei : map.get(from)) {
                    int to = nei[0], cost = curCost + nei[1];
                    if (cost > ans) continue;
                    queue.offer(new int[]{to, cost});
                }
            }
            if (step++ > k) break;
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }


    // 方法2： dijkstra算法  O()
    public int findCheapestPrice_2(int n, int[][] flights, int src, int dst, int k) {
        HashMap<Integer, List<int[]>> map = new HashMap<>();
        for (int[] flight : flights) {
            int from = flight[0], to = flight[1], cost = flight[2];
            if (!map.containsKey(from)) {
                map.put(from, new ArrayList<>());
            }
            map.get(from).add(new int[]{to, cost});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[1] - b[1]));
        pq.offer(new int[]{src, 0, k + 1}); // from - total price - stop

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int city = cur[0], curPrice = cur[1], stop = cur[2];
            if (city == dst) return curPrice;
            if (stop > 0) {
                if (!map.containsKey(city)) continue;
                for (int[] nei : map.get(city)) {
                    pq.offer(new int[]{nei[0], nei[1] + curPrice, stop - 1});
                }
            }
        }

        return -1;
    }


    // 方法3: 基于dijkstra 不超时： 把pq改成queue，然后用个minPrice数组
    // O(N*N)：N为城市数量: 所有节点最多进出队一次V,每次会遍历邻居(最坏为V)
    public int findCheapestPrice_3(int n, int[][] flights, int src, int dst, int k) {
        HashMap<Integer, List<int[]>> map = new HashMap<>();
        for (int[] flight : flights) {
            int from = flight[0], to = flight[1], cost = flight[2];
            if (!map.containsKey(from)) {
                map.put(from, new ArrayList<>());
            }
            map.get(from).add(new int[]{to, cost});
        }

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{src, 0, k + 1}); // city - total price - left stop
        int[] minPrice = new int[n];
        Arrays.fill(minPrice, Integer.MAX_VALUE);

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int city = cur[0], curPrice = cur[1], leftStep = cur[2];
            if (!map.containsKey(city) || leftStep <= 0) continue;
            for (int[] nei : map.get(city)) {
                if (nei[1] + curPrice >= minPrice[nei[0]]) continue;
                q.offer(new int[]{nei[0], nei[1] + curPrice, leftStep - 1});
                minPrice[nei[0]] = nei[1] + curPrice;
            }
        }

        return minPrice[dst] == Integer.MAX_VALUE ? -1 : minPrice[dst];
    }


    // 方法4：Bellman Ford : DP  O(E * K)
    public int findCheapestPrice_4(int n, int[][] flights, int src, int dst, int K) {
        int[][] dp = new int[K + 1][n]; // min cost from src to dst with in k stops
        // dp[k][i] = min(dp[k - 1][i], dp[k - 1][j] + cost[j][i])
        for (int i = 0; i < K + 1; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[0][src] = 0;
        for (int[] f : flights) {
            if (f[0] == src) {
                dp[0][f[1]] = f[2];
            }
        }

        for (int k = 1; k < K + 1; k++) {
            dp[k][src] = 0;
            for (int[] f : flights) {
                int from = f[0], to = f[1], price = f[2];
                if (dp[k - 1][from] == Integer.MAX_VALUE) continue;
                dp[k][to] = Math.min(dp[k][to], dp[k - 1][from] + price);
            }
        }

        return dp[K][dst] == Integer.MAX_VALUE ? -1 : dp[K][dst];
    }
}
