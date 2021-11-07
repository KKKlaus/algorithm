import java.util.LinkedList;
import java.util.Queue;

public class JumpGame3 {

    // BFS
    // O(n)
    public boolean canReach(int[] arr, int start) {
        if (arr[start] == 0) return true;
        boolean[] visited = new boolean[arr.length];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = true;
        while (!queue.isEmpty()) {
            int midIndex = queue.poll();
            for (int index : getNextIndexes(arr, midIndex)) {
                if (index < 0 || index >= arr.length || visited[index]) continue;
                if (arr[index] == 0) return true;
                queue.offer(index);
                visited[index] = true;
            }
        }
        return false;
    }

    private int[] getNextIndexes(int[] arr, int midIndex) {
        int[] res = new int[2];
        int mid = arr[midIndex];
        res[0] = midIndex + mid;
        res[1] = midIndex - mid;
        return res;
    }
}
