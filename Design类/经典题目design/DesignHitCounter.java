import java.util.TreeMap;

public class DesignHitCounter {

    class HitCounter {

        int count;
        TreeMap<Integer, Integer> map; // time - count
        public HitCounter() {
            map = new TreeMap<>();
            count = 0;
        }

        public void hit(int timestamp) {
            map.put(timestamp, ++count);
        }

        public int getHits(int timestamp) {
            Integer right = map.floorKey(timestamp);
            if (right == null || right < timestamp - 300) return 0;
            Integer left = map.floorKey(timestamp - 300);
            if (left == null) {
                return map.get(right);
            } else {
                return map.get(right) - map.get(left);
            }
        }
    }
}
