public class SplitArrayLargestSum {

    // 二分答案
    public int splitArray(int[] nums, int m) {
        int max = 0, sum = 0;
        for (int num : nums) {
            max = Math.max(max, num);
            sum += num;
        }

        int l = max, r = sum;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (valid(mid, nums, m)) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private boolean valid(int target, int[] nums, int m) {
        int count = 1;
        int sum = 0;
        for (int num : nums) {
            sum += num;
            if (sum > target) {
                sum = num;
                count ++;
                if (count > m) return false;
            }
        }
        return true;
    }


    // 自己写比较看得懂的
    public int splitArray_2(int[] nums, int m) {
        int max = 0, sum = 0;
        for (int num : nums) {
            max = Math.max(max, num);
            sum += num;
        }

        int start = max, end = sum;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (isValid(nums, m, mid)) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }

    private boolean isValid(int[] nums, int m, int mid) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
            if (sum > mid) {
                m--;
                sum = num;
            }
        }
        m--;
        return m >= 0;
    }
}
