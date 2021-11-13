public class FindMinimumInRotatedSortedArray2 {

    // 这里用二分模板start + 1 < end 不太行
    public int findMin(int[] nums) {
        int start = 0, end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            // 接下来三行对这道题可以不加，但是如果要求最小值对应的index则需要加，详情见第一个comment: https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/discuss/48808/My-pretty-simple-code-to-solve-it
            if (nums[end - 1] > nums[end]) {
                start = end;
                break;
            }
            if (nums[mid] == nums[end]) {
                end--;
            } else if (nums[mid]  > nums[end]) {
                start = mid;
            } else {
                end = mid;
            }
        }

        return Math.min(nums[start], nums[end]);
    }
}
