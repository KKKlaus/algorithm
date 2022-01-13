import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class 汇率转换2 {

    public static void main(String[] args) {
        汇率转换2 s = new 汇率转换2();
        s.test();
    }

    public void test() {
        List<Node> input = Arrays.asList(new Node("GCP", "EUR", 10), new Node("EUR", "USD", 1.1));
        double ans = exchange(input, 10, "GCP", "USD");
        System.out.println("ans = " + ans);
    }

    // {"GCP", "EUR", 10}
    // {"EUR", "USD", 1.1}
    // {10, "GCP", "USD"}

    public double exchange(List<Node> input, double startMoney, String start, String end) {
        // 1. build graph
        HashMap<String, HashMap<String, Double>> map = new HashMap<>();
        for (Node node : input) {
            String x = node.x, y = node.y;
            double rate = node.rate;
            if (!map.containsKey(x)) {
                map.put(x, new HashMap<>());
            }
            map.get(x).put(y, rate);
            if (!map.containsKey(y)) {
                map.put(y, new HashMap<>());
            }
            map.get(y).put(x, 1 / rate);
        }

        // 2. DFS
        return startMoney * dfs(map, start, end, new HashSet<>());
    }

    private double dfs(HashMap<String, HashMap<String, Double>> map, String cur, String end, HashSet<String> visited) {
        if (!map.containsKey(cur)) return -1;

        if (map.get(cur).containsKey(end)) {
            return map.get(cur).get(end);
        }

        visited.add(cur);
        for (String next : map.get(cur).keySet()) {
            if (visited.contains(next)) continue;
            double res = dfs(map, next, end, visited);
            if (res != -1) {
                double ans = map.get(cur).get(next);
                map.get(cur).put(next, ans * res);
                return ans * res;
            }
        }
        return -1;
    }

    class Node {
        String x, y;
        double rate;
        public Node(String x, String y, double rate) {
            this.x = x;
            this.y = y;
            this.rate = rate;
        }
    }
}
