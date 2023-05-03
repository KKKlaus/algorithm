import java.lang.reflect.Array;
import java.util.*;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {
//        HashMap<int[], Integer> map1 = new HashMap<>();
//        HashMap<List<Integer>, Integer> map2 = new HashMap<>();
//        map1.put(new int[]{1,2}, 1);
//        map2.put(Arrays.asList(1,2), 2);
//        System.out.println(map1.get(new int[]{1,2}));
//        System.out.println(map2.get(Arrays.asList(1,2)));

        maxProbability(3, new int[][]{{0,1},{1,2},{0,2}}, new double[]{0.5,0.5,0.2}, 0, 2);
    }

    public static double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        Map<Integer, List<double[]>> graph = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            int from = edges[i][0], to = edges[i][1];
            double prob = succProb[i];
            if (!graph.containsKey(from)) {
                graph.put(from, new ArrayList<>());
            }
            if (!graph.containsKey(to)) {
                graph.put(to, new ArrayList<>());
            }
            graph.get(from).add(new double[]{(double)to, prob});
            graph.get(to).add(new double[]{(double)from, prob});
        }

        Map<Integer, Double> maxProbMap = new HashMap<>();


        PriorityQueue<double[]> pq = new PriorityQueue<>((a,b) -> (a[1] - b[1] > 0 ? -1 : b[1] == a[1] ? 0 : 1));
        pq.offer(new double[]{start, 1});
        while(!pq.isEmpty()) {
            double[] curr = pq.poll();
            double currNode = curr[0], currProb = curr[1];
            if ((int)currNode == end) return currProb;
            if (!graph.containsKey((int)currNode)) continue;
            for (double[] neighbor : graph.get((int)currNode)) {
                double next = neighbor[0], prob = neighbor[1];
                if (maxProbMap.getOrDefault((int)next, 0.0) < currProb * prob) {
                    maxProbMap.put((int)next, currProb * prob);
                    pq.offer(new double[]{next, currProb * prob});
                }
            }
        }
        return 0d;
    }
}