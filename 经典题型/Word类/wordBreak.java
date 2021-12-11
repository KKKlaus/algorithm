import java.util.HashSet;
import java.util.List;

public class wordBreak {

    // 方法1： TLE 最直接的暴力方法
    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> wordSet = new HashSet<>(wordDict);
        return dfs(s, wordSet);
    }

    private boolean dfs(String s, HashSet<String> wordSet) {
        int len = s.length();
        if (len == 0) return true;

        for (int i = 1; i <= len; i++) {
            if (wordSet.contains(s.substring(0, i)) && dfs(s.substring(i), wordSet)) {
                return true;
            }
        }
        return false;
    }


    // 方法2： 用checked来剪枝
    // worst case :
    //"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab"
    //["a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"]
    public boolean wordBreak_2(String s, List<String> wordDict) {
        return dfs(s, wordDict, new HashSet<>());
    }

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

    // 方法3： dp
    public boolean wordBreak_3(String s, List<String> wordDict) {
        HashSet<String> wordSet = new HashSet<>(wordDict);
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;

        // dp[i] = dp[j] == true && s.[i-j] exist in wordDict
        for (int i = 1; i < n + 1; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordSet.contains(s.substring(j, i))) {  // 这里注意因为dp[j]是前j个，所以s.substring从j开始
                    dp[i] = true;
                }
            }
        }
        return dp[n];
    }
}
