import java.util.ArrayDeque;
import java.util.Deque;

public class Deque_op {

    public static void main(String[] args) {
        print_deque();
    }

    private static void print_deque() {
        Deque<Integer> deque = new ArrayDeque<>();
        // 一般主要还是queue操作： offerFirst(), offerLast(), pollFirst(), pollLast()

        // queue操作
        deque.offer(1);
        deque.offer(2);
        System.out.println(deque.peek());  // 1
        deque.poll();
        System.out.println(deque.peek()); // 2

        deque = new ArrayDeque<>();

        // stack 操作
        deque.push(1);
        deque.push(2);
        System.out.println(deque.peek()); // 2
        System.out.println(deque.peekFirst()); // 2
        System.out.println(deque.peekLast()); // 1
        deque.pollFirst();
        System.out.println(deque.peek()); // 1
    }
}
