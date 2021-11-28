package Pure电面;

import java.util.ArrayList;

public class DrawCircle {

    static int r = 20;

    public static ArrayList<int[]> draw1() {
        ArrayList<int[]> res = new ArrayList<int[]>();
        for (int i = -r; i < r; ++i) {
            for (int j = -r; j < r; ++j) {
                if (getDiff(0,0,i,j) < 1) {
                    res.add(new int[] {i,j});
                }
            }
        }
        return res;
    }

    private static int getDiff(int x1, int y1, int x2, int y2) {
        int square = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
        int diff = Math.abs(Math.sqrt(square) - r) > 1 ? 1: 0;
        return diff;
    }
}
