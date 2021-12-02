package Oracle;

import java.util.PriorityQueue;

public class FindMedianFromDataStream {

    class MedianFinder {

        PriorityQueue<Integer> small;    // maxHeap
        PriorityQueue<Integer> large;    // minHeap
        // small:    1
        // large:    2  3
        public MedianFinder() {
            small = new PriorityQueue<>((a,b) -> (b - a));
            large = new PriorityQueue<>();
        }

        public void addNum(int num) {
            if(small.size() == large.size()) {
                small.offer(num);
                int x = small.poll();
                large.offer(x);
            } else {
                large.offer(num);
                int x = large.poll();
                small.offer(x);
            }
        }

        public double findMedian() {
            if (small.size() == large.size()) {
                return ((double) small.peek() + (double) large.peek()) / 2;
            } else {
                return large.peek();
            }
        }
    }
}
