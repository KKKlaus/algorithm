import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Autocomplete我的做法 {

    // 跟TrieVersion2想法相同，用到dfs去获取prefix的所有list，时间复杂度比较高
    public static void main(String[] args) {
        Autocomplete我的做法 t = new Autocomplete我的做法();
        t.test();
    }

    private void test() {
        String[] sentences = new String[]{"i love you","island","iroman","i love leetcode"};
        int[] times = new int[]{5,3,2,2};
        AutocompleteSystem(sentences, times);
        input('i');
        input(' ');
        input('a');
        input('#');

        input('i');
        input(' ');
        input('a');
        input('#');

        input('i');
        input(' ');
        input('a');
        input('#');
    }

    Trie trie;
    String prefix = "";
    public void AutocompleteSystem(String[] sentences, int[] times) {
        trie = new Trie();
        for (int i = 0; i < sentences.length; i++) {
            String s = sentences[i];
            TrieNode node = trie.insert(s);
            node.count = times[i];
        }
    }

    public List<String> input(char c) {
        List<String> res = new ArrayList<>();
        if (c == '#') {
            prefix = "";
            return res;
        }
        prefix += c;
        List<TrieNode> list = trie.searchPrefix(prefix);
        PriorityQueue<TrieNode> pq = new PriorityQueue<>((a, b) -> {
            if (a.count == b.count) {
                return a.word.compareTo(b.word);
            } else {
                return b.count - a.count;
            }
        });
        for (TrieNode node : list) {
            pq.offer(node);
        }
        while (!pq.isEmpty() && res.size() < 3) {
            res.add(pq.poll().word);
        }
        for (String s : res) {
            System.out.print(s + " ");
        }
        System.out.println();
        return res;
    }

    class Trie {

        TrieNode root;
        Trie() {
            root = new TrieNode();
        }

        public TrieNode insert(String word) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                if (node.children[c] == null) {
                    node.children[c] = new TrieNode(c);
                }
                node = node.children[c];
            }
            node.isWord = true;
            node.word = word;
            return node;
        }

        public List<TrieNode> searchPrefix(String prefix) {
            List<TrieNode> res = new ArrayList<>();
            TrieNode node = root;
            for (char c : prefix.toCharArray()) {
                if (node.children[c] == null) return res;
                node = node.children[c];
            }
            dfs(res, node);
            return res;
        }

        private void dfs(List<TrieNode> res, TrieNode node) {
            if (node == null) return;
            if (node.isWord) {
                res.add(node);
            }

            for (TrieNode child : node.children) {
                dfs(res, child);
            }
        }

    }

    class TrieNode {

        TrieNode[] children;
        char val;
        boolean isWord;
        String word;
        int count;

        TrieNode() {
            children = new TrieNode[256];
        }
        TrieNode(char c) {
            children = new TrieNode[256];
            this.val = c;
        }
    }
}
