import java.util.ArrayDeque;
import java.util.Deque;

// 这道题跟MinimumSizeSubarraySum很类似，但那道题因为全是正数所以可以用双指针，这里有负数所以不行

public class ShortestSubarrayWithSumatLeastK {

    // 注意prefixSum 写成long类型
    public int shortestSubArray(int[] nums, int k) {
        long[] prefixSum = getPrefixSum(nums);
        Deque<Integer> deque = new ArrayDeque<>(); // store the index
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < prefixSum.length; i++) {
            while (!deque.isEmpty() && prefixSum[i] - prefixSum[deque.peekFirst()] >= k) {
                int index = deque.pollFirst(); // pop出来因为从他开始再往后的必然会更长
                // prefix i - prefix[j] -> subarraysum 0 - j - 1.       0 - i - 1
                res = Math.min(res, i - index);
            }
            // loop2: make sure the deque is increasing
            //  help us make the subarray length shorter and sum bigger. So no need to keep d.back() in our deque.
            //
            //More detailed on this, we always add at the LAST position
            //B[d.back] <- B[i] <- ... <- B[future id]
            //B[future id] - B[d.back()] >= k && B[d.back()] >= B[i]
            //B[future id] - B[i] >= k too
            while (!deque.isEmpty() && prefixSum[i] <= prefixSum[deque.peekLast()]) {
                deque.pollLast();
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
