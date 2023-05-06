import java.util.*;

public class MinimumCostOfPathWithSpecialRoads {

    public int minimumCost(int[] start, int[] target, int[][] specialRoads) {
        List<int[]> filteredRoads = new ArrayList<>();
        for (int[] road : specialRoads) {
            int a = road[0], b = road[1], c = road[2], d = road[3], cost = road[4];
            if (cost < Math.abs(a - c) + Math.abs(b - d)) {
                filteredRoads.add(new int[]{a, b, c, d, cost});
            }
        }


        // dist: {(x, y), min_cost}
        Map<List<Integer>, Integer> dist = new HashMap<>();
        dist.put(Arrays.asList(start[0], start[1]), 0);

        // pq: [cost, x, y]
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> (a[0] - b[0]));
        pq.offer(new int[]{0, start[0], start[1]});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int x = cur[1], y = cur[2], curDist = cur[0];
            for (int[] road : filteredRoads) {
                int a = road[0], b = road[1], c = road[2], d = road[3], cost = road[4];
                if (dist.getOrDefault(Arrays.asList(c,d), Integer.MAX_VALUE) > curDist + Math.abs(a - x) + Math.abs(b - y) + cost) {
                    // P.S: That's why we define key of dist as List instead of int[], because:
                    // When you add the key-value pair to map using Arrays.asList() as the key, Java internally checks if the List object Arrays.asList() is equal to any existing key, but add new int[]{} will be different
                    dist.put(Arrays.asList(c,d), curDist + Math.abs(a - x) + Math.abs(b - y) + cost);
                    pq.offer(new int[]{dist.get(Arrays.asList(c,d)), c, d});
                }
            }
        }

        int res = Math.abs(target[0] - start[0]) + Math.abs(target[1] - start[1]);
        for (int[] filterRoad : filteredRoads) {
            int a = filterRoad[0], b = filterRoad[1], c = filterRoad[2], d = filterRoad[3], cost = filterRoad[4];
            res = Math.min(res, dist.getOrDefault(Arrays.asList(c, d), Integer.MAX_VALUE) + Math.abs(target[0] - c) + Math.abs(target[1] - d));
        }
        return res;
    }
}
