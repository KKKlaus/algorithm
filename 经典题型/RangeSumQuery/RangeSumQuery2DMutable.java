public class RangeSumQuery2DMutable {


    // 这道题有3问，分别参考LC303,304, 308
    class NumMatrix {

        int[][] prefixSum;
        int[][] matrix;
        public NumMatrix(int[][] matrix) {
            this.matrix = matrix;
            int m = matrix.length, n = matrix[0].length;
            prefixSum = new int[m][n + 1];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    prefixSum[i][j + 1] = prefixSum[i][j] + matrix[i][j];
                }
            }
        }

        public void update(int row, int col, int val) {
            int diff = val - matrix[row][col];
            for (int j = col; j < matrix[row].length; j++) {
                prefixSum[row][j + 1] += diff;
            }
            matrix[row][col] = val;
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            int sum = 0;
            for (int i = row1; i <= row2; i++) {
                sum += prefixSum[i][col2 + 1] - prefixSum[i][col1];
            }
            return sum;
        }
    }
}
