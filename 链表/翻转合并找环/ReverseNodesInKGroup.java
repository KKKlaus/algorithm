public class ReverseNodesInKGroup {

    // 方法2：也是利用ReverseLinkedList2的方法，不过一次跑完
    public ListNode reverseKGroup2(ListNode head, int k) {
        if (k == 1) return head;
        int n = countLength(head);
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;


        for (; n >= k; n -= k) {
            ListNode start = prev.next;
            ListNode then = start.next;
            for (int i = 1; i < k; i++) {
                start.next = then.next;
                then.next = prev.next;
                prev.next = then;
                then = start.next;
            }
            prev = start;
        }
        return dummy.next;
    }

    // 方法1：利用ReverseLinkedList2的方法，不过每次都是从头到尾
    public ListNode reverseKGroup(ListNode head, int k) {
        int len = countLength(head);
        for (int i = 1; i + k <= len + 1; i = i + k) {
            head = reverse(head, i, i + k - 1);
        }
        return head;
    }

    private ListNode reverse(ListNode head, int left, int right) {
        if (left == right) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        int i = 0;
        while (i < left - 1) {
            pre = pre.next;
            i++;
        }

        ListNode start = pre.next;
        ListNode then = start.next;
        for (i = left; i < right; i++) {
            start.next = then.next;
            then.next = pre.next;
            pre.next = then;
            then = start.next;
        }
        return dummy.next;
    }

    private int countLength(ListNode head) {
        int count = 0;
        ListNode cur = head;
        while (cur != null) {
            cur = cur.next;
            count++;
        }
        return count;
    }
}
