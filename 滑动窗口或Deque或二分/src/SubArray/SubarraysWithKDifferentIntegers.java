package SubArray;

import java.util.HashMap;

public class SubarraysWithKDifferentIntegers {

    // 巧妙!!!!! Just need one more step to reach the folloing equation:
    //exactly(K) = atMost(K) - atMost(K-1)
    public int subarraysWithKDistinct(int[] nums, int k) {
        return AtMostK(nums, k) - AtMostK(nums, k-1);
    }

    // 重要理解：For me the explaination of res += end - start + 1; it's the amount of subarrays with at most K elements that end at 'end'.
    private int AtMostK(int[] nums, int k) {
        int start = 0, end = 0, res = 0, count = 0;
        HashMap<Integer, Integer> map = new HashMap<>(); // val - count
        while (end < nums.length) {
            int a1 = nums[end];
            map.put(a1, map.getOrDefault(a1, 0) + 1);
            if (map.get(a1) == 1) count++;
            while (count > k) {
                int a2 = nums[start];
                map.put(a2, map.get(a2) - 1);
                if (map.get(a2) == 0) count--;
                start++;
            }
            res += end - start + 1;
            end++;
        }
        return res;
    }
}
