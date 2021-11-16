import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordSearch2_21年11月8号 {

    Set<String> res = new HashSet<String>();

    public List<String> findWords(char[][] board, String[] words) {
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }

        Set<String> res = new HashSet<String>();
        int m = board.length, n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = board[i][j];
                dfs(board, visited, "", i, j, trie.root, res);
            }
        }
        return new ArrayList<String>(res);
    }

    private void dfs(char[][] board, boolean[][] visited, String str, int x, int y, TrieNode node, Set<String> res) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || visited[x][y]) return;

        char c = board[x][y];
        if (node.children[c - 'a'] == null) return;
        node = node.children[c - 'a'];
        if (node.isWord) {
            res.add(node.word);
        }

        visited[x][y] = true;
        dfs(board, visited, str, x - 1, y, node, res);
        dfs(board, visited, str, x + 1, y, node, res);
        dfs(board, visited, str, x, y - 1, node, res);
        dfs(board, visited, str, x, y + 1, node, res);
        visited[x][y] = false;
    }

    class Trie {

        TrieNode root;
        Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                if (node.children[c - 'a'] == null) {
                    node.children[c - 'a'] = new TrieNode(c);
                }
                node = node.children[c - 'a'];
            }
            node.isWord = true;
            node.word = word;
        }

    }


    class TrieNode {

        TrieNode[] children;
        boolean isWord;
        char val;
        String word;


        TrieNode() {
            children = new TrieNode[26];
        }

        TrieNode(char val) {
            children = new TrieNode[26];
            this.val = val;
        }

    }
}
