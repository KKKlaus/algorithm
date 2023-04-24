public class RemoveDuplicatesFromSortedList {

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return head;
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            ListNode front = cur.next;
            if (cur.val == front.val) {
                cur.next = front.next;
            } else {
                cur = cur.next;
            }
        }

        return head;
    }
}
