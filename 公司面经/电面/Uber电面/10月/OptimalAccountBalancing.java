import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OptimalAccountBalancing {

    public int minTransfers(int[][] transactions) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int[] transaction : transactions) {
            int from = transaction[0], to = transaction[1], amount = transaction[2];
            map.put(from, map.getOrDefault(from, 0) - amount);
            map.put(to, map.getOrDefault(to, 0) + amount);
        }
        return dfs(0, new ArrayList<>(map.values()));
    }

    // dfs 去遍历所有可以把债务清零的可能
    private int dfs(int start, List<Integer> debt) {
        while (start < debt.size() && debt.get(start) == 0) {
            start++;
        }
        if (start == debt.size()) return 0;
        int r = Integer.MAX_VALUE;
        for (int i = start + 1; i < debt.size(); i++) {
            if (debt.get(i) * debt.get(start) < 0) {
                debt.set(i, debt.get(i) + debt.get(start));
                r = Math.min(r, 1 + dfs(start + 1, debt));
                debt.set(i, debt.get(i) - debt.get(start));
            }
        }
        return r;
    }
}
