import java.util.*;

public class WordLadder2 {

    // https://www.youtube.com/watch?v=mIZJIuMpI2M
    // 时间复杂度见leetcode solution
    public static void main(String[] args) {
//        findLadders("red", "tax", Arrays.asList("ted","tex","red","tax","tad","den","rex","pee"));
        Set<Integer> set = new HashSet<>();
        set.add(2);
        set.add(41);
        System.out.println(set.size());
        int x = set.iterator().next();
        System.out.println(set.size());
        set.remove(x);
        set.add(x);
        x = set.iterator().next();
    }

    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> wordSet = new HashSet<>(wordList);
        HashMap<String, List<String>> neighbors = new HashMap<>();
        HashMap<String, Integer> levelMap = new HashMap<>();
        List<List<String>> res = new ArrayList<>();

        bfs(beginWord, endWord, wordSet, neighbors, levelMap);
        dfs(beginWord, endWord, neighbors, levelMap, new ArrayList<>(), res);
        return res;
    }

    public static void bfs(String beginWord, String endWord, HashSet<String> wordSet, HashMap<String, List<String>> neighbors, HashMap<String, Integer> levelMap) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        levelMap.put(beginWord, 0);

        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean found = false;
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                int curLevel = levelMap.get(cur);
                StringBuilder sb = new StringBuilder(cur);
                for (int j = 0; j < sb.length(); j++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == sb.charAt(j)) continue;
                        String next = sb.substring(0,j) + c + sb.substring(j + 1);
                        if (!wordSet.contains(next)) continue;
                        if (!neighbors.containsKey(cur)) neighbors.put(cur, new ArrayList<>());
                        neighbors.get(cur).add(next);
                        if (!levelMap.containsKey(next)) {
                            levelMap.put(next, curLevel + 1);
                            if (endWord.equals(next)) {
                                found = true;
                            } else {
                                queue.offer(next);
                            }
                        }
                    }
                }

            }

            if (found) break;
        }
    }


    public static void dfs(String curWord, String endWord, HashMap<String, List<String>> neighbors, HashMap<String, Integer> levelMap, List<String> path,  List<List<String>> res) {
        path.add(curWord);
        if (curWord.equals(endWord)) {
            res.add(new ArrayList<>(path));
        } else {
            if (neighbors.containsKey(curWord)) {
                for (String neighbor : neighbors.get(curWord)) {
                    if (levelMap.get(neighbor) == levelMap.get(curWord) + 1) {
                        dfs(neighbor, endWord, neighbors, levelMap, path, res);
                    }
                }
            }

        }

        path.remove(path.size() - 1);
    }
}
