import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AllOOneDataStructure {

    class AllOne {

        private class Node {
            int count;
            Set<String> keySet;
            Node next;
            Node prev;
            public Node(int cnt) {
                count = cnt;
                keySet = new HashSet<>();
            }
        }

        private Node head;
        private Node tail;
        private Map<Integer, Node> countToNodeMap;
        private Map<String, Integer> keyToCountMap;

        public AllOne() {
            head = new Node(Integer.MIN_VALUE);
            tail = new Node(Integer.MAX_VALUE);
            head.next = tail;
            tail.prev = head;
            countToNodeMap = new HashMap<>();
            keyToCountMap = new HashMap<>();
        }

        public void inc(String key) {
            if (keyToCountMap.containsKey(key)) {
                changeKey(key, 1);
            } else {
                keyToCountMap.put(key, 1);
                if (!countToNodeMap.containsKey(1)) {
                    Node node = new Node(1);
                    addNodeAfter(node, head);
                }
                head.next.keySet.add(key);
                countToNodeMap.put(1, head.next);
            }
        }

        public void dec(String key) {
            if (!keyToCountMap.containsKey(key)) return;
            int count = keyToCountMap.get(key);
            if (count == 1) {
                keyToCountMap.remove(key);
                removeKeyFromNode(countToNodeMap.get(count), key);
            } else {
                changeKey(key, -1);
            }
        }

        public String getMaxKey() {
            return tail.prev == head ? "" : tail.prev.keySet.iterator().next();
        }

        public String getMinKey() {
            return head.next == tail ? "" : head.next.keySet.iterator().next();
        }

        // inc : offset = 1, dec : offset = -1
        private void changeKey(String key, int offset) {
            int count = keyToCountMap.get(key);
            keyToCountMap.put(key, count + offset);
            Node curNode = countToNodeMap.get(count);
            Node newNode;
            if (countToNodeMap.containsKey(count + offset)) {
                newNode = countToNodeMap.get(count + offset);
            } else {
                newNode = new Node(count + offset);
                addNodeAfter(newNode, offset == 1 ? curNode : curNode.prev);  // 这里注意下逻辑
                countToNodeMap.put(count + offset, newNode);
            }
            newNode.keySet.add(key);
            removeKeyFromNode(curNode, key);
        }

        private void removeKeyFromNode(Node node, String key) {
            node.keySet.remove(key);
            if (node.keySet.size() == 0) {
                removeNode(node);
                countToNodeMap.remove(node.count);
            }
        }

        private void removeNode(Node node) {
            Node prev = node.prev;
            Node next = node.next;
            prev.next = next;
            next.prev = prev;
            node.next = null;
            node.prev = null;
        }

        private void addNodeAfter(Node newNode, Node preNode) {
            Node nextNode = preNode.next;
            newNode.next = nextNode;
            nextNode.prev = newNode;
            preNode.next = newNode;
            newNode.prev = preNode;
        }
    }
}
