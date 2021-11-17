import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum {

    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;

        for (int i = 0; i < n - 3; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) continue;
            for (int j = i + 1; j < n - 2; j++) {
                if (j != i + 1 && nums[j] == nums[j - 1]) continue;
                twoSum(nums, i, j, target);
            }
        }

        return res;
    }

    private void twoSum(int[] nums, int i, int j, int target) {
        int left = j + 1, right = nums.length - 1;
        while (left < right) {
            int sum = nums[i] + nums[j] + nums[left] +nums[right];
            if (sum == target) {
                res.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                left++;
                right--;
                while (left < right && nums[left] == nums[left - 1]) { left++; }
                // while (left < right && nums[right] == nums[right + 1]) { right--; }
            } else if (sum > target) {
                right--;
            } else {
                left++;
            }
        }
    }
}
