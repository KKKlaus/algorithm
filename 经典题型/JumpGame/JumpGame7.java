import java.util.ArrayDeque;
import java.util.Queue;

public class JumpGame7 {

    // BFS
    // https://www.youtube.com/watch?v=v1HpZUnQ4Yo
    // O(n)
    public boolean canReach(String s, int minJump, int maxJump) {
        Queue<Integer> queue = new ArrayDeque<>();
        int farthest = 0, n = s.length();
        queue.offer(0);
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            int start = Math.max(farthest + 1, cur+ minJump);
            int end = Math.min(n - 1, cur + maxJump);
            for (int i = start; i <= end; i++) {
                char c = s.charAt(i);
                if (c == '0') {
                    if (i == n - 1) return true;
                    queue.offer(i);
                }
            }
            farthest = cur + maxJump;
        }
        return false;
    }
}
