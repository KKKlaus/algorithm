package Pure电面;

import java.util.HashMap;

// 个人方法：O(1)set可以考虑HashMap<value, version>
public class O1Map {

    class MyMap {

        HashMap<Integer, int[]> map;   // key - {value, versionID}
        int versionId;

        MyMap() {
            map = new HashMap<>();
            versionId = 0;
        }

        public boolean delete(int key) {
            if (!map.containsKey(key)) return false;
            int[] node = map.get(key);
            if (node[1] != versionId) return false;
            map.remove(key);
            return true;
        }

        public int lookUp(int key) {
            if (!map.containsKey(key)) throw new IllegalArgumentException("key doesn't exist!");
            int[] node = map.get(key);
            if (node[1] != versionId)  throw new IllegalArgumentException("key doesn't exist!");
            else return node[0];
        }

        public void clear() {
            versionId++;
        }

        public void add(int key, int value) {
            if (!map.containsKey(key)) {
                map.put(key, new int[]{value, versionId});
            } else {
                int[] node = map.get(key);
                if (node[1] == versionId) {
                    // 看需求，如果需要抛出错误
//                    throw new IllegalArgumentException("duplicate key");
                    // 如果需要修改值
                    node[0] = value;
                }
            }
        }

    }

    private void test() {
        MyMap myMap = new MyMap();
    }

    public static void main(String[] args) {
        O1Map t = new O1Map();
        t.test();
    }
}
