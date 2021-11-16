import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class RangeMatrixByBeauty {

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1,2,2,3},{4,6,4,5},{7,8,1,3},{9,10,4,5}};
        range(matrix, 2);
    }

    // 一个nxn的matrix，里面都是正整数，保证可以被一个size分割成若干sub matrix，然后在这些sub里面找beauty，也就是最小的missing正整数，
    // 比如2x2 sub里面有 {1,2,4,6}，beauty就是3。要求把这些sub按照beauty大小排序然后放回nxn的matrix里面，如果beauty一样就按照原先的相对顺序放。
    //这题比较烦，我最后做的，暴力解，map存beauty和sub左上角坐标，没有重新放回matrix,新建了一个做输出
    public static int[][] range(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0) return matrix;
        int n = matrix.length; // n*n matrix
        TreeMap<Integer, List<int[]>> map = new TreeMap<>(); // beauty - leftTop(x,y)
        for (int i = 0; i < n; i = i + k) {
            for (int j = 0; j < n; j = j + k) {
                int beauty = findBeauty(matrix, i, j, k);
                if (!map.containsKey(beauty)) {
                    map.put(beauty, new ArrayList<>());
                }
                map.get(beauty).add(new int[]{i, j});
            }
        }

        // re-range
        int[][] res = new int[n][n];
        int x = 0, y = 0;
        for (int beauty : map.keySet()) {
            for (int[] point : map.get(beauty)) {
                reRange(matrix, point[0], point[1], res, x, y, k);
                y += k;
                if (y == n) {
                    y = 0;
                    x += k;
                }
            }
        }
        return res;
    }

    private static void reRange(int[][] matrix, int x1, int y1, int[][] res, int x2, int y2, int k) {
        for (int i1 = x1, i2 = x2; i1 < x1 + k && i2 < x2 + k; i1++, i2++) {
            for (int j1 = y1, j2 = y2; j1 < y1 + k && j2 < y2 + k; j1++, j2++) {
                res[i2][j2] = matrix[i1][j1];
            }
        }
    }

    private static int findBeauty(int[][] matrix, int x, int y, int k) {
        int n = matrix.length;
        int[] arr = new int[n*n + 1];
        for (int i = x; i < x + k; i++) {
            for (int j = y; j < y + k; j++) {
                int value = matrix[i][j];
                if (value > n*n) continue;  // 即使超过了不处理即可，因为若有超过，必有<n*n的missing数字会找到，所以不需要开无限大的数组
                arr[value]++;
            }
        }
        int beauty = 1;
        while (true) {
            if (arr[beauty] == 0) return beauty;
            beauty++;
        }
    }
}
