import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subsets2 {

    // 重复数字
    // 复杂度跟subsets1一样道理
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        dfs(nums, 0, res, new ArrayList<>());
        return res;
    }

    public void dfs(int[] nums, int start, List<List<Integer>> res, List<Integer> temp) {
        res.add(new ArrayList<>(temp));

        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) continue;     // 和上个数字相等就跳过 需要画图理解，很巧妙
            temp.add(nums[i]);
            dfs(nums, i + 1, res, temp);
            temp.remove(temp.size() - 1);
        }
    }


    // subset2和permutation2可以用一样的方法used
    private void backtrack_(int[] nums, List<List<Integer>> res, List<Integer> temp, int start, boolean[] used) {
        res.add(new ArrayList<>(temp));

        for (int i = start; i < nums.length; i++) {
            if (i != 0 && (nums[i] == nums[i - 1] && !used[i - 1])) continue;
            used[i] = true;
            temp.add(nums[i]);
            backtrack_(nums, res, temp, i + 1, used);
            temp.remove(temp.size() - 1);
            used[i] = false;
        }
    }
}
