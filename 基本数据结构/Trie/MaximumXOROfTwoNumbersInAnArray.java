import java.util.Arrays;
import java.util.PriorityQueue;

public class MaximumXOROfTwoNumbersInAnArray {

    public static void main(String[] args) {
//        System.out.println(10 >> 4);
//        System.out.println(10 >> 3);
//        System.out.println((10 >> 4) & 1);
        System.out.println((5 >> 3) & 1);
//        System.out.println(1 << 2);
        MaximumXOROfTwoNumbersInAnArray t = new MaximumXOROfTwoNumbersInAnArray();
        t.test();
    }

    private void test() {
//        int res = findMaximumXOR(new int[]{3,10,5,25,2,8});
//        System.out.println(res);
        int[] nums = new int[]{5,2,4,6,6,3};
        int[][] queries = new int[][]{{12,4}, {8,1}, {6,3}};
        maximizeXor(nums, queries);
    }

    //
    public int findMaximumXOR(int[] nums) {
        Trie trie = new Trie();
        for (int num : nums) {
            trie.insert(num);
        }
        int res = Integer.MIN_VALUE;
        for (int num : nums) {
            res = Math.max(res, trie.findMaxXOR(num));
        }
        return res;
    }

    public int[] maximizeXor(int[] nums, int[][] queries) {
        int m = queries.length;
        int k = 0;
        int[] res = new int[m];
        Trie trie = new Trie();
        PriorityQueue<QueryPair> pq = new PriorityQueue<>((a, b) -> a.query[1] - b.query[1]);
        for (int i = 0; i < m; i++) {
            pq.offer(new QueryPair(queries[i], i));
        }
        Arrays.sort(nums);

        int i = 0;

        while (!pq.isEmpty()) {
            QueryPair node = pq.poll();
            int[] query = node.query;
            int index = node.index;
            while (i < nums.length && nums[i] <= query[1]) {
                trie.insert(nums[i]);
                i++;
            }
            res[index] = trie.findMaxXOR(query[0]);
        }
        return res;
    }


    class QueryPair {

        int[] query;
        int index;
        QueryPair(int[] arr, int i) {
            this.query = new int[2];
            this.query[0] = arr[0];
            this.query[1] = arr[1];
            this.index = i;
        }

    }

    class Trie {

        TrieNode root;
        public Trie() {
            this.root = new TrieNode(-1);
        }

        public void insert(int num) {
            TrieNode node = root;
            for (int i =  4; i >= 0; i--) {
                int curBit = (num >> i) & 1;
                if (node.children[curBit] == null) {
                    node.children[curBit] = new TrieNode(curBit);
                }
                node = node.children[curBit];
            }
        }

        public int findMaxXOR(int num) {
            int sum = 0;
            TrieNode node = root;
            for (int i = 4; i >= 0; i--) {
                int curBit = (num >> i) & 1;
                int k = curBit == 1 ? 0 : 1;
                if (node.children[k] == null) {
                    node = node.children[curBit];
                } else {
                    sum += 1 << i;
                    node = node.children[k];
                }
            }
            return sum;
        }
    }

    class TrieNode {

        TrieNode[] children;
        int val;

        public TrieNode(int val) {
            this.children = new TrieNode[2];
            this.val = val;
        }
    }
}
