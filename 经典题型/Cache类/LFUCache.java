import java.util.HashMap;
import java.util.LinkedHashSet;

public class LFUCache {

    // 3hashmap + 1 linkedHashSet
    // 情况很多需要考虑，这道题要多练
    public static void main(String[] args) {
        LFUCache lfu = new LFUCache(2);
        lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
        lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
        System.out.println(lfu.get(1));      // return 1
        // cache=[1,2], cnt(2)=1, cnt(1)=2
        lfu.put(3, 3);   // 2 is the LFU key because cnt(2)=1 is the smallest, invalidate 2.
        // cache=[3,1], cnt(3)=1, cnt(1)=2
        System.out.println(lfu.get(2));      // return -1 (not found)
        System.out.println(lfu.get(3));      // return 3
        // cache=[3,1], cnt(3)=2, cnt(1)=2
        lfu.put(4, 4);   // Both 1 and 3 have the same cnt, but 1 is LRU, invalidate 1.
        // cache=[4,3], cnt(4)=1, cnt(3)=2
        System.out.println(lfu.get(1));      // return -1 (not found)
        System.out.println(lfu.get(3));      // return 3
        // cache=[3,4], cnt(4)=1, cnt(3)=3
        System.out.println( lfu.get(4));
    }

    HashMap<Integer, Integer> keyToValMap;
    HashMap<Integer, Integer> keyToCountMap;
    HashMap<Integer, LinkedHashSet<Integer>> countToKeysMap;
    int capacity;
    int min;
    public LFUCache(int capacity) {
        keyToValMap = new HashMap<>();
        keyToCountMap = new HashMap<>();
        countToKeysMap = new HashMap<>();
        this.capacity = capacity;
        min = -1;
    }

    public int get(int key) {
        if (!keyToValMap.containsKey(key)) return -1;

        // update keyToCountMap
        int count = keyToCountMap.get(key);
        keyToCountMap.put(key, count + 1);

        // update countToKeysMap:
        countToKeysMap.get(count).remove(key);
        if (!countToKeysMap.containsKey(count + 1)) countToKeysMap.put(count + 1, new LinkedHashSet<>());
        countToKeysMap.get(count + 1).add(key);

        if (count == min && countToKeysMap.get(count).size() == 0) {
            min++;
            countToKeysMap.remove(count);
        }

        return keyToValMap.get(key);
    }

    public void put(int key, int value) {
        if (capacity == 0) return;

        // if already exist
        if (keyToValMap.containsKey(key)) {
            keyToValMap.put(key, value);
            get(key);
            return;
        }

        // if not exist

        // check if up to capacity
        if (keyToValMap.size() == capacity) {
            int evictKey = countToKeysMap.get(min).iterator().next();
            countToKeysMap.get(min).remove(evictKey);
            if (countToKeysMap.get(min).size() == 0) {
                countToKeysMap.remove(min);
                min++;
            }
            keyToValMap.remove(evictKey);
            keyToCountMap.remove(evictKey);
        }

        // add new
        min = 1;
        keyToValMap.put(key, value);
        keyToCountMap.put(key, min);
        if (!countToKeysMap.containsKey(min)) countToKeysMap.put(min, new LinkedHashSet<>());
        countToKeysMap.get(min).add(key);


    }
}
