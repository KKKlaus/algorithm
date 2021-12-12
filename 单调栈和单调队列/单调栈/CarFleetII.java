import java.util.Arrays;
import java.util.Stack;

public class CarFleetII {

    // https://www.youtube.com/watch?v=0tB6Ozo40Kk
    public double[] getCollisionTimes(int[][] cars) {
        int n = cars.length;
        double[] res = new double[n];
        Arrays.fill(res, -1);
        Stack<Integer> stack = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && cars[i][1] <= cars[stack.peek()][1]) {
                stack.pop();
            }

            while (!stack.isEmpty()) {
                int candidate = stack.peek();
                double currentTime = (double)(cars[candidate][0] - cars[i][0]) /  (cars[i][1] - cars[candidate][1]);
                if (currentTime <= res[candidate] || res[candidate] == -1) {
                    res[i] = currentTime;
                    break;
                } else {
                    stack.pop();
                }
            }
            stack.push(i);
        }
        return res;
    }
}
