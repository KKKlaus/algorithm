import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PrefixSumMatrix {

    public static void main(String[] args) {
        int a[][] = {{1,2,3},{3,2,1},{0,0,0}};
//        int[][] res = prefixSumMatrix(a);
//        for (int i = 0; i < res.length; i++) {
//            for (int j = 0; j < res[0].length; j++) {
//                System.out.print(res[i][j] + " ");
//            }
//            System.out.println();
//        }
        int res = getSum(a, 2);
        System.out.println(res);
    }

    public static int[][] prefixSumMatrix(int[][] arr) {
        if (arr == null || arr.length == 0) return arr;
        int m = arr.length, n = arr[0].length;

        int[][] matrix = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = arr[i][j];
            }
        }


        for (int i = 0; i < m; i++) {
            for (int j = 1; j < n; j++) {
                matrix[i][j] = matrix[i][j - 1] + matrix[i][j];
            }
        }

        for (int j = 0; j < n; j++) {
            for (int i = 1; i < m; i++) {
                matrix[i][j] = matrix[i - 1][j] + matrix[i][j];
            }
        }

        return matrix;
    }


    // 给一个 matrix 和一个 int k, 找到所有总和最大的 k * k ，返回这些 submatrix的元素和（重复元素不重复计算）
    //例子：
    //[1, 2, 3]      k = 2
    //[3, 2, 1]
    //[0, 0, 0]
    //有两个submatrix的和都是8， 这两个submatrix的元素构成集合 {1, 2, 3}, 1+2+3 = 6， return 6
    //## 思路：利用prefixSum来避免重复计算，参考：https://www.geeksforgeeks.org/prefix-sum-2d-array/
    public static int getSum(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;

        Set<Integer> set = new HashSet<>();
        List<Integer> maxIndexList = new ArrayList<>();

        int[][] prefixSum = prefixSumMatrix(matrix);
        int max = Integer.MIN_VALUE;

        for (int i = k - 1; i < m; i++) {
            for (int j = k - 1; j < n; j++) {
                int top = i - k < 0 ? 0 : prefixSum[i - k][j];
                int left = j - k < 0 ? 0 : prefixSum[i][j - k];
                int leftTop = i - k >= 0 && j - k >= 0 ? prefixSum[i - k][j - k] : 0;
                if (prefixSum[i][j] - left - top + leftTop > max) {
                    max = prefixSum[i][j] - left - top + leftTop;
                    maxIndexList.clear();
                    maxIndexList.add(i * n + j);
                }
                else if (prefixSum[i][j] - left - top + leftTop == max) {
                    maxIndexList.add(i * n + j);
                }
            }
        }

        for (int index : maxIndexList) {
            int x = index / n, y = index % n;
            for (int i = x; i > x - k; i--) {
                for (int j = y; j > y - k; j--) {
                    set.add(matrix[i][j]);
                }
            }
        }

        int sum = 0;
        for (int t : set) {
            sum += t;
        }

        return sum;
    }
}
