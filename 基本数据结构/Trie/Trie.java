import java.util.HashMap;
import java.util.Map;

public class Trie {
    // 时间复杂度：构建树为所有字符串长度, insert 和search 为单个字符串长度

    public static void main(String[] args) {
        Trie t = new Trie();
        t.test();
    }

    private void test() {
        Trie_ trie = new Trie_();
        trie.insert("apple");
        trie.insert("apptt");
        System.out.println(trie.search("app")); // false
        System.out.println(trie.search("apptt")); // true
        System.out.println(trie.startsWith("ap")); // true
    }

    // Follow up: LC1804. TrieNode加上 count和end来表示单词/前缀数量

    class Trie_ {

        // TrieNode 最好放外面
        class TrieNode {

            public Map<Character, TrieNode> children;
            public char val;
            public boolean isWord;

            public TrieNode(char c) {
                this.val = c;
                children = new HashMap<>();
            }
        }

        private TrieNode root;

        public Trie_() {
            root = new TrieNode(' ');
        }

        public void insert(String word) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (!node.children.containsKey(c)) {
                    node.children.put(c, new TrieNode(c));
                }
                node = node.children.get(c);
            }
            node.isWord = true;
        }

        public boolean search(String word) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (!node.children.containsKey(c)) return false;
                node = node.children.get(c);
            }
            return node.isWord;
        }

        public boolean startsWith(String prefix) {
            TrieNode node = root;
            for (int i = 0; i < prefix.length(); i++) {
                char c = prefix.charAt(i);
                if (!node.children.containsKey(c)) return false;
                node = node.children.get(c);
            }
            return true;
        }
    }
}



