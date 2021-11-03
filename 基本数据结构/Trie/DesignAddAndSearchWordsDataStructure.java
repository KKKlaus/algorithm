public class DesignAddAndSearchWordsDataStructure {

    public static void main(String[] args) {
        DesignAddAndSearchWordsDataStructure t = new DesignAddAndSearchWordsDataStructure();
        t.test();
    }

    public void test() {
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        System.out.println(wordDictionary.search("pad")); // return False
        System.out.println(wordDictionary.search("bad")); // return True
        System.out.println(wordDictionary.search(".ad")); // return True
        System.out.println(wordDictionary.search("b..")); // return True
    }

    class WordDictionary {

        Trie trie;

        public WordDictionary() {
            trie = new Trie();
        }

        public void addWord(String word) {
            trie.insert(word);
        }

        public boolean search(String word) {
            return trie.search(word, trie.root);
        }
    }

    class Trie {

        TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String s) {
            TrieNode node = root;
            for (char c : s.toCharArray()) {
                if (node.children[c - 'a'] == null) {
                    node.children[c - 'a'] = new TrieNode(c);
                }
                node = node.children[c - 'a'];
            }
            node.isWord = true;
        }

        // 中间的for循环关键
        public boolean search(String s, TrieNode node) {
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == '.') {
                    for (int j = 0; j < 26; j++) {
                        if (node.children[j] != null && search(s.substring(i + 1), node.children[j])) {
                            return true;
                        }
                    }
                    return false;
                } else {
                    if (node.children[c - 'a'] == null) return false;
                    node = node.children[c - 'a'];
                }
            }
            return node.isWord;
        }


        class TrieNode {

            public char val;
            TrieNode[] children = new TrieNode[26];
            public boolean isWord;

            public TrieNode() {
            }

            public TrieNode(char c) {
                this.val = c;
            }
        }
    }
}
