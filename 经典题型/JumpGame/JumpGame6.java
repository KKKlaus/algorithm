import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

// https://www.youtube.com/watch?v=M_PzYd59_kk
public class JumpGame6 {

    // DP + maxslidingWindow  妥妥的hard题

    // 时间复杂度O(n), 空间复杂度O(n + k)
    // 我写的：很蠢，每次用deque算i-1前面的slidingWindow，再更新dp[i]
    public int maxResult(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 1; i < n; i++) {
            while (!deque.isEmpty() && i - 1 - deque.peek() >= k) {
                deque.poll();
            }
            while (!deque.isEmpty() && dp[i - 1] > dp[deque.peekLast()]) {
                deque.pollLast();
            }

            deque.offer(i - 1);
            dp[i] = dp[deque.peek()] + nums[i];
        }

        return dp[n - 1];
    }

    // 时间复杂度O(n), 空间复杂度O(n + k)
    // 别人写的就直接可以用maxSlidingWindow的代码了：先更新dp[i]，再用deque算每次i前面的maxSlidingWindow
    public int maxResult_optimize(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        Deque<Integer> deque = new ArrayDeque<>();
        deque.offer(0);
        for (int i = 1; i < n; i++) {
            dp[i] = dp[deque.peek()] + nums[i];
            while (!deque.isEmpty() && i - deque.peek() >= k) {
                deque.poll();
            }
            while (!deque.isEmpty() && dp[i] > dp[deque.peekLast()]) {
                deque.pollLast();
            }

            deque.offer(i);
        }

        return dp[n - 1];
    }

    public int maxResult_optimize2(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        Deque<Integer> deque = new ArrayDeque<>();
        deque.offer(0);
        for (int i = 1; i < n; i++) {
            dp[i] = dp[deque.peek()] + nums[i];
            while (!deque.isEmpty() && i - deque.peek() >= k) {
                deque.poll();
            }
            while (!deque.isEmpty() && dp[i] > dp[deque.peekLast()]) {
                deque.pollLast();
            }

            deque.offer(i);
        }

        return dp[n - 1];
    }

    // 空间如果还要优化就不用dp而是deque<Pair<Integer, Integer>>
    public int maxResult_optimize3(int[] nums, int k) {
        int n = nums.length;
        int score = nums[0];
        Deque<int[]> deque = new ArrayDeque<>();
        deque.offer(new int[]{0, score});
        for (int i = 1; i < n; i++) {
            score = deque.peek()[1] + nums[i];
            while (!deque.isEmpty() && i - deque.peek()[0] >= k) {
                deque.poll();
            }
            while (!deque.isEmpty() && score > deque.peekLast()[1]) {
                deque.pollLast();
            }

            deque.offer(new int[]{i, score});
        }

        return score;
    }


    public static void main(String[] args) {
        maxResult_pq(new int[]{10,-5,-2,4,0,3}, 3);
    }

    // DP + PQ
    // 空间复杂度O(nlongn)不是最优解
    public static int maxResult_pq(int[] nums, int k) {
        int n = nums.length;
        int[] score = new int[n];
        score[0] = nums[0];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        pq.add(new int[] { nums[0], 0 });
        for (int i = 1; i < n; i++) {
            // pop the old index
            while (pq.peek()[1] < i - k) {
                pq.remove();
            }
            score[i] = nums[i] + score[pq.peek()[1]];
            pq.add(new int[] { score[i], i });
        }
        return score[n - 1];
    }
}
