import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordLadder {

    // bi-direction BFS

    // 需要注意的是这一行把List<String> wordList 换成Set<String> dict
    // 因为contains of list: O(n)   while contains of Set: O(1)!!!!!!!!!!


    // 时间复杂度: O(M^2 * N)where M is the length of each word and N is the total number of words in the input word list.
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
}
