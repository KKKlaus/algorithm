public class SpiralMatrix2 {

    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int rowBegin = 0, rowEnd = n - 1, colBegin = 0, colEnd = n - 1;
        int count = 1;
        while (rowBegin <= rowEnd && colBegin <= colEnd) {
            for (int j = colBegin; j <= colEnd; j++) {
                matrix[rowBegin][j] = count++;
            }
            rowBegin++;

            for (int i = rowBegin; i <= rowEnd; i++) {
                matrix[i][colEnd] = count++;
            }
            colEnd--;

            if (rowBegin <= rowEnd) {
                for (int j = colEnd; j >= colBegin; j--) {
                    matrix[rowEnd][j] = count++;
                }
                rowEnd--;
            }

            if (colBegin <= colEnd) {
                for (int i = rowEnd; i >= rowBegin; i--) {
                    matrix[i][colBegin] = count++;
                }
                colBegin++;
            }
        }
        return matrix;
    }
}
