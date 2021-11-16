import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutations2 {

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(res, nums, new boolean[nums.length], new ArrayList<>());
        return res;
    }

    private void backtrack(List<List<Integer>> res, int[] nums, boolean[] used, List<Integer> temp) {
        if (temp.size() == nums.length) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;
            used[i] = true;
            temp.add(nums[i]);
            backtrack(res, nums, used, temp);
            temp.remove(temp.size() - 1);
            used[i] = false;
        }
    }
}
