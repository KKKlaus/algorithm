import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class ConsistentHashing1 {

    // 题目描述：https://www.lintcode.com/problem/519/description
    class Node {
        int start, end, id;
        public Node(int start, int end, int id) {
            this.start = start;
            this.end = end;
            this.id = id;
        }

        public int getSpace() {
            return this.end - this.start;
        }
    }


    public List<List<Integer>> consistentHashing(int n) {
        // write your code here
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> {
            if (a.getSpace() == b.getSpace()) {
                return a.id - b.id;
            } else {
                return b.getSpace() - a.getSpace();
            }
        });

        pq.offer(new Node(0, 359, 1));

        for (int i = 2; i <= n; i++) {
            Node node = pq.poll();
            int start = node.start, end = node.end, id = node.id;
            int mid = (start + end) / 2;
            pq.offer(new Node(start, mid, id));
            pq.offer(new Node(mid + 1, end, i));
        }

        List<List<Integer>> res = new ArrayList<>();
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            res.add(Arrays.asList(node.start, node.end, node.id));
        }
        return res;
    }
}
