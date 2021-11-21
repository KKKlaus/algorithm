import java.util.Arrays;

// 这道题了解即可
public class SetIntersectionSizeAtLeastTwo {

    public static void main(String[] args) {
        int[][] intervals = new int[][]{{1,3}, {1,4}, {2,5},{3,5}};
        System.out.println(intersectionSizeTwo(intervals));
    }

    public static int intersectionSizeTwo(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[1] != b[1] ? a[1]-b[1] : b[0]-a[0]); // java8 functional way of sort
        int sndMax = -2, max = -1, cnt = 0; // pay attention to intial values of sndMax and max
        for (int[] interval: intervals) {
            int start = interval[0], end = interval[1]; // check the validity before processing
            if (start <= sndMax ) { // two point overlapped
                continue;
            } else if (start <= max) { // one point overlapped
                cnt += 1;
                sndMax = max;
                max = end;
            } else { // no point overlapped
                cnt += 2;
                sndMax = end - 1;
                max = end;
            }
        }
        return cnt;
    }
}
