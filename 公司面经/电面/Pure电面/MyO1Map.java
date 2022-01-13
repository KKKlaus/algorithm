package Pure电面;

import java.util.Arrays;
import java.util.HashMap;

public class MyO1Map {
    // add 2,4,1
    // index :    0  1  2  3  4  5
    // map   :    0  3  1  0  2  0      ->  (2,1) (4,2) (1,3)  value - count
    // bucket:    0  2  4  1  0  0      ->  (1,2) (2,4) (3,1)  count - value

    // remove(1)   index = 3 == counter
    // index :    0  1  2  3  4  5
    // map   :    0  0  1  0  2  0      ->  (2,1) (4,2) (1,3)  value - count
    // bucket:    0  2  4  0  0  0      ->  (1,2) (2,4) (3,1)  count - value

    // remove(4)  index = 2 != counter
    // index :    0  1  2  3  4  5
    // map   :    0  2  1  0  0  0      ->  (2,1) (4,2) (1,3)  value - count
    // bucket:    0  2  1  0  0  0      ->  (1,2) (2,4) (3,1)  count - value

    class O1Map {
        int N = 10;
        int[] map;
        int[] bucket;
        int counter;
        O1Map() {
            this.map = new int[N];
            this.bucket = new int[N];
            this.counter = 0;
        }

        public boolean add(int n) {
            if (contains(n)) return false;
            ++this.counter;
            map[n] = this.counter;
            bucket[this.counter] = n;
            return true;
        }

        public boolean remove(int n) {
            if (!contains(n)) return false;
            int index = map[n];
            if (index != counter) {
                int temp = bucket[counter];
                bucket[counter] = n;
                bucket[index] = temp;
                map[temp] = index;
            }
            map[n] = 0;
            bucket[counter] = 0;
            counter--;
            return true;
        }

        public void clear() {
            this.counter = -1;
        }

        public boolean contains(int n) {
            if (n >= N) throw new IllegalArgumentException();
            if (map[n] == 0) return false;
            if (map[n] > counter) return false;
            return true;
        }
    }


    // add 2,0,4
    // index :    0  1  2  3  4  5
    // map   :    (2,0) (0,1) (4,2)  value - count
    // bucket:    2  0  4  0  0  0   ->  (0,2) (1,0) (2,4)  count - value

    // remove(0)   index = 1  != counter = 3
    // index :    0  1  2  3  4  5
    // map   :    (2,0) (0,1) (4,2)  value - count
    // bucket:    2  0  4  0  0  0   ->  (0,2) (1,0) (2,4)  count - value
    class O1Map2 {
        int N = 10;
        HashMap<Integer, Integer> map; // value - count
        int[] bucket;                  // count -value
        int counter;
        O1Map2() {
            this.map = new HashMap<>();
            this.bucket = new int[N];
            this.counter = 0;
        }

        public boolean add(int n) {
            if (contains(n)) return false;
            map.put(n, this.counter);
            bucket[this.counter] = n;
            ++this.counter;
            return true;
        }

        public boolean remove(int n) {
            if (!contains(n)) return false;
            int index = map.get(n);
            if (index != counter) {
                int temp = bucket[counter - 1]; // temp = 4
                bucket[counter - 1] = n;
                bucket[index] = temp;
                map.put(temp, index);
            }
            map.remove(n);
            bucket[counter - 1] = 0;
            counter--;
            return true;
        }

        public void clear() {
            this.counter = 0;
        }

        public boolean contains(int n) {
            if (n >= N) throw new IllegalArgumentException();
            if (map.containsKey(n)) return false;
            if (map.get(n) > counter) return false;
            return true;
        }
    }
}
