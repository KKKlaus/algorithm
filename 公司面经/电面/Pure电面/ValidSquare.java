package Pure电面;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class ValidSquare {

    // 给4个点，判断是否能成正方形
    // 对应N个点，多少个正方形可以利用这个方法for循环4次：o(n^4)
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        HashSet<Integer> set = new HashSet<>(Arrays.asList(dis(p1,p2), dis(p1,p3), dis(p1,p4), dis(p2,p3), dis(p2,p4), dis(p3,p4)));
        return !set.contains(0) && set.size() == 2;
    }

    private int dis(int[] p1, int[] p2) {
        return (p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1]);
    }

    public static void main(String[] args) {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(0, 1);
        Point p3 = new Point(1, 0);
        Point p4 = new Point(1, 1);
        Point p5 = new Point(2, 0);
        Point p6 = new Point(2, 1);
        Point[] arr = new Point[]{p1, p2, p3, p4, p5, p6};
        System.out.println(numOfSquare(arr));
    }
//    class Point {
//        int x, y;
//        Point(int x, int y) {
//            this.x = x;
//            this.y = y;
//        }
//
//        @Override
//        public boolean equals(Object obj) {
//            if (!(obj instanceof Point)) return false;
//            Point point = (Point) obj;
//            return point.x == this.x && point.y == this.y;
//        }
//    }


    // 给n个点，判断有多少个正方形： 每两个点确认一条边，可以确认两个正方形 ： o(n^2)
    public static int numOfSquare(Point[] input) {
        HashSet<Point> set = new HashSet<>(Arrays.asList(input));
        ArrayList<Point> list = new ArrayList<>(set);
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                Point point1 = list.get(i);
                Point point2 = list.get(j);
                int x1 = point1.x, y1 = point1.y;
                int x2 = point2.x, y2 = point2.y;

                int gapX = Math.abs(x1 - x2);
                int gapY = Math.abs(y1 - y2);

                int x3 = x1 + gapY, y3 = y1 + gapX;
                int x4 = x2 + gapY, y4 = y2 + gapX;

                int x5 = x1 - gapY, y5 = y1 - gapX;
                int x6 = x2 - gapY, y6 = y2 - gapX;

                Point point3 = new Point(x3, y3);
                Point point4 = new Point(x4, y4);
                Point point5 = new Point(x5, y5);
                Point point6 = new Point(x6, y6);

                if (set.contains(point3) && set.contains(point4)) count++;
                if (set.contains(point5) && set.contains(point6)) count++;
            }
        }

        return count / 4; // 因为每条边会被重复计算4次，作为任意相邻两点组成的正方形的一条边
    }


    // 两点作为对角线去找 /2 会有double问题。有些麻烦，可能适用于长方形
//    public static int numOfSquare(Point[] input) {
//        HashSet<Point> set = new HashSet<>(Arrays.asList(input));
//        ArrayList<Point> list = new ArrayList<>(set);
//        int count = 0;
//        for (int i = 0; i < list.size(); i++) {
//            for (int j = i + 1; j < list.size(); j++) {
//                Point point1 = list.get(i);
//                Point point2 = list.get(j);
//                int x1 = point1.x, y1 = point1.y;
//                int x2 = point2.x, y2 = point2.y;
//
//                double midX = ((double) x1 + (double) x2) / 2;
//                double midY = ((double) y1 + (double) y2) / 2;
//
//                double gapX = Math.abs(x1 - midX);   // x1 - midX = x2 - midX都一样
//                double gapY = Math.abs(y1 - midY);
//
//                int x3 = (int) (midX + gapY), y3 = (int) (midY + gapX);
//                int x4 = (int) (midX - gapY), y4 = (int) (midY - gapX);
//
//                Point point3 = new Point(x3, y3);
//                Point point4 = new Point(x4, y4);
//                if (set.contains(point3) && set.contains(point4)) count++;
//            }
//        }
//
//        return count / 2;
//    }
}
