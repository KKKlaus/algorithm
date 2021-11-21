import java.util.HashMap;

public class MaxPointsOnALine {

    public int maxPoints(int[][] points) {
        HashMap<String, Integer> map = new HashMap<>();
        int n = points.length, res = 0;
        for (int i = 0; i < n; i++) {
            map.clear();
            int max = 0, same = 0;
            for (int j = i + 1; j < n; j++) {
                int[] p1 = points[i], p2 = points[j];
                if (p1[0] == p2[0] && p1[1] == p2[1]) same++;
                int x_gap = p1[0] - p2[0];
                int y_gap = p1[1] - p2[1];
                int gcd = gcd(x_gap, y_gap);
                x_gap /= gcd;
                y_gap /= gcd;
                String key = x_gap + "," + y_gap;
                map.put(key, map.getOrDefault(key, 0) + 1);
                max = Math.max(max, map.get(key));
            }
            res = Math.max(res, max + same + 1);
        }
        return res;
    }

    public int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}
