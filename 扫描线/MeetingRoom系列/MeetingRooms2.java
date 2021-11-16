import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class MeetingRooms2 {

    // TreeMap
    public int minMeetingRooms(int[][] intervals) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int[] interval : intervals) {
            int start = interval[0], end = interval[1];
            map.put(start, map.getOrDefault(start, 0) + 1);
            map.put(end,  map.getOrDefault(end, 0) - 1);
        }
        int count = 0, minVal = 0;
        for (int key : map.keySet()) {
            count += map.get(key);
            minVal = Math.max(count, minVal);
        }
        return minVal;
    }


    // PQ
    public int minMeetingRooms_2(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[1] - b[1]));
        pq.offer(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            int[] k = pq.poll();

            if (intervals[i][0] >= k[1]) {
                k[1] = intervals[i][1];
            } else {
                pq.offer(intervals[i]);
            }


            pq.offer(k);
        }
        return pq.size();
    }

    // 画一个示意图
    // 原理是当start[i] < end[endIndex] 表示我又有个会议但是小于最近的会议结束时间，所以要额外安排一个会议
    // else表示之前的某个会议结束了，我空出来了一个房间，而当前start[i]这个会议就用这个房间
    public int minMeetingRooms_3(int[][] intervals) {
        int n = intervals.length;

        int[] start = new int[n];
        int[] end = new int[n];
        for (int i = 0; i < n; i++) {
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }
        Arrays.sort(start);
        Arrays.sort(end);

        int rooms = 0, endIndex = 0;
        for (int i = 0; i < n; i++) {
            if(start[i] < end[endIndex]) {
                rooms++;
            } else {
                endIndex++;
            }
        }
        return rooms;
    }
}
