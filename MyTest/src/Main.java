import java.lang.reflect.Array;
import java.util.*;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {
        HashMap<int[], Integer> map1 = new HashMap<>();
        HashMap<List<Integer>, Integer> map2 = new HashMap<>();
        map1.put(new int[]{1,2}, 1);
        map2.put(Arrays.asList(1,2), 2);
        System.out.println(map1.get(new int[]{1,2}));
        System.out.println(map2.get(Arrays.asList(1,2)));
    }
}