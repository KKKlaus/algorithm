import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals {

    // 注意： cur[1] = Math.max(cur[1], intervals[i][1]); 取更大的那个而不是后面那个比如[[1,4],[2,3]]
    // 最后list转array ： res.toArray(new int[res.size()][])
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        List<int[]> res = new ArrayList<>();
        int[] cur = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= cur[1]) {
                cur[1] = Math.max(cur[1], intervals[i][1]);
            } else {
                res.add(cur);
                cur = intervals[i];
            }
        }
        res.add(cur);
        return res.toArray(new int[res.size()][]);

    }
}
