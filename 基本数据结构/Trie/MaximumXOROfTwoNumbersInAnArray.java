public class MaximumXOROfTwoNumbersInAnArray {

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

    class Trie {

        TrieNode root;
        public Trie() {
            this.root = new TrieNode(-1);
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
            int sum = 0;
            TrieNode node = root;
            for (int i = 31; i >= 0; i--) {
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
