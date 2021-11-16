import java.util.*;

public class InsertDeleteGetRandomWithDuplicate_21年10月12号 {

    //   先看InsertDeleteGetRandom1
    class RandomizedCollection {

        List<Integer> list;  // value
        HashMap<Integer, HashSet<Integer>> map;  // value - {index}
        Random rand;
        public RandomizedCollection() {
            list = new ArrayList<>();
            map = new HashMap<>();
            rand = new Random();
        }

        public boolean insert(int val) {
            boolean contains = map.containsKey(val);
            if (!contains) {
                map.put(val, new HashSet<>());
            }
            map.get(val).add(list.size());
            list.add(val);
            return !contains;
        }

        public boolean remove(int val) {
            if (!map.containsKey(val)) return false;
            HashSet<Integer> set = map.get(val);
            int index = set.iterator().next();  // index will be deleted
            set.remove(index);
            if (index < list.size() - 1) {
                int lastOne = list.get(list.size() - 1);
                list.set(index, lastOne);
                map.get(lastOne).remove(list.size() - 1);
                map.get(lastOne).add(index);
            }

            list.remove(list.size() - 1);
            if (map.get(val).size() == 0) {
                map.remove(val);
            }

            return true;
        }

        public int getRandom() {
            return list.get(rand.nextInt(list.size()));
        }
    }
}
