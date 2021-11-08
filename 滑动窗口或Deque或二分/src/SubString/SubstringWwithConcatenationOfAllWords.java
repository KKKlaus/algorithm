package SubString;

import java.util.*;

public class SubstringWwithConcatenationOfAllWords {
    // -----------选做-----------

    public static void main(String[] args) {
        findSubstring("barfoofoothefoobarman", new String[]{"foo","bar"});
    }

    public static List<Integer> findSubstring(String s, String[] words) {
        Map<String, Integer> map = new HashMap<>(), curMap = new HashMap<>();
        List<Integer> res = new ArrayList<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        int len = words[0].length();
        // 要使用双指针方法，这里可能从0-（len-1）开始
        for (int i = 0; i < len; i++) {
            int start = i, end = i, count = 0;
            while (end + len <= s.length()) {
                String s1 = s.substring(end, end + len);
                if (!map.containsKey(s1)) {
                    start = end + len;
                    end = start;
                    curMap.clear();
                    count = 0;
                    continue;
                }
                curMap.put(s1, curMap.getOrDefault(s1, 0) + 1);
                end += len;
                if (curMap.get(s1) <= map.get(s1)) count++;
                while (curMap.get(s1) > map.get(s1)) {
                    String s2 = s.substring(start, start + len);
                    curMap.put(s2, curMap.get(s2) - 1);
                    if (curMap.get(s2) < map.get(s2)) count--;
                    start += len;
                }
                if (count == words.length) {
                    res.add(start);
                    String s2 = s.substring(start, start + len);
                    curMap.put(s2, map.get(s2) - 1);
                    count--;
                    start += len;
                }
            }
            curMap.clear();
        }
        return res;
    }


}
