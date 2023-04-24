import java.util.*;

public class CriticalConnectionsinaNetwork {

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        // build graph
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (List<Integer> connect : connections) {
            graph.get(connect.get(0)).add(connect.get(1));
            graph.get(connect.get(1)).add(connect.get(0));
        }
        HashSet<List<Integer>> criticalSet = new HashSet<>(connections);
        int[] rank = new int[n];
        Arrays.fill(rank, -2);
        dfs(graph, 0, 0, rank, criticalSet);
        return new ArrayList<>(criticalSet);
    }

    // return the minDepth
    private int dfs(HashMap<Integer, List<Integer>> graph, int curNode, int depth,int[] rank, HashSet<List<Integer>> criticalSet) {
        if (rank[curNode] != -2) return rank[curNode];

        rank[curNode] = depth;
        int minDepth = Integer.MAX_VALUE;
        for (int child : graph.get(curNode)) {
            if (rank[child] == depth - 1) continue;
            int minDepthForTwoNodes = dfs(graph, child, depth + 1, rank, criticalSet);
            if (minDepthForTwoNodes <= depth) {
                criticalSet.remove(Arrays.asList(curNode, child));
                criticalSet.remove(Arrays.asList(child, curNode));
            }
            minDepth = Math.min(minDepth, minDepthForTwoNodes);
        }
        return minDepth;
    }

    public static void main(String[] args) {
        List<List<Integer>> connections = new ArrayList<>();
        connections.add(Arrays.asList(0,1));
        connections.add(Arrays.asList(1,2));
        connections.add(Arrays.asList(2,0));
        connections.add(Arrays.asList(1,3));
        CriticalConnectionsinaNetwork test = new CriticalConnectionsinaNetwork();
        test.criticalConnections(4, connections);
    }
}
