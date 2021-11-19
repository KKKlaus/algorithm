import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class SlidingPuzzle {

    // Analysis:
    //There are at most 6! permutation of the 6 numbers: 0~5. For each permustion, cost spaceO(6);
    // String.indexOf() and String.equals() cost time O(6). Therefore, space and time both cost 6 * 6! = 4320.
    //
    //Time & space: O(4320).
    public int slidingPuzzle(int[][] board) {
        String target = "123450";
        String start = "";
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                start += board[i][j];
            }
        }

        int[][] dirs = new int[][]{{1, 3}, {0, 2, 4}, {1, 5}, {0, 4}, {1, 3, 5}, {2, 4}};
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        visited.add(start);
        int step = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                if (cur.equals(target)) return step;
                int index = cur.indexOf("0");
                for (int dir : dirs[index]) {
                    String next = helper(cur, index, dir);
                    if (visited.contains(next)) continue;
                    queue.offer(next);
                    visited.add(next);
                }
            }
            step++;
        }
        return -1;
    }

    private String helper(String str, int x, int y) {
        StringBuilder sb = new StringBuilder(str);
        sb.setCharAt(x, str.charAt(y));
        sb.setCharAt(y, str.charAt(x));
        return sb.toString();
    }
}
