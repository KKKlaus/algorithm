import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

// 最小堆
public class PriorityQueueDemo {

    public static void main(String[] args) {
        PriorityQueueDemo t = new PriorityQueueDemo();
        t.test();
    }

    private void test() {
        PQ pq = new PQ();
        //5,7,4,3,6,2,1,8,9};
        pq.offer(5);
        pq.offer(7);
        pq.offer(4);
        pq.offer(3);
        pq.offer(6);
        pq.offer(2);
        pq.offer(1);
        pq.offer(8);
        pq.offer(9);

        while (!pq.isEmpty()) {
            int x = pq.poll();
            System.out.println(x + " ");
        }

    }

    class PQ {
        List<Integer> list;
        int size;
        public PQ() {
            list = new ArrayList<>();
            size = 0;
        }

        public boolean offer(int num) {
            list.add(0, num);
            size++;
            heapifyHelper(list, 0);
            return true;
        }

        public int poll() {
            if (size <= 0) return -1;
            int first = list.get(0);
            int end = list.get(size - 1);
            list.remove(list.size() - 1);
            size--;
            if (size > 0) {
                list.set(0, end);
                heapifyHelper(list, 0);
            }
            return first;
        }

        public boolean isEmpty() {
            return list.isEmpty();
        }

        public int peek() {
            return list.get(0);
        }

        private void heapify(List<Integer> list) {
            for (int start = list.size() / 2 - 1; start >= 0; start--) {
                heapifyHelper(list, start);
            }
        }

        private void heapifyHelper(List<Integer> list, int i) {
            int minIndex = i;
            int left = 2 * i + 1, right = 2 * i + 2;
            if (left < size && list.get(minIndex) > list.get(left)) minIndex = left;
            if (right < size && list.get(minIndex) > list.get(right)) minIndex = right;
            if (minIndex == i) return;
            swap(list, minIndex, i);
            heapifyHelper(list, minIndex);
        }

        private void swap(List<Integer> list, int x, int y) {
            int temp = list.get(x);
            list.set(x, list.get(y));
            list.set(y, temp);
        }
    }
}
