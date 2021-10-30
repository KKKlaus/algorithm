package SubArray;

import java.util.ArrayDeque;
import java.util.Deque;

public class ShortestSubarrayWithSumatLeastK {

    // 注意prefixSum 写成long类型
    public int shortestSubArray(int[] nums, int k) {
        long[] prefixSum = getPrefixSum(nums);
        Deque<Integer> deque = new ArrayDeque<>(); // store the index
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < prefixSum.length; i++) {
            // loop1: make sure the deque is increasing  （1 2 循环调换无所谓）
            while (!deque.isEmpty() && prefixSum[i] <= prefixSum[deque.peekLast()]) {
                deque.pollLast();
            }
            // loop2
            while (!deque.isEmpty() && prefixSum[i] - prefixSum[deque.peekFirst()] >= k) {
                int index = deque.pollFirst();
                // prefix i - prefix[j] -> subarraysum 0 - j - 1.       0 - i - 1
                res = Math.min(res, i - index);
            }
            deque.addLast(i);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private long[] getPrefixSum(int[] nums) {
        long[] prefixSum = new long[nums.length + 1];
        prefixSum[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
        return prefixSum;
    }
}
