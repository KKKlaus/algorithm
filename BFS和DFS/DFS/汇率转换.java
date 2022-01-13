import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class 汇率转换 {

    // 方法1： 直接dfs，不cache
    public static void main(String[] args) {
        汇率转换 s = new 汇率转换();
        s.test();
    }

    public void test() {
        List<Node> input = Arrays.asList(new Node("GCP", "EUR", 10), new Node("EUR", "USD", 1.1));
        double ans = exchange(input, 10, "USD", "GCP");
        System.out.println("ans = " + ans);
    }

    // {"GCP", "EUR", 10}
    // {"EUR", "USD", 1.1}
    // {10, "GCP", "USD"}
    public double res = 0;

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
        dfs(map, start, end, startMoney, new HashSet<>());
        return res;
    }

    private void dfs(HashMap<String, HashMap<String, Double>> map, String cur, String end, double temp, HashSet<String> visited) {
        if (cur.equals(end)) {
            res = temp;
            return;
        }

        if (!map.containsKey(cur)) return;
        HashMap<String, Double> neighbors = map.get(cur);
        for (String next : neighbors.keySet()) {
            if (visited.contains(next)) continue;
            double rate = neighbors.get(next);
            visited.add(cur);
            dfs(map, next, end, temp * rate, visited);
            visited.remove(cur);
        }

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
