import java.util.Arrays;

public class ThreeSumSmaller {

    public int threeSumSmaller(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int res = 0;
        for (int i = 0; i < n - 2; i++) {
            int start = i + 1, end = n - 1;
            while (start < end) {
                if (nums[start] + nums[end] + nums[i] < target) {
                    res += end - start;   // 这一行是关键
                    start++;
                } else {
                    end--;
                }
            }
        }
        return res;
    }
}
