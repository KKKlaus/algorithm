package Pure电面;

import java.util.ArrayList;

public class DrawCircle {

    static int r = 20;

    public static ArrayList<int[]> draw1() {
        ArrayList<int[]> res = new ArrayList<int[]>();
        for (int i = -r; i <= r; ++i) {
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

    //二分
    public static ArrayList<int[]> draw2() {
        ArrayList<int[]> res = new ArrayList<int[]>();
        for (int i = -r; i <= r; ++i) {
            int j = calculateJ(i);
            res.add(new int[] {i,j});
            res.add(new int[] {i, -j});
        }
        return res;
    }

    public static int calculateJ(int x) {
        double temp = Math.sqrt(r * r - x * x);
        int res = temp - (int) temp > 0.5 ? (int) temp + 1 : (int) temp;
        return res;
    }

    // 四分
    public static ArrayList<int[]> draw3() {
        ArrayList<int[]> res = new ArrayList<int[]>();
        for (int i = 0; i <= r; ++i) {
            int j = calculateJ(i);
            res.add(new int[] {i,j});
            res.add(new int[] {i, -j});
            res.add(new int[] {-i, -j});
            res.add(new int[] {-i, j});
        }
        return res;
    }

    // 八分
    public static ArrayList<int[]>draw4() {
        ArrayList<int[]> res = new ArrayList<int[]>();
        double limit = r/Math.sqrt(2);
        for (int x = 0; x < limit; ++x) {
            int y = calculateJ(x);
            res.add(new int[] {x,y});
            res.add(new int[] {x,-y});
            res.add(new int[] {-x,y});
            res.add(new int[] {-x,-y});
            res.add(new int[] {-y,x});
            res.add(new int[] {y,-x});
            res.add(new int[] {-y,-x});
            res.add(new int[] {y,x});
        }
        return res;
    }


    public static ArrayList<int[]>draw_midPoint() {
        ArrayList<int[]> res = new ArrayList<int[]>();
        int x = 0, y = r;
        while (x < y) {
            res.add(new int[] {x,y});
            res.add(new int[] {x,-y});
            res.add(new int[] {-x,y});
            res.add(new int[] {-x,-y});
            res.add(new int[] {-y,x});
            res.add(new int[] {y,-x});
            res.add(new int[] {-y,-x});
            res.add(new int[] {y,x});
            double distance = getDist(x + 1, y - 0.5, 0, 0);
            if (distance - r * r > 0) {
                y--;
            }
            ++x;
        }
        return res;
    }

    private static double getDist(double x1, double y1, int x2, int y2) {
        return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
    }
}
