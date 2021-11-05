public class Heapify {

    //balanced binary tree data structure

    // Heap/PQ  time complexity:
    // Heapify: O(NlogN)
    // poll() / offer() : O(logN)
    //Delete random would cause O(n), because search O(n) + delete O(logn)

    public static void main(String[] args) {
        Heapify t = new Heapify();
        t.test();
    }

    public void test() {
        int[] A = new int[]{5,7,4,3,6,2,1,8,9};
        heapify(A);
        for (int a : A) {
            System.out.print(a + " "); //1 3 2 7 6 5 4 8 9 只是确保了最小堆
        }
    }

    public void heapify(int[] A) {
        // write your code here
        for (int start = A.length / 2 - 1; start >= 0; start--) {
            heapifyHelper(A, start);
        }
    }

    private void heapifyHelper(int[] A, int i) {
        int minIndex = i;
        int l = 2 * i + 1, r = 2 * i + 2;
        if (l < A.length && A[minIndex] > A[l]) minIndex = l;  // 如果构建最大堆就是A[minIndex] < A[l]
        if (r < A.length && A[minIndex] > A[r]) minIndex = r;  // 如果构建最大堆就是A[minIndex] < A[l]
        if (minIndex == i) return;
        swap(A, minIndex, i);
        heapifyHelper(A, minIndex);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
