import java.util.Map;
import java.util.TreeMap;

public class Calendar2 {

    // 整个对所有区间book: O(n*n),单独book:O(n)
    class MyCalendarTwo {

        private TreeMap<Integer, Integer> map;

        public MyCalendarTwo() {
            map = new TreeMap<>();
        }

        // O(n)
        public boolean book(int start, int end) {
            map.put(start, map.getOrDefault(start, 0) + 1);
            map.put(end, map.getOrDefault(end, 0) - 1);
            int count = 0;
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                count += entry.getValue();
                if (count > 2) {
                    map.put(start, map.get(start) - 1);
                    if (map.get(start) == 0) {
                        map.remove(start);
                    }
                    map.put(end, map.get(end) + 1);
                    if (map.get(end) == 0) {
                        map.remove(end);
                    }
                    return false;
                }
            }
            return true;
        }
    }
}
