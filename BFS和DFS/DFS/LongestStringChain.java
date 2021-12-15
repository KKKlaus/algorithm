import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class LongestStringChain {

    // Top Down: DFS + Memo
    public int longestStrChain(String[] words) {
        Map<String, Integer> memo = new HashMap<>();
        HashSet<String> wordSet = new HashSet<>(Arrays.asList(words));
        int ans = 0;
        for (String word : words) {
            ans = Math.max(ans, dfs(memo, wordSet, word));
        }
        return ans;
    }

    private int dfs(Map<String, Integer> memo, HashSet<String> wordSet, String word) {
        if (memo.containsKey(word)) return memo.get(word);

        int res = 1;
        for (int i = 0; i < word.length(); i++) {
            String prev = word.substring(0, i) + word.substring(i + 1);
            if (!wordSet.contains(prev)) continue;
            res = Math.max(res, dfs(memo, wordSet, prev) + 1);
        }
        memo.put(word, res);
        return res;
    }


    //方法2：bottom up DP
    public int longestStrChain_2(String[] words) {
        HashMap<String, Integer> dp = new HashMap<>();
        Arrays.sort(words, (a, b) -> (a.length() - b.length()));
        int res = 0;
        for (String word : words) {
            int best = 0;
            for (int i = 0; i < word.length(); i++) {
                String pre = word.substring(0, i) + word.substring(i + 1);
                best = Math.max(best, dp.getOrDefault(pre, 0) + 1);
            }
            dp.put(word, best);
            res = Math.max(res, best);
        }
        return res;
    }
}
