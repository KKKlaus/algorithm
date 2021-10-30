package SubString;

import java.util.HashMap;

public class LongestSubstringWithAtMostKDistinctCharacters {

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int maxLen = 0, start = 0, end = 0, count = 0;
        int[] map = new int[128];
        while (end < s.length()) {
            char c1 = s.charAt(end);
            if (map[c1] == 0) count++;
            map[c1]++;
            end++;
            while (count > k) {
                char c2 = s.charAt(start);
                map[c2]--;
                if (map[c2] == 0) count--;
                start++;
            }
            maxLen = Math.max(maxLen, end - start);
        }
        return maxLen;
    }

    //用hashmap代替的话
    public int lengthOfLongestSubstringKDistinct_2(String s, int k) {
        int maxLen = 0, start = 0, end = 0, count = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        while (end < s.length()) {
            char c1 = s.charAt(end);
            map.put(c1, map.getOrDefault(c1, 0) + 1);
            if (map.get(c1) == 1) count++;
            end++;
            while (count > k) {
                char c2 = s.charAt(start);
                map.put(c2, map.get(c2) - 1);
                if (map.get(c2) == 0) count--;
                start++;
            }
            maxLen = Math.max(maxLen, end - start);
        }
        return maxLen;
    }

}
