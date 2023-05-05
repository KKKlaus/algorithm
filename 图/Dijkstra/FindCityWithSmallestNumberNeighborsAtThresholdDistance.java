import java.util.*;

public class FindCityWithSmallestNumberNeighborsAtThresholdDistance {

    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        // build graph
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] edge : edges) {
            int city1 = edge[0], city2 = edge[1], weight = edge[2];
            if (!graph.containsKey(city1)) {
                graph.put(city1, new ArrayList<>());
            }
            if (!graph.containsKey(city2)) {
                graph.put(city2, new ArrayList<>());
            }
            graph.get(city1).add(new int[]{city2, weight});
            graph.get(city2).add(new int[]{city1, weight});
        }

        int minCityNums = Integer.MAX_VALUE;
        int res = 0;
        for (int i = n - 1; i >= 0; i--) {
            int reachableNums = dijkstra(i, n, graph, distanceThreshold);
            if (reachableNums < minCityNums) {
                res = i;
                minCityNums = reachableNums;
            }
        }
        return res;
    }

    private int dijkstra(int city, int n, Map<Integer, List<int[]>> graph, int distanceThreshold) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        System.out.println("\ncity: " + city);


        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> (a[1] - b[1]));
        pq.offer(new int[]{city, 0});
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curCity = cur[0], curWeight = cur[1];
            if (!graph.containsKey(curCity)) continue;
            for (int[] neighbor : graph.get(curCity)) {
                int nextCity = neighbor[0], cost = neighbor[1];
                if (curWeight + cost > distanceThreshold) continue;
                if (dist[nextCity] > curWeight + cost) {
                    dist[nextCity] = curWeight + cost;
                    pq.offer(new int[]{nextCity, curWeight + cost});
                }
            }
        }
        int num = 0;
        for (int i = 0; i < n; i++) {
            // not include its own
            if (i == city) continue;
            System.out.println("node: " + i + " dist: " + dist[i]);
            if (dist[i] <= distanceThreshold) num++;
        }
        return num;
    }

}
