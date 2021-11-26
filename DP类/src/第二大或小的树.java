public class 第二大或小的树 {

    // 基于paintHouse的找第二大或小的数，不过painthouse是找index

    public int secondMax(int[] nums) {
        int max = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;

        for (int n : nums) {
            if (n >= max) {
                second = max;
                max = n;
            } else if (n >= second) {
                second = n;
            }
        }

        return second;
    }

    public int secondMin(int[] nums) {
        int min = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;

        for (int n : nums) {
            if (n <= min) {
                second = min;
                min = n;
            } else if (n <= second) {
                second = n;
            }
        }
        return second;
    }
}
