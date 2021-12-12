package Google;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

// 设计一个数据结构，一个queue，需要实现max 返回当前queue的最大值。
public class maxValueInQueue {

    class maxQueue {
        Queue<Integer> queue;
        Deque<Integer> deque;
        public maxQueue() {
            queue = new LinkedList<>();
            deque = new ArrayDeque<>();
        }

        public void offer(int x) {
            queue.offer(x);
            while(!deque.isEmpty() && x > deque.peekLast()) {
                deque.pollLast();
            }
            deque.offerLast(x);
        }

        public int poll() {
            if (queue.isEmpty()) return -1;
            if (deque.peekFirst() == queue.peek()) {
                deque.pollFirst();
            }
            return queue.poll();
        }

        public int getMax() {
            if (deque.isEmpty()) return -1;
            return deque.peekFirst();
        }
    }

    public static void main(String[] args) {
        maxValueInQueue t = new maxValueInQueue();
        t.test();
    }

    private void test() {
        maxQueue maxQueue = new maxQueue();
        maxQueue.offer(-1);
        maxQueue.offer(2);
        System.out.println(maxQueue.getMax());

        maxQueue.offer(8);
        maxQueue.offer(4);
        System.out.println(maxQueue.getMax());

        System.out.println(maxQueue.poll());
    }

}
