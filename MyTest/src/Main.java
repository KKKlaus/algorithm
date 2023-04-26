import java.lang.reflect.Array;
import java.util.*;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {
        Main test = new Main();
        System.out.println(test.wordBreak_2("aaab", Arrays.asList("aa","aaa","a","aab")));
    }

    public boolean wordBreak_2(String s, List<String> wordDict) {
        return dfs(s, wordDict, new HashSet<>());
    }

    // aaaab
    // a, aa, ab
    private boolean dfs(String s, List<String> wordDict, HashSet<String> checked) {
        if (s.isEmpty()) return true;
        if (checked.contains(s)) return false;
        checked.add(s);

        for (String word : wordDict) {
            if (s.startsWith(word) && dfs(s.substring(word.length()), wordDict, checked)) {
                return true;
            }
        }
        return false;
    }
}