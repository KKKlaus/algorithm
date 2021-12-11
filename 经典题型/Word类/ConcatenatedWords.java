import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class ConcatenatedWords {

    // 就是利用了wordbreak1
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        HashSet<String> preWords = new HashSet<>();
        List<String> res = new ArrayList<>();
        Arrays.sort(words, (a, b) -> (a.length() - b.length()));
        for (String word : words) {
            if (wordBreak(word, preWords)) {
                res.add(word);
            }
            preWords.add(word);
        }
        return res;
    }

    public boolean wordBreak(String s, HashSet<String> wordSet) {
        if (s.isEmpty()) return false;
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;

        // dp[i] = dp[j] == true && s.[i-j] exist in wordDict
        for (int i = 1; i < n + 1; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                }
            }
        }
        return dp[n];
    }
}
