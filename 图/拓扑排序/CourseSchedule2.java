import java.util.*;

public class CourseSchedule2 {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        int[] indegree = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            int pre = prerequisite[1], after = prerequisite[0];
            indegree[after]++;
            if (!map.containsKey(pre)) {
                map.put(pre, new ArrayList<>());
            }
            map.get(pre).add(after);
        }

        int count = 0;
        int[] res = new int[numCourses];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                res[count++] = i;
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int pre = queue.poll();
            if (!map.containsKey(pre)) continue;
            for (int after : map.get(pre)) {
                if (--indegree[after] == 0) {
                    queue.offer(after);
                    res[count++] = after;
                }
            }
        }
        return count == numCourses ? res : new int[]{};
    }
}
