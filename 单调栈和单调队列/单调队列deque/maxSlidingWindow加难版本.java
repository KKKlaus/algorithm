import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class maxSlidingWindow加难版本 {

    // https://www.lintcode.com/problem/751/description
    // 难在滑动窗口大小是 [i - k, i + k]
    // 先用for循环处理先k个(因为没有左边i-k) -> 入队列
    // 循环时注意不是跟当前比，而是A[i + k + 1]，同时注意最后k个是没有右边的(i + k)
    public static void main(String[] args) {
        int[] A = new int[]{1, 3, 2, 1, 5};
        business(A, 2);
    }

    public static int[] business(int[] A, int k) {
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i <= k && i < A.length; i++) {
            while (!deque.isEmpty() && A[i] < A[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }

        int[] res = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            res[i] = A[i] - A[deque.peekFirst()];

            while (!deque.isEmpty() && i - deque.peekFirst() > k) {
                deque.pollFirst();
            }
            if (i + k + 1 < A.length) {
                while (!deque.isEmpty() && A[i + k + 1] < A[deque.peekLast()]) {
                    deque.pollLast();
                }
                deque.offer(i + k + 1);
            }

        }

        return res;
    }
}
