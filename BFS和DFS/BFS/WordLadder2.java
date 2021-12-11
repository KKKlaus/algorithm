import java.util.*;

public class WordLadder2 {

    // https://www.youtube.com/watch?v=mIZJIuMpI2M
    // 时间复杂度见leetcode solution
    public static void main(String[] args) {
        findLadders("hit", "cog", Arrays.asList("hot","dot","dog","lot","log","cog"));
    }

    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> wordSet = new HashSet<String>(wordList);
        List<List<String>> res = new ArrayList<>();
        HashMap<String, List<String>> nodeNeighbors = new HashMap<>();
        HashMap<String, Integer> distance = new HashMap<>();

        wordSet.add(beginWord);
        bfs(beginWord, endWord, wordSet, nodeNeighbors, distance);
        dfs(beginWord, endWord, nodeNeighbors, distance, res, new ArrayList<>());
        return res;
    }

    private static void bfs(String beginWord, String endWord, HashSet<String> wordSet, HashMap<String, List<String>> nodeNeighbors, HashMap<String, Integer> distance) {
        for (String s : wordSet) {
            nodeNeighbors.put(s, new ArrayList<>());
        }

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        distance.put(beginWord, 0);

        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean found = false;
            for (int k = 0; k < size; k++) {
                String cur = queue.poll();
                int curDistance = distance.get(cur);
                for (int i = 0; i < cur.length(); i++) {
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        if (ch == cur.charAt(i)) continue;
                        String neighbor = cur.substring(0, i) + ch + cur.substring(i + 1);
                        if (!wordSet.contains(neighbor)) continue;
                        nodeNeighbors.get(cur).add(neighbor);
                        if (!distance.containsKey(neighbor)) { // check if exist
                            distance.put(neighbor, curDistance + 1);
                            queue.offer(neighbor);
                            if (neighbor.equals(endWord)) {
                                found = true;
                            }
                        }
                    }
                }
            }
            if (found) break;
        }
    }

    private static void dfs(String cur, String end, HashMap<String, List<String>> nodeNeighbors, HashMap<String, Integer> distance, List<List<String>> res, List<String> temp) {
        temp.add(cur);
        if (cur.equals(end)) {
            res.add(new ArrayList<>(temp));
        } else {
            for (String next : nodeNeighbors.get(cur)) {
                if (distance.get(next) == distance.get(cur) + 1) {
                    dfs(next, end, nodeNeighbors, distance, res, temp);
                }
            }
        }

        temp.remove(cur);
    }
}
