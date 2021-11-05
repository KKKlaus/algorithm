public class QuickSort {

    public int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    public void quickSort(int[] nums, int low, int high) {
        if (low >= high) return;
        int mid = partition(nums, low, high);
        quickSort(nums, low, mid - 1);
        quickSort(nums, mid + 1, high);
    }

    public int partition(int[] nums, int low, int high) {
        int pivot = nums[low];
        while (low < high) {
            while(low < high && pivot <= nums[high]) --high;
            swap(nums, low, high);
            while(low < high && pivot >= nums[low]) ++ low;
            swap(nums, low, high);
        }
        nums[low] = pivot;
        return low;
    }

    public void swap(int[] nums, int l, int r) {
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
    }
}
