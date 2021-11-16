import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountOfSmallerNumbersAfterSelf_21年11月15号 {

    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        ArrayWithIndex[] newNums = new ArrayWithIndex[n];
        for (int i = 0; i < n; i++) {
            newNums[i] = new ArrayWithIndex(nums[i], i);
        }

        int[] res = new int[n];
        mergeSort(newNums, 0, n - 1, res);

        List<Integer> list = new ArrayList<>();
        for (int t : res) {
            list.add(t);
        }
        return list;
    }

    private void mergeSort(ArrayWithIndex[] newNums, int start, int end, int[] res) {
        if (start >= end) return;

        int mid = start + (end - start) / 2;
        mergeSort(newNums, start, mid, res);
        mergeSort(newNums, mid + 1, end, res);
        merge(newNums, start, end, mid, res);
    }

    private void merge(ArrayWithIndex[] newNums, int start, int end, int mid, int[] res) {
        int l = start, r = mid + 1;
        ArrayWithIndex[] buffer = new ArrayWithIndex[end - start + 1];
        int numRightArrayLessThanLeft = 0;
        int k = 0; // buffer index

        while (l <= mid && r <= end) {
            if (newNums[l].val > newNums[r].val) {
                numRightArrayLessThanLeft++;
                buffer[k++] = newNums[r++];
            } else {
                res[newNums[l].originalId] += numRightArrayLessThanLeft;
                buffer[k++] = newNums[l++];
            }
        }

        while (l <= mid) {
            res[newNums[l].originalId] += numRightArrayLessThanLeft;
            buffer[k++] = newNums[l++];
        }

        while (r <= end) {
            buffer[k++] = newNums[r++];
        }

        for (int i = start; i <= end; i++) {
            newNums[i] = buffer[i - start];
        }
    }

    class ArrayWithIndex {

        int val;
        int originalId;
        public ArrayWithIndex(int val, int id) {
            this.val = val;
            this.originalId = id;
        }
    }
}
