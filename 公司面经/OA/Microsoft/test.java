package Microsoft;

import java.util.HashSet;

public class test {

    public static void main(String[] args) {

    }

    public static boolean helper(int[] arr) {
        HashSet<Integer> set = new HashSet<>();
        for (int a : arr) {
            if (!set.contains(a)) {
                set.add(a);
            } else {
                set.remove(a);
            }
        }
        return set.isEmpty();
    }
}
