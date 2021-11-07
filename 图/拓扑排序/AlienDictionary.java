import java.util.*;

public class AlienDictionary {

    // 1. 用StringBuilder更快
    // 2. 不需要count，最后比较 sb.length() == indegreeMap.size()
    // 3. count的增加即这里sb.append()统一放在queue.poll()的时候
    // 4. !!!!!!临界情况要注意： （abcaa, abc）:
    //             if (word1.length() > word2.length() && word1.startsWith(word2)) {
    //                return "";
    //            }
    public String alienOrder(String[] words) {
        HashMap<Character, List<Character>> map = new HashMap<>();
        HashMap<Character, Integer> indegreeMap = new HashMap<>();

        for (String word : words) {
            for (char c : word.toCharArray()) {
                indegreeMap.put(c, 0);
            }
        }

        for (int i = 1; i < words.length; i++) {
            String word1 = words[i - 1], word2 = words[i];
            if (word1.length() > word2.length() && word1.startsWith(word2)) {
                return "";
            }
            for (int j = 0; j < Math.min(word1.length(), word2.length()); j++) {
                char w1 = word1.charAt(j), w2 = word2.charAt(j);
                if (w1 != w2) {
                    if (!map.containsKey(w1)) {
                        map.put(w1, new ArrayList<>());
                    }
                    map.get(w1).add(w2);
                    indegreeMap.put(w2, indegreeMap.get(w2) + 1);
                    break;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        Queue<Character> queue = new LinkedList<>();
        for (char in : indegreeMap.keySet()) {
            if (indegreeMap.get(in) == 0) {
                queue.offer(in);
            }
        }

        while (!queue.isEmpty()) {
            char cur = queue.poll();
            sb.append(cur);
            if (!map.containsKey(cur)) {
                continue;
            }
            for (char next : map.get(cur)) {
                indegreeMap.put(next, indegreeMap.get(next) - 1);
                if (indegreeMap.get(next) == 0) {
                    queue.offer(next);
                }
            }
        }

        return sb.length() == indegreeMap.size() ? sb.toString() : "";
    }



    //反过来easy题 LC953. Verifying an Alien Dictionary
    public boolean isAlienSorted(String[] words, String order) {
        if (words.length <= 1) return true;
        int[] map = new int[26];
        for (int i = 0; i < order.length(); i++) {
            char c = order.charAt(i);
            map[c - 'a'] = i;
        }
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i], word2 = words[i + 1];
            if (word1.length() > word2.length() && word1.startsWith(word2)) return false;  // 临界情况要注意
            for (int j = 0; j < Math.min(word1.length(), word2.length()); j++) {
                char c1= word1.charAt(j), c2 = word2.charAt(j);
                if (map[c1 - 'a'] > map[c2 - 'a']) {
                    return false;
                }
                else if (map[c1 - 'a'] == map[c2 - 'a']) {
                    continue;
                } else {
                    break;
                }
            }
        }
        return true;
    }
}
