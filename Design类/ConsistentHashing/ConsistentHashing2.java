import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;

// 题目描述：https://www.lintcode.com/problem/520/description
public class ConsistentHashing2 {

    class Solution {

        private int n, k;
        private TreeMap<Integer, Integer> map;
        /*
         * @param n: a positive integer
         * @param k: a positive integer
         * @return: a Solution object
         */
        public Solution(int n, int k) {
            // Write your code here
            this.n = n;
            this.k = k;
            this.map = new TreeMap<>();
        }

        /*
         * @param machine_id: An integer
         * @return: a list of shard ids
         */
        public List<Integer> addMachine(int machine_id) {
            // write your code here
            List<Integer> res = new ArrayList<>();
            Random rand = new Random();

            for (int i = 0; i < k; ) {
                int shardId = rand.nextInt(n);
                if (map.containsKey(shardId)) continue;
                else {
                    i++;
                    map.put(shardId, machine_id);
                    res.add(shardId);
                }
            }
            return res;
        }

        /*
         * @param hashcode: An integer
         * @return: A machine id
         */
        public int getMachineIdByHashCode(int hashcode) {
            // write your code here
            Integer ceilingKey = map.ceilingKey(hashcode);
            return ceilingKey == null ? map.get(map.firstKey()) : map.get(ceilingKey); // 注意的是当找不到下一个时应该返回第一个，因为看成了一个圆
        }
    }
}
