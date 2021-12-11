import java.util.*;

public class kSum {

    public static void main(String[] args) {
//        fourSum(new int[]{1,0,-1,0,-2,2}, 0);
        kSum kSum = new kSum();
        kSum.test();
    }

    private void test() {
        List<List<Integer>> res = fourSum(new int[]{1,0,-1,0,-2,2}, 0);
        for (List<Integer> arr : res) {
            for (int a : arr) {
                System.out.print(a + " ");
            }
            System.out.println();
        }
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        return kSum(nums, target, 4, 0);
    }


    private ArrayList<List<Integer>> kSum(int[] nums, int target, int k, int index) {
        ArrayList<List<Integer>> res = new ArrayList<>();
        if (index >= nums.length) return res;

        if (k == 2) {
            twoSum(nums, index, nums.length - 1, target, res);
        } else {
            for (int i = index; i < nums.length - k + 1; i++) {
                if (i != index && nums[i] == nums[i - 1]) continue;
                ArrayList<List<Integer>> temp = kSum(nums, target - nums[i], k - 1, i + 1);
                for (List<Integer> t : temp) {
                    t.add(0, nums[i]);
                }
                res.addAll(temp);
            }
        }
        return res;
    }

    private void twoSum(int[] nums, int left, int right, int target, List<List<Integer>> res) {
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                List<Integer> path = new ArrayList<Integer>();
                path.add(nums[left++]);
                path.add(nums[right--]);
                res.add(path);
                while (left < right && nums[left] == nums[left - 1]) left++;
                while (left < right && nums[right] == nums[right + 1]) right--;
            } else if (sum > target) {
                right--;
            } else {
                left++;
            }
        }
    }

}
