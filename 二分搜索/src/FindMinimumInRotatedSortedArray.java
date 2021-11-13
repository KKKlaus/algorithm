public class FindMinimumInRotatedSortedArray {

    // Find 和 Search的区别是：
    // Find是不给target，直接找到最小值
    // Search是给一个target，找到target对应的位置


    // 用end比较对第二题比较有利
    public int findMin_2(int[] nums) {
        int start = 0, end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] > nums[end]) {
                start = mid;
            } else {
                end = mid;
            }
        }

        return Math.min(nums[start], nums[end]);
    }

    public int findMin(int[] nums) {
        int start = 0, end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] > nums[0]) {
                start = mid;
            } else {
                end = mid;
            }
        }

        return Math.min(Math.min(nums[start], nums[end]), nums[0]);
    }
}
