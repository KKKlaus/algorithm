package SubString;

public class LongestSubstringWithoutRepeatingCharacters {

    public int lengthOfLongestSubstring(String s) {
        int maxLen = 0, start = 0, end = 0, count = 0;
        int[] map = new int[128];
        while (end < s.length()) {
            char c1 = s.charAt(end);
            if (map[c1] > 0) count++;
            map[c1]++;
            end++;

            while (count > 0) {
                char c2 = s.charAt(start);
                if (map[c2] > 1) count--;  // map[c2] will not > 2所以这里 if (map[c2] == 2 ) count--; 也可以
                map[c2]--;
                start++;
            }
            maxLen = Math.max(maxLen, end - start);
        }
        return maxLen;
    }
}
