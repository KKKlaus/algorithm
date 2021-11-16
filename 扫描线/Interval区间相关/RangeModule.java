import java.util.TreeMap;

public class RangeModule {

    public static void main(String[] args) {
        RangeModule test = new RangeModule();
        test.addRange(10, 20);
        test.removeRange(14, 16);
    }

    TreeMap<Integer, Integer> intervals;
    public RangeModule() {
        intervals = new TreeMap<>();
    }

    public void addRange(int left, int right) {
        Integer start = intervals.floorKey(left);
        Integer end = intervals.floorKey(right);
        if (start != null && intervals.get(start) >= left) {
            left = start;
        }
        if (end != null && intervals.get(end) > right) {
            right = intervals.get(end);
        }
        intervals.put(left, right);
        intervals.subMap(left, false, right, true).clear();   // 这一行注意下default是subMap(start, true, end, false)
    }

    public boolean queryRange(int left, int right) {
        Integer start = intervals.floorKey(left);
        if (start != null && intervals.get(start) >= right) return true;
        return false;
    }

    public void removeRange(int left, int right) {
        Integer start = intervals.floorKey(left);
        Integer end = intervals.floorKey(right);

        if (end != null && intervals.get(end) > right) {
            intervals.put(right, intervals.get(end));
        }
        if (start != null && intervals.get(start) > left) {
            intervals.put(start, left);
        }
        intervals.subMap(left, true, right, false).clear();
    }
}
