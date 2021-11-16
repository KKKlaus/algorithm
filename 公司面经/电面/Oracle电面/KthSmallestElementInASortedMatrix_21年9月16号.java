import java.util.PriorityQueue;

public class KthSmallestElementInASortedMatrix_21年9月16号 {

    // BS Time: O((M+N) * logD), where M <= 300 is the number of rows, N <= 300 is the number of columns, D <= 2*10^9 is the difference between the maximum element and the minimum element in the matrix.
    // countLessOrEqualsK()这个方程循环非常巧妙
    public int kthSmallest(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        int start = matrix[0][0], end = matrix[m - 1][n - 1];
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (countLessOrEqualsK(matrix, k, mid) >= k) {

                end = mid;
            } else {
                start = mid;
            }
        }

        return countLessOrEqualsK(matrix, k, start) >= k ? start : end;
    }

    private int countLessOrEqualsK(int[][] matrix, int k, int mid) {
        int count = 0, col = matrix[0].length - 1;
        for (int row = 0; row < matrix.length; row++) {
            while (col >= 0 && matrix[row][col] > mid) {
                col--;
            }
            count += col + 1;
        }
        return count;
    }


    // pq 跟merge k sorted list一个做法，但没有利用到column sorted的性质。时间复杂度: KlogK
    public int kthSmallest_pq(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        for (int i = 0; i < m && i < k; i++) {
            pq.offer(new int[]{i, 0, matrix[i][0]});
        }

        for (int i = 1; i < k; i++) {
            int[] cur = pq.poll();
            int x = cur[0], y = cur[1], val = cur[2];
            if (y + 1 < n) pq.offer(new int[]{x, y + 1, matrix[x][y + 1]});
        }
        return pq.peek()[2];
    }
}
