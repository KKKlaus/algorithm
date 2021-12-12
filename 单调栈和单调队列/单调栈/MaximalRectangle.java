import java.util.Stack;

public class MaximalRectangle {

    // 方法1： 一层层遍历，其实就是应用了LargestRectangleinHistogram这道题
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[] heights = new int[n];
        int maxArea = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') heights[j]++;
                else heights[j] = 0;
            }
            maxArea = Math.max(maxArea, largestRectangleArea(heights));
        }
        return maxArea;
    }


    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        Stack<Integer> stack = new Stack<>(); // monotonic increasing stack
        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                // insert i will be the first right greatest
                // pop it, stack peek will be the frist left greatest
                int curIndex = stack.pop();
                int leftIndex = stack.isEmpty() ? -1 : stack.peek();
                int rightIndex = i;
                int width = rightIndex - leftIndex - 1;
                maxArea = Math.max(maxArea, width * heights[curIndex]);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int curIndex = stack.pop();
            int leftIndex = stack.isEmpty() ? -1 : stack.peek();
            int rightIndex = n;
            int width = rightIndex - leftIndex - 1;
            maxArea = Math.max(maxArea, width * heights[curIndex]);
        }

        return maxArea;
    }
}
