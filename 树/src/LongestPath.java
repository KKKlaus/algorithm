import java.util.*;

public class LongestPath {

    // LintCode 1469 树带权值

    // 第一次BFS => 找到所求最长路径的起始点

    int maxDistance = 0;
    public int longestPath(int n, int[] starts, int[] ends, int[] lens) {
        // Write your code here
        HashMap<Integer, List<int[]>> tree = buildGraph(starts, ends, lens);
        int start = bfs(tree, 0);
        bfs(tree, start);
        return maxDistance;
    }

    private int bfs(HashMap<Integer, List<int[]>> tree, int root) {
        HashMap<Integer, Integer> distance = new HashMap<>();
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(root);
        distance.put(root, 0);

        maxDistance = 0;
        int end = root;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            int cur_dist = distance.get(cur);
            for (int[] next : tree.get(cur)) {
                int node = next[0], weight = next[1];
                if (distance.containsKey(node)) continue;
                queue.offer(node);
                distance.put(node, cur_dist + weight);
                if (distance.get(node) > maxDistance) {
                    maxDistance = distance.get(node);
                    end = node;
                }
            }
        }

        return end;
    }

    private HashMap<Integer, List<int[]>> buildGraph(int[] starts, int[] ends, int[] lens) {
        HashMap<Integer, List<int[]>> map = new HashMap<>(); // node1 - {node2, lens}
        for (int i = 0; i < starts.length; i++) {
            int node1 = starts[i], node2 = ends[i], len = lens[i];
            if (!map.containsKey(node1)) {map.put(node1, new ArrayList<>());}
            map.get(node1).add(new int[]{node2, len});
            if (!map.containsKey(node2)) {map.put(node2, new ArrayList<>());}
            map.get(node2).add(new int[]{node1, len});
        }
        return map;
    }
}
