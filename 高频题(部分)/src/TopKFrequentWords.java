import java.util.*;

public class TopKFrequentWords {

    // 注意PQ的写法，然后从小到大排序确保只有k个，复杂度就为o(klogk)
    public List<String> topKFrequent(String[] words, int k) {


        HashMap<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        PriorityQueue<Map.Entry<String,Integer>> pq =  new PriorityQueue<>(
                (a, b) -> a.getValue() == b.getValue() ? b.getKey().compareTo(a.getKey()) : a.getValue() - b.getValue()
        );
//        PriorityQueue<Map.Entry<String,Integer>> pq =  new PriorityQueue<>(
//                (a, b) -> {
//                    if (a.getValue() != b.getValue()) {
//                        return a.getValue() - b.getValue();
//                    } else {
//                        return b.getKey().compareTo(a.getKey());
//                    }
//
//                }
//        );

        for (Map.Entry<String,Integer> entry : map.entrySet()) {
            pq.offer(entry);
            if (pq.size() > k) {
                pq.poll();
            }
        }

        List<String> res = new ArrayList<>();
        while (!pq.isEmpty()) {
            res.add(0, pq.poll().getKey());
        }
        return res;
    }
}
