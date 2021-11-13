public class SearchInRotatedSortedArray {

    // 方法1： 先得到最小值的index，然后再来一次binary search，唯一不一样的是realMid
    public int search(int[] nums, int target) {
        int minIndex = getMinIndex(nums);
        int start = 0, end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            int realMid = (mid + minIndex) % nums.length;     // 这一步比较tricky
            if (nums[realMid] == target) return realMid;
            if (nums[realMid] > target) {
                end = mid;
            } else {
                start = mid;
            }
        }

        int startRealMid = (start + minIndex) % nums.length;
        int endRealMid = (end + minIndex) % nums.length;
        if (nums[startRealMid] == target) return startRealMid;
        if (nums[endRealMid] == target) return endRealMid;
        return -1;
    }

    private int getMinIndex(int[] nums) {
        int start = 0, end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] > nums[0]) {
                start = mid;
            } else {
                end = mid;
            }
        }
        int minIndex = nums[start] < nums[end] ? start : end;
        return nums[minIndex] < nums[0] ? minIndex : 0;
    }


    // 方法2： 评论1的图片https://leetcode.com/problems/search-in-rotated-sorted-array/discuss/14436/Revised-Binary-Search
    public int search_2(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] > nums[start]) {    // 因为数组没有duplicate，这里不需要考虑==的情况
                if (nums[start] <= target && target < nums[mid]) {     // 这里注意<=， 有可能没有旋转
                    end = mid;
                } else {
                    start = mid;
                }
            } else {
                if (nums[end] >= target && target > nums[mid]) {   // 注意 >=
                    start = mid;
                } else {
                    end = mid;
                }
            }
        }

        if (nums[start] == target) return start;
        if (nums[end] == target) return end;

        return -1;
    }


    // 上面的按照模板写
    public int search_a(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] > nums[start]) {
                if (target >= nums[start] && target < nums[mid]) {
                    end = mid;
                } else {
                    start = mid;
                }
            } else {
                if (target <= nums[end] && target > nums[mid]) {
                    start = mid;
                } else {
                    end = mid;
                }
            }
        }

        return nums[start] == target ? start : nums[end] == target ? end : -1;

    }
}
