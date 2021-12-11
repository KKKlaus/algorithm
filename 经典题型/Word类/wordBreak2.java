import java.util.ArrayList;
import java.util.List;

public class wordBreak2 {

    // wordBreak1中为了剪枝加入了visited，这里需要输出所有可能所以不剪枝
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> res = new ArrayList<>();
        dfs(s, wordDict, res, new StringBuilder());
        return res;
    }

    private void dfs(String s, List<String> wordDict, List<String> res, StringBuilder sb) {
        if (s.isEmpty()) {
            res.add(new String(sb).trim());
            return;
        }

        for (String w : wordDict) {
            if (s.startsWith(w)) {
                StringBuilder newSb = new StringBuilder(sb);
                newSb.append(" " + w);
                dfs(s.substring(w.length()), wordDict, res, newSb);
            }
        }
    }
}
