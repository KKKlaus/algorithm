public class ReverseLinkedList2 {

    // 比较难想
    // https://leetcode.com/problems/reverse-linked-list-ii/discuss/30666/Simple-Java-solution-with-clear-explanation
    public ListNode reverseBetween(ListNode head, int left, int right) {
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
        for (i = 0; i < right - left; i++) { //也可以写成 for (i = left; i < right; i++) {
            start.next = then.next;
            then.next = pre.next;
            pre.next = then;
            then = start.next;
        }
        return dummy.next;
    }
}
