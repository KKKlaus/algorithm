import java.util.ArrayList;
import java.util.List;


// Input: nums = [1,2,3]
// Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
public class Subsets {
    // 复杂度搞清楚下
    //Time complexity: {O}(N * 2^N)
    //
    // to generate all subsets and then copy them into output list.
    //
    //Space complexity: {O}(N). We are using O(N) space to maintain curr,
    // and are modifying curr in-place with backtracking. Note that for space complexity analysis, we do not count space that is only used for the purpose of returning output, so the output array is ignored.


    //The recursive function is called 2^n times.
    //
    // Because we have 2 choices at each iteration in nums array.
    // Either we include nums[i] in the current set, or we exclude nums[i]. This array nums is of size n = number of elements in nums.
    //We need to create a copy of the current set because we reuse the original one to build all the valid subsets.
    // This copy costs O(n) and it is performed at each call of the recursive function, which is called 2^n times as mentioned in above. So total time complexity is O(n x 2^n).

    public static void main(String[] args) {
        subsets(new int[]{1, 2, 3});
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(nums, 0, res, new ArrayList<>());
        return res;
    }

    public static void dfs(int[] nums, int start, List<List<Integer>> res, List<Integer> temp) {
        res.add(new ArrayList<>(temp));

        for (int i = start; i < nums.length; i++) {
            temp.add(nums[i]);
            dfs(nums, i + 1, res, temp);
            temp.remove(temp.size() - 1);
        }
    }
}
