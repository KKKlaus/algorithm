import java.util.PriorityQueue;

public class MergeKSortedLists {

    // 复杂度: O(Nlogk) k为list数量， N为所有节点数， 因为while循环会遍历每个节点，offer操作是O(logk)

    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);


        for (ListNode node : lists) {
            if (node != null) {
                pq.offer(node);
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        while (!pq.isEmpty()) {
            ListNode node = pq.poll();
            cur.next = node;
            if(node.next != null) pq.offer(node.next);
            cur = cur.next;
        }
        return dummy.next;
    }
}
