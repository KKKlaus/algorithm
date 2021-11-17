import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {

    // 排序，遍历+twosum双指针，注意去重
    // O(n*n)
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            twoSum(nums, i + 1, nums.length - 1, nums[i]);
        }
        return res;
    }

    private void twoSum(int[] nums, int left, int right, int t) {
        while (left < right) {
            int sum = nums[left] + nums[right] + t;
            if (sum == 0) {
                res.add(Arrays.asList(t, nums[left], nums[right]));
                left++;
                right--;
                while (left < right && nums[left] == nums[left - 1]) {     // 这里去重很关键
                    left++;
                }
            } else if (sum > 0) {
                right--;
            } else {
                left++;
            }
        }
    }
}
