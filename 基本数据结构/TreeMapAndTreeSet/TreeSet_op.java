import java.util.TreeSet;

public class TreeSet_op {

    public static void main(String[] args) {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(4);
        set.add(1);
        set.add(2);

        System.out.println(set.ceiling(2)); // 2   >=
        System.out.println(set.ceiling(3)); // 4   >=

        System.out.println(set.floor(2));  // 2 <=

        System.out.println(set.lower(2)); // 1 <
        System.out.println(set.higher(2)); // 4 >
    }
}
