package Pure电面;

import java.util.HashSet;
import java.util.Set;

public class HappyNumber {

    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();

        while (set.add(n)) {
            int sum = 0;
            while (n > 0) {
                int remain = n % 10;
                sum += remain * remain;
                n  /= 10;
            }

            if (sum == 1) return true;
            else n = sum;
        }
        return false;
    }
}
