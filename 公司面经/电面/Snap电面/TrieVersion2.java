import java.util.ArrayList;
import java.util.List;

// Snap 电面 trie的followup 出的好呀：https://www.1point3acres.com/bbs/thread-820884-1-1.html
public class TrieVersion2 {

    class TrieNode {
        TrieNode[] children;
        char val;
        boolean isWord;
        String word;
        TrieNode() {
            this.children = new TrieNode[26];
        }
        TrieNode(char c) {
            this.children = new TrieNode[26];
            this.val = c;
        }
    }

    class Trie {

        TrieNode root;
        Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode cur = root;
            for (char c : word.toCharArray()) {
                if (cur.children[c - 'a'] == null) {
                    cur.children[c - 'a'] = new TrieNode(c);
                }
                cur = cur.children[c - 'a'];
            }
            cur.isWord = true;
            cur.word = word;
        }

        public boolean searchWord(String word) {
            TrieNode cur = root;
            for (char c : word.toCharArray()) {
                if (cur.children[c - 'a'] == null) return false;
                cur = cur.children[c - 'a'];
            }
            return cur.isWord;
        }

        public boolean searchPrefix(String prefix) {
            TrieNode cur = root;
            for (char c : prefix.toCharArray()) {
                if (cur.children[c - 'a'] == null) return false;
                cur = cur.children[c - 'a'];
            }
            return true;
        }

        // 要求的是给一个prefix，输出含有这个prefix的所有词
        public List<String> searchWordWithPrefix(String prefix) {
            List<String> res = new ArrayList<>();
            TrieNode cur = root;
            for (char c : prefix.toCharArray()) {
                if (cur.children[c - 'a'] == null) return res;
                cur = cur.children[c - 'a'];
            }
            dfs(res, cur);
            return res;
        }
        // 如果要按照搜索次数排序 -> TreeMap<Integer, List<String>> treemap 次数和对应的字符list

        private void dfs(List<String> res, TrieNode node) {
            if (node == null) return;
            if (node.isWord) {
                res.add(node.word);
            }
            for (TrieNode child : node.children) {
                dfs(res, child);
            }
        }


        // follow up: searchWordWithPrefixVersion2(string prefix) 的prefix参数含有‘？’可以match所有值，
        // e.g.: ab?de 可以match： abcde, abdde,abe‍‌‌‌‌‌‌‍‌‌‌‍‍‌‍‌‍‌‌‌de。要求返回所有含有这个prefix的所有词。
        public List<String> searchWordWithPrefixVersion2(String prefix) {
            List<String> res = new ArrayList<>();
            List<TrieNode> nodeList = helper(prefix);
            for (TrieNode node : nodeList) {
                List<String> part_res = new ArrayList<>();
                dfs(part_res, node);
                res.addAll(part_res);
            }
            return  res;
        }

        public List<TrieNode> helper(String prefix) {
            List<TrieNode> res = new ArrayList<>();
            dfs_TrieNode(root, prefix, 0, res);
            return res;
        }

        private void dfs_TrieNode(TrieNode cur, String prefix, int index, List<TrieNode> res) {
            if (cur == null) return;
            if (index == prefix.length()) {
                res.add(cur);
                return;
            }

            char c = prefix.charAt(index);
            if (c == '?') {
                for (int i = 0; i < 26; i++) {
                    dfs_TrieNode(cur.children[i], prefix, index + 1, res);
                }
            } else {
                dfs_TrieNode(cur.children[c - 'a'], prefix, index + 1, res);
            }
        }



    }

    public static void main(String[] args) {
        TrieVersion2 t = new TrieVersion2();
        t.test();
    }

    private void test() {
        Trie trie = new Trie();
        trie.insert("apple");
        trie.insert("app");
        trie.insert("apef");
        trie.insert("apeg");
        trie.insert("add");
        trie.insert("apd");
//        List<String> res = trie.searchWordWithPrefixVersion2("app");
//        for (String s : res) {
//            System.out.print(s + ",");
//        }

        List<String> res = trie.searchWordWithPrefixVersion2("?");
        for (String s : res) {
            System.out.print(s + ",");
        }
    }
}
