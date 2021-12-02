import java.util.TreeMap;

public class DataStreamAsDisjointIntervals {

    TreeMap<Integer, Integer> map;
    public void SummaryRanges() {
        map = new TreeMap<>();
    }
    // 13 and 18行的if逻辑需要思考一下
    public void addNum(int val) {
        int start = val, end = val;
        Integer floorKey = map.floorKey(val);
        if (floorKey != null && map.get(floorKey) + 1 >= val) {
            start = floorKey;
            end = Math.max(end, map.get(floorKey));
        }
        Integer ceilingKey = map.ceilingKey(val);
        if (ceilingKey != null && ceilingKey <= val + 1) {
            end = map.get(ceilingKey);
        }
        map.put(start, end);
        map.subMap(start, false, end, true).clear();
    }

    public int[][] getIntervals() {
        int[][] res = new int[map.size()][2];
        int t = 0;
        for (int key : map.keySet()) {
            res[t][0] = key;
            res[t][1] = map.get(key);
            t++;
        }
        return res;
    }
}
