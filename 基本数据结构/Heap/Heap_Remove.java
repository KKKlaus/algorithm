import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Heap_Remove {

    // 软删除的思想： 不是真的删除，delete的时候只是记下删除的index，当pop的时候才真删除

    public static void main(String[] args) {

    }

    public void test() {

    }

    class ValueIndexPair {
        int value;
        int index;
        ValueIndexPair(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }

    class Heap {

        private PriorityQueue<ValueIndexPair> minHeap;
        private Set<Integer> deleteSet;

        public Heap () {
            minHeap = new PriorityQueue<>((a,b) -> a.value - b.value);
            deleteSet = new HashSet<>();
        }

        public void push(int index, int value) {
            minHeap.add(new ValueIndexPair(value, index));
        }

        public void pop() {
            softDelete();
            minHeap.poll();
        }

        public ValueIndexPair top() {
            softDelete();
            return minHeap.peek();
        }

        public void delete(int index) {
            deleteSet.add(index);
        }

        public boolean isEmpty() {
            return minHeap.isEmpty();
        }

        private void softDelete() {
            while (!minHeap.isEmpty() && deleteSet.contains(minHeap.peek().index)) {
                ValueIndexPair pair = minHeap.poll();
                deleteSet.remove(pair.index);  // Set.remove() -> O(1) set是由树构造的，就是去掉了一个节点，随便重构树(个人理解)
            }
        }

    }
}
