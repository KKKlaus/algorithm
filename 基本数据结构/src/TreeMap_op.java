import java.util.TreeMap;

public class TreeMap_op {

    public static void main(String[] args) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(2,200);
        map.put(1,100);
        map.put(4,400);
        System.out.println(map.firstKey()); // 1
        //  floorKey: <= given key, else return null
        System.out.println(map.floorKey(2)); // 2
        System.out.println(map.floorKey(3)); // 2

        // ceilingKey: >= given key, else return null
        System.out.println(map.ceilingKey(3)); // 4
        System.out.println(map.ceilingKey(5)); // null

        System.out.println(map.lowerKey(2)); // 1

        System.out.println(map.higherKey(2)); // 4
    }
}
