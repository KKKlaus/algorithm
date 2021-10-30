public class Heapify {

    // Heap/PQ  time complexity:
    // SlidingWindowOrDeque.Utils.Heapify: O(NlogN)
    // poll() / offer() : O(logN)

    public void heapify(int[] A) {
        // write your code here
        for (int start = A.length / 2 - 1; start >= 0; start--) {
            heapify_helper(A, start);
        }
    }

    private void heapify_helper(int[] A, int i) {
        int minIndex = i;
        int l = 2 * i + 1, r = 2 * i + 2;
        if (l < A.length && A[minIndex] > A[l]) minIndex = l;
        if (r < A.length && A[minIndex] > A[r]) minIndex = r;
        if (minIndex == i) return;
        swap(A, minIndex, i);
        heapify_helper(A, minIndex);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
