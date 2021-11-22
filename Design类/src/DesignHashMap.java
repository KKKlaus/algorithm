import java.util.List;

public class DesignHashMap {

    class ListNode {
        ListNode next;
        int key, val;
        ListNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    //
    // 如果需要resize：
    // At most 10^4 calls will be made to put, get, and remove. And build in load factor = 0.75 means we can keep these method as O(1)
    // 10000 / 0.75 = 13000 https://leetcode.com/problems/design-hashmap/discuss/
    ListNode[] nodes;
    int size = 10000;
    public DesignHashMap() {
        nodes = new ListNode[size];
    }

    public void put(int key, int value) {
        int idx = hash(key);
        if (nodes[idx] == null) {
            nodes[idx] = new ListNode(-1, -1);
        }
        ListNode prev = find(nodes[idx], key);
        if (prev.next == null) {
            prev.next = new ListNode(key, value);
        } else {
            prev.next.val = value;
        }
    }

    public int get(int key) {
        int idx = hash(key);
        if (nodes[idx] == null) return -1;
        ListNode prev = find(nodes[idx], key);
        if (prev.next == null) return -1;
        else return prev.next.val;
    }

    public void remove(int key) {
        int idx = hash(key);
        if (nodes[idx] == null) return;
        ListNode prev = find(nodes[idx], key);
        if (prev.next == null) return;
        prev.next = prev.next.next;
    }

    private ListNode find(ListNode bucket, int key) {
        ListNode node = bucket, prev = null;
        while (node != null && node.key != key) {
            prev = node;
            node = node.next;
        }
        return prev;
    }

    private int hash(int key) {
        return Integer.hashCode(key) % size;
    }
}
