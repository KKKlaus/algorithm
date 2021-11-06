public class PalindromeLinkedList {

    public boolean isPalindrome(ListNode head) {
        ListNode firstEnd = findMid(head);
        ListNode secondHead = reverse(firstEnd.next);
        while(secondHead != null) {
            if (head.val != secondHead.val) return false;
            head = head.next;
            secondHead = secondHead.next;
        }
        return true;

    }

    private ListNode reverse(ListNode head) {
        ListNode back = null, cur = head;
        while (cur != null) {
            ListNode front = cur.next;
            cur.next = back;
            back = cur;
            cur = front;
        }
        return back;
    }

    private ListNode findMid(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
