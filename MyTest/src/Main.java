import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int[] num = new int[]{3,5,4};
        pq.offer(3);
        pq.offer(5);
        System.out.println(pq.peek());
    }
}