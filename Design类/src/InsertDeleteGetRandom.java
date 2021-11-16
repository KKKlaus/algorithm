import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class InsertDeleteGetRandom {


    // 关键点就是删除的时候空出了那个空位应该跟最后一个互换来填补，然后删除最后一个。
    // Uber OA的那道alloc/erase题就是应用了这个思想
    class RandomizedSet {

        List<Integer> list;  // value
        HashMap<Integer, Integer> map;   // value - index
        Random rand;
        public RandomizedSet() {
            list = new ArrayList<>();
            map = new HashMap<>();
            rand = new Random();
        }

        public boolean insert(int val) {
            if (map.containsKey(val)) return false;
            map.put(val, list.size());
            list.add(val);
            return true;
        }

        public boolean remove(int val) {
            if (!map.containsKey(val)) return false;
            int index = map.get(val);
            if (index != list.size() - 1) {
                int last = list.get(list.size() - 1);
                map.put(last, index);
                list.set(index, last);
            }
            map.remove(val);
            list.remove(list.size() - 1);
            return true;
        }

        public int getRandom() {
            return list.get(rand.nextInt(list.size()));
        }
    }
}
