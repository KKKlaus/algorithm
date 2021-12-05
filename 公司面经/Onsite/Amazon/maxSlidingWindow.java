package Amazon;

import java.util.ArrayDeque;
import java.util.Deque;

public class maxSlidingWindow {

    //Time complexity : O(N), since each element is processed exactly twice - it's index added and then removed from the deque.
    //Space complexity : O(N), since {O(N−k+1) is used for an output array and O(k) for a deque.
    public static void main(String[] args) {
        int[] res = maxSlidingWindow(new int[]{5,3,2,4}, 3);
        for (int x : res) {
            System.out.print(x + " ");
        }
    }

    public static int[] maxSlidingWindow(int[] a, int k) {
        if (a == null || k <= 0) {
            return new int[0];
        }
        int n = a.length;
        int[] r = new int[n-k+1];
        int ri = 0;
        // store index
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < a.length; i++) {
            // remove numbers out of range k
            while (!q.isEmpty() && i - q.peek() >= k) {
                q.poll();
            }
            // remove smaller numbers in k range as they are useless
            while (!q.isEmpty() && a[q.peekLast()] < a[i]) {
                q.pollLast();
            }
            // q contains index... r contains content
            q.offer(i);
            if (i >= k - 1) {
                r[ri++] = a[q.peekFirst()];  // res[i - k + 1]
            }
        }
        return r;
    }
}
