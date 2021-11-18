import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LongestPath {

    // LintCode 1469 无向图带权值

//    int max = 0;
//    public int longestPath(int n, int[] starts, int[] ends, int[] lens) {
//        // Write your code here
//        HashMap<Integer, List<int[]>> graph = buildGraph(starts, ends, lens);
//    }
//
//    private int dfs(int root, HashMap<Integer, List<int[]>> graph) {
//
//    }

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
