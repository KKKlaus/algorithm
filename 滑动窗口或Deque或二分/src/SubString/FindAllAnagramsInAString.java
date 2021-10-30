package SubString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FindAllAnagramsInAString {

    public static void main(String[] args) {
        findAnagrams("cbaec", "abc");
    }

    public static List<Integer> findAnagrams(String s, String p) {
        int start = 0, end = 0, count = 0;
        List<Integer> res = new ArrayList<>();
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : p .toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        count = map.size();
        while (end < s.length()) {
            char c1 = s.charAt(end);
            if (map.containsKey(c1)) {
                map.put(c1, map.get(c1) - 1);
                if (map.get(c1) == 0) count--;
            }
            end++;
            while(count == 0) {
                if (end - start == p.length()) {
                    res.add(start);
                }
                char c2 = s.charAt(start);
                if (map.containsKey(c2)) {
                    map.put(c2, map.get(c2) + 1);
                    if (map.get(c2) > 0) count++;
                }
                start++;
            }
        }
        return res;
    }
}
