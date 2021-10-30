import java.util.HashMap;

public class PrefixSum {

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5};
        int[] res = getPrefixSum(arr);
        for (int a : res) {
            System.out.println(a);
        }
    }

    // if we want to know subArray sum from index i to j
    // = all previous sum to index (j) - all previous sum to index (i-1) =  prefixSum[j + 1] - prefixSum[i]
    private static int[] getPrefixSum(int[] arr) {
        int[] PrefixSum = new int[arr.length + 1];
        PrefixSum[0] = 0;
        for (int i = 0; i < arr.length; i++) {
            PrefixSum[i + 1] = PrefixSum[i] + arr[i];
        }
        return PrefixSum;
    }

    public int subArraySumEqualsKII(int[] nums, int k) {
        // write your code here
        HashMap<Integer, Integer> map = new HashMap<>(); // preSum - index
        int preSum = 0;
        int res = Integer.MAX_VALUE;
        map.put(0, 0);
        for (int i = 0; i < nums.length; i++) {
            preSum += nums[i];
            if (map.containsKey(preSum - k)) {
                int len = i - map.get(preSum - k) + 1;
                res = Math.min(res, len);
            }
            map.put(preSum, i + 1);
        }

        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
