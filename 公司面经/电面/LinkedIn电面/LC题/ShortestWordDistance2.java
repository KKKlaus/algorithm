import java.util.*;

//      （*两个方法都要掌握 看哪个api调用的频次更高 应该尽可能的降低该api的时间复杂度 即使会令另一个api复杂度更高*）
//        1. 方法1， 针对每个字典存入a list of index， 拿出来之后比较，注意这里的list是sorted的，可以双指针遍历
//        2. 方法2， shortest call 很多次时： 先优先把所有key pair的距离记录下来，这样shortest可以直接call
public class ShortestWordDistance2 {

    // 方法1
    class WordDistance {

        HashMap<String, List<Integer>> map;
        public WordDistance(String[] words) {
            map = new HashMap<>();
            for(int i = 0; i < words.length; i++) {
                String w = words[i];
                if(map.containsKey(w)) {
                    map.get(w).add(i);
                } else {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(i);
                    map.put(w, list);
                }
            }
        }

        // O(m + n) 跟mergesort一个道理，因为两个数组都是排序好的
        public int shortest(String word1, String word2) {
            List<Integer> list1 = map.get(word1);
            List<Integer> list2 = map.get(word2);
            int min = Integer.MAX_VALUE;
            for (int i = 0, j = 0; i < list1.size() && j < list2.size();) {
                int index1 = list1.get(i), index2 = list2.get(j);
                if (index1 < index2) {
                    min = Math.min(min, index2 - index1);
                    i++;
                } else {
                    min = Math.min(min, index1 - index2);
                    j++;
                }
            }
            return min;
        }
    }

    // 方法2
    class WordDistance2 {

        HashMap<String, Integer> shortestMap;
        public WordDistance2(String[] words) {
            shortestMap = new HashMap<>();
            for (int i = 0; i < words.length; i++) {
                for (int j = i + 1; j < words.length; j++) {
                    String word1 = words[i], word2 = words[j];
                    if (word1.equals(word2)) continue;
                    String key = "";
                    key = word1.compareTo(word2) < 0 ? word1 + "," + word2 : word2 + "," + word1;
                    if (shortestMap.containsKey(key)) {
                        shortestMap.put(key, Math.min(shortestMap.get(key), Math.abs(i - j)));
                    } else {
                        shortestMap.put(key, Math.abs(i - j));
                    }
                }
            }
        }


        public int shortest(String word1, String word2) {
            return word1.compareTo(word2) < 0 ? shortestMap.get(word1 + "," + word2) : shortestMap.get(word2 + "," + word1);
        }
    }

    public static void main(String[] args) {
        ShortestWordDistance2 t = new ShortestWordDistance2();
        t.test();
    }

    public void test() {
        WordDistance2 wd = new WordDistance2(new String[]{"practice","makes","perfect","coding","makes"});
        System.out.println(wd.shortest("makes", "coding"));
    }
}
