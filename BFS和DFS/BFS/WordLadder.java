import java.util.*;

public class WordLadder {

    // bi-direction BFS

    // 需要注意的是这一行把List<String> wordList 换成Set<String> dict
    // 因为contains of list: O(n)   while contains of Set: O(1)!!!!!!!!!!


    // 时间复杂度: O(M^2 * N)where MM is the length of each word and N is the total number of words in the input word list.
    // 我的理解是BFS花费(M + N) 这里N远小于M忽略？ 然后找下一个可能是N * M
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (beginWord.equals(endWord) || !wordList.contains(endWord)) return 0;
        Set<String> dict = new HashSet<>(wordList); // 这一行
        Set<String> begin = new HashSet<>();
        Set<String> end = new HashSet<>();
        Set<String> visited = new HashSet<>();
        begin.add(beginWord);
        end.add(endWord);

        visited.add(beginWord);

        int level = 1;

        while (!begin.isEmpty() && !end.isEmpty()) {
            if (begin.size() > end.size()) {
                Set<String> set = begin;
                begin = end;
                end = set;
            }

            Set<String> temp = new HashSet<>();
            for (String s : begin) {
                StringBuilder sb = new StringBuilder(s);
                for (int j = 0; j < sb.length(); j++) {
                    char c = sb.charAt(j);
                    for (char nc = 'a'; nc <= 'z'; nc++){
                        String ns = sb.substring(0,j) + nc + sb.substring(j + 1);
                        if (end.contains(ns)) return level + 1;

                        if (!visited.contains(ns) && dict.contains(ns)) {
                            temp.add(ns);
                            visited.add(ns);
                        }
                    }
                }
            }
            begin = temp;
            level++;
        }

        return 0;
    }



    // 普通bfs
    public int ladderLength_bfs(String beginWord, String endWord, List<String> wordList) {

        Set<String> wordSet = new HashSet<>(wordList);
        Set<String> visited = new HashSet<>();

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        visited.add(beginWord);

        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                if (cur.equals(endWord)) return level + 1;
                StringBuilder sb = new StringBuilder(cur);
                for (int j = 0; j < sb.length(); j++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == sb.charAt(j)) continue;
                        String next = sb.substring(0,j) + c + sb.substring(j + 1);
                        if (!visited.contains(next) && wordSet.contains(next)) {
                            queue.offer(next);
                            visited.add(next);
                        }
                    }
                }
            }
            level++;
        }

        return 0;
    }
}
