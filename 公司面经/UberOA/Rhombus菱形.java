public class Rhombus菱形 {

    public static void main(String[] args) {

    }

    public static void solution(int[][] matrix, int r) {

    }

    private static int rhombus(int[][] matrix, int x, int y, int r) {
        int res = 0;
        for (int i = x - r; i <= x + r; i++) {
            int dist = Math.abs(i - x);
            int antiDist = r - dist;
            for (int j = y - antiDist; j <= y + antiDist; j++) {
                res += matrix[i][j];
            }
        }
        return res;
    }
}
