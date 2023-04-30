import java.lang.reflect.Array;
import java.util.*;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {
        Main test = new Main();
        int[] start = new int[]{1,1};
        int[] target = new int[]{4,5};
        int[][] specialRoads = new int[][]{{1,2,3,3,2},{3,4,4,5,1}};
        System.out.println(test.minimumCost(start, target, specialRoads));
    }

    public int minimumCost(int[] start, int[] target, int[][] specialRoads) {
        int minCost = getRegularCost(start, target);
        for (int[] specialRoad : specialRoads) {
            int[] point1 = new int[]{specialRoad[0], specialRoad[1]};
            int[] point2 = new int[]{specialRoad[2], specialRoad[3]};
            int curCost = specialRoad[4];
            if (minCost <= curCost) continue;
            if (!isValid(start, target, point1, point2)) continue;
            if ((isPointTheSame(start, point1) && isPointTheSame(target, point2)) || (isPointTheSame(start, point2) && isPointTheSame(target, point1)) ) {
                return curCost;
            }
            if (getRegularCost(point1, point2) < curCost) continue; // may not need this?
            int cost1 = curCost + minimumCost(start, point1, specialRoads) + minimumCost(point2, target, specialRoads);
            int cost2 = curCost + minimumCost(start, point2, specialRoads) + minimumCost(point1, target, specialRoads);
            minCost = Math.min(minCost, Math.min(cost1, cost2));
        }
        return minCost;
    }

    private int getRegularCost(int[] p1, int[] p2) {
        return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
    }

    private boolean isPointTheSame(int[] p1, int[] p2) {
        return p1[0] == p2[0] && p1[1] == p2[1];
    }

    private boolean isValid(int[] start, int[] target, int[] p1, int[] p2) {
        if (!(start[0] <= p1[0] && p1[0] <= target[0])) return false;
        if (!(start[0] <= p2[0] && p2[0] <= target[0])) return false;
        if (!(start[1] <= p1[1] && p1[1] <= target[1])) return false;
        if (!(start[1] <= p2[1] && p2[1] <= target[1])) return false;
        return true;
    }
}