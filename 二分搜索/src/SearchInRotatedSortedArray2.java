public class SearchInRotatedSortedArray2 {

    // 跟1的区别就是nums[mid] == nums[start]的情况
    // Because there are 4 cases here:
    //1. target == nums[mid], then we find it;
    //2. nums[left] < nums[mid], then the left part must be sorted;
    //3. nums[left] > nums[mid], then the right part must be sorted;
    //4. nums[left] == nums[mid], we can not make sure which part is sorted, the only thing we can do is scale in the problem size. Since target != nums[mid] == nums[left], we just offset right by left++.
    public boolean search(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) return true;
            if (nums[mid] == nums[start]) start++;      // 这一行很tricky
            else if (nums[mid] > nums[start]) {
                if (nums[start] <= target && target < nums[mid]) {
                    end = mid;
                } else {
                    start = mid;
                }
            } else {
                if (nums[end] >= target && target > nums[mid]) {
                    start = mid;
                } else {
                    end = mid;
                }
            }
        }

        if (nums[start] == target) return true;
        if (nums[end] == target) return true;

        return false;
    }
}
