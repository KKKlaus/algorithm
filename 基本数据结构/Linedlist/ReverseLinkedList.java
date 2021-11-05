public class ReverseLinkedList {

    public ListNode reverseList(ListNode root) {
        if (root == null) return null;
        ListNode back = null;
        ListNode cur = root;
        while (cur != null) {
            ListNode front = cur.next;
            cur.next = back;
            back = cur;
            cur = front;
        }
        return back;
    }
}
