import java.util.*;

public class SlidingPuzzle2 {

    public static void main(String[] args) {
        SlidingPuzzle2 t = new SlidingPuzzle2();
        t.test();
    }

    public void test() {
        int[][] board = new int[][]{{4,1,2},{5,0,3}};
//        int[][] board = new int[][]{{1,2,3},{4,0,5}};
        System.out.println(slidingPuzzle(board));
        for (String s : path) {
            System.out.print(s + " -> ");
        }
        System.out.print( "DONE");
    }

    List<String> path = new ArrayList<>();
    // Snap电面题， followUp 如何输出path
    public int slidingPuzzle(int[][] board) {
        String target = "123450";
        String start = "";
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                start += board[i][j];
            }
        }
        HashMap<String, String> childToParentMap = new HashMap<>();

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
                if (cur.equals(target)) {
                    String s = target;
                    while (childToParentMap.containsKey(s)) {
                        path.add(0, s);
                        s = childToParentMap.get(s);
                    }
                    path.add(0, start);
                    return step;
                }
                int index = cur.indexOf("0");
                for (int dir : dirs[index]) {
                    String next = helper(cur, index, dir);
                    if (visited.contains(next)) continue;
                    childToParentMap.put(next, cur);
                    queue.offer(next);
                    visited.add(next);
                }
            }
            step++;
        }


        return -1;
    }

    private String helper(String str, int x, int y) {
        // 我面试中用的是char[] arr = str.toCharArray() 然后数组swap
        StringBuilder sb = new StringBuilder(str);
        sb.setCharAt(x, str.charAt(y));
        sb.setCharAt(y, str.charAt(x));
        return sb.toString();
    }
}
