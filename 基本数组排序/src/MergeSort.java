public class MergeSort {

    // top-down merge sort
    public int[] sortArray(int[] nums) {
        mergeSort(nums, 0, nums.length - 1);
        return nums;
    }

    public void mergeSort(int[] nums, int l, int r) {
        if (l >= r) return;
        int mid = l + (r - l) / 2;
        mergeSort(nums, l, mid);
        mergeSort(nums, mid + 1, r);
        merge(nums, l, r, mid);
    }

    public void merge(int[] nums, int start, int end, int mid) {
        int l = start; int r = mid + 1;
        int[] buffer = new int[end - start + 1];
        int t = 0;

        while (l <= mid && r <= end) {

            if (nums[l] <= nums[r]) {
                buffer[t++] = nums[l++];
            } else {
                buffer[t++] = nums[r++];
            }

        }

        while (l <= mid) buffer[t++] = nums[l++];
        while (r <= end) buffer[t++] = nums[r++];

        for (int i = start; i <= end; i++) {
            nums[i] = buffer[i - start];
        }
    }
}
