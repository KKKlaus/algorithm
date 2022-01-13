package Lyft;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MeetingRoom3 {

    public static void main(String[] args) {
        MeetingRoom3 test = new MeetingRoom3();
        test.testMeetings();
    }

    public void testMeetings() {
        getMinimumMeetings(new int[][]{{4, 8, 0}, {5, 10, 1}, {6, 8, 2}, {9, 16, 3}, {15, 20, 4}});
    }

    public void getMinimumMeetings(int[][] meetings) {
        var meetingsByRoom = minMeetings(meetings);
        System.out.println(meetingsByRoom);
        System.out.println(Collections.max(meetingsByRoom.values()) + 1); // max rooms required
    }

    private Map<Integer, Integer> minMeetings(int[][] meetings) {
        Arrays.sort(meetings, Comparator.comparingInt(a -> a[0]));


        PriorityQueue<Integer> freeRoom = new PriorityQueue<>();
        for (int i = 0; i < meetings.length; i++) {
            freeRoom.offer(i);
        }

        PriorityQueue<int[]> assignedRooms = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        Map<Integer, Integer> res = new HashMap<>();

        for (int[] meeting : meetings) {
            int start = meeting[0], end = meeting[1], meetingId = meeting[2];

            while (!assignedRooms.isEmpty() && start >= assignedRooms.peek()[0]) {
                freeRoom.offer(assignedRooms.poll()[1]);
            }

            if (!freeRoom.isEmpty()) { // 这里因为freeRoom数量 = meetings.length，所以必是足够的
                int roomId = freeRoom.poll();
                assignedRooms.offer(new int[]{end, roomId});
                res.put(meetingId, roomId);
            }
        }
        return res;
    }

}
