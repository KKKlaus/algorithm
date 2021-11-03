import java.util.HashSet;
import java.util.Set;

public class MinCostToConnectAllPoints {

    // Prime Naive 算法：时间复杂度 O(V^2)
    public int minCostConnectPoints(int[][] points) {
        Set<Integer> visited = new HashSet<>();
        int n = points.length;
        int[] distance = new int[n];
        // init distance
        for (int i = 0; i < n; i++) {
            distance[i] = getDist(points, 0, i);
        }
        int res = 0;
        while (visited.size() < n) {
            int next = -1;
            // get the shortest one
            for (int i = 0; i < n; i++) {
                if (visited.contains(i)) continue;
                if (next == -1 || distance[next] > distance[i]) next = i;
            }
            res += distance[next];
            visited.add(next);
            for (int i = 0; i < n; i++) {
                distance[i] = Math.min(distance[i], getDist(points, next, i));
            }
        }
        return res;
    }

    private int getDist(int[][] points, int from, int to) {
        return Math.abs(points[from][0] - points[to][0]) + Math.abs(points[from][1] - points[to][1]);
    }

}
