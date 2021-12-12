import java.util.Stack;

public class LargestRectangleinHistogram {

    // 遍历heights高，向左向右找比它小的来构成宽(向左向右两个while循环)->最坏情况O(n^2)，而这一步可以用单调栈来实现O(n)
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
