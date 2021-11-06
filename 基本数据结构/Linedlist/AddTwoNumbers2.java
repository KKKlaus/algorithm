import java.util.Stack;

public class AddTwoNumbers2 {

    // 方法1: 用stack

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        while (l1 != null) {
            s1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            s2.push(l2.val);
            l2 = l2.next;
        }

        int sum = 0;
        ListNode tail = new ListNode(0);
        while (!s1.isEmpty() || !s2.isEmpty()) {
            if (!s1.isEmpty()) sum += s1.pop();
            if (!s2.isEmpty()) sum += s2.pop();
            tail.val = sum % 10;
            ListNode head = new ListNode(sum / 10);
            head.next = tail;
            tail = head;
            sum = sum / 10;
        }
        return tail.val == 0? tail.next : tail;
    }

    // 方法2：用addTwoNumbers1的方法即可
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode reverseL1 = reverse(l1);
        ListNode reverseL2 = reverse(l2);
        ListNode reverseSum = addTwoNumbers1(reverseL1, reverseL2);
        return reverse(reverseSum);
    }

    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        int sum = 0;
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (l1 != null || l2 != null) {
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            cur.next = new ListNode(sum % 10);
            cur = cur.next;
            sum = sum / 10;
        }
        if (sum != 0) {
            cur.next = new ListNode(1);
        }
        return dummy.next;
    }

    private ListNode reverse(ListNode head) {
        ListNode back = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode front = cur.next;
            cur.next = back;
            back = cur;
            cur = front;
        }
        return back;
    }
}
