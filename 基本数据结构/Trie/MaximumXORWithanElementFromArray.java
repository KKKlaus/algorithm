import java.util.Arrays;

public class MaximumXORWithanElementFromArray {

    // 需要非常注意的两点
    // 1. queries排序会导致index变化，所以额外维护一个pair[]来记录index - 35行 class Pair
    // 2. 可能查询的值比nums里面的都小，所以trie中findMaxXOR中for循环中需要判断node == null?    ---67行

    public int[] maximizeXor(int[] nums, int[][] queries) {
        int n = queries.length;
        Pair[] arr = new Pair[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Pair(queries[i], i);
        }

        Arrays.sort(arr, (a, b) -> (a.query[1] - b.query[1]));
        Arrays.sort(nums);
        Trie trie = new Trie();


        int[] res = new int[n];
        int k = 0;
        for (Pair pair : arr) {
            int value = pair.query[0], max = pair.query[1], index = pair.index;
            while (k < nums.length && nums[k] <= max) {
                trie.insert(nums[k]);
                k++;
            }
            res[index] = trie.findMaxXOR(value);
        }

        return res;
    }

    class Pair {

        int[] query;
        int index;
        public Pair(int[] query, int index) {
            this.query = query;
            this.index = index;
        }
    }

    class Trie {

        TrieNode root;
        public Trie() {
            root = new TrieNode();
        }

        public void insert(int num) {
            TrieNode node = root;
            for (int i = 31; i >= 0; i--) {
                int curBit = (num >> i) & 1;
                if (node.children[curBit] == null) {
                    node.children[curBit] = new TrieNode(curBit);
                }
                node = node.children[curBit];
            }
        }

        public int findMaxXOR(int num) {
            TrieNode node = root;
            int res = 0;
            for (int i = 31; i >= 0; i--) {
                if (node == null) return -1;    // 这一行是edge case 格外注意
                int curBit = (num >> i) & 1;
                int k = curBit == 1 ? 0 : 1;
                if (node.children[k] == null) {
                    node = node.children[curBit];
                } else {
                    res += 1 << i;
                    node = node.children[k];
                }
            }
            return res;
        }
    }

    class TrieNode {

        TrieNode[] children = new TrieNode[2];
        int val;
        public TrieNode() {}
        public TrieNode(int val) {
            this.val = val;
        }
    }
}


