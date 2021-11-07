import java.util.Map;
import java.util.TreeMap;

public class Calendar3 {

    class MyCalendarThree {


        private TreeMap<Integer, Integer> map;
        public MyCalendarThree() {
            map = new TreeMap<>();
        }

        public int book(int start, int end) {
            map.put(start, map.getOrDefault(start, 0) + 1);
            map.put(end, map.getOrDefault(end, 0) - 1);
            int count = 0, maxBooking = 0;
            for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
                count += entry.getValue();
                maxBooking = Math.max(count, maxBooking);
            }
            return maxBooking;
        }
    }
}
