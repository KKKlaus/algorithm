import java.util.*;

public class CourseSchedule {

    // 在图论中，拓扑排序（Topological Sorting）是一个有向无环图（DAG, Directed Acyclic Graph）的所有顶点的线性序列。且该序列必须满足下面两个条件：
    //
    //每个顶点出现且只出现一次。
    //若存在一条从顶点 A 到顶点 B 的路径，那么在序列中顶点 A 出现在顶点 B 的前面。
    //有向无环图（DAG）才有拓扑排序，非DAG图没有拓扑排序一说。
    public boolean canFinish(int numCourses, int[][] prerequisites) {
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
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                count++;
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int pre = queue.poll();
            if (!map.containsKey(pre)) continue;
            for (int after : map.get(pre)) {
                if (--indegree[after] == 0) {
                    queue.offer(after);
                    count++;
                }
            }
        }
        return count == numCourses;
    }
}
