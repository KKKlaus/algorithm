package Lyft电面;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class intervalGame {

    //外星人袭击题：有一道0-10的一维线，然后implement一个class： attack和game is over两个func
    //attack就是给一个点，（x，x+1）区间内的线都被打掉。
    //game is over就是是否还有没被打掉的区间。

//    // 3-6        1   /   2   / 4   / 6    / 8
//    TreeMap<Integer, Integer> map = new TreeMap<>();
//    public void attack(int x) {
//        if (x < 0 || x >= 10) return;
//        Integer floorKey = map.floorKey(x);
//        if (floorKey != null && map.get(floorKey) > x + 1) {    // 4
//            return;
//        }
//        if (floorKey == null && map.containsKey(x + 1)) {       // 2
//            int ceiling = map.get(x + 1);
//            map.put(x, ceiling);
//            map.subMap(x, false, ceiling, true).clear();
//            return;
//        }
//        if (floorKey == null && !map.containsKey(x + 1)) {      // 1
//            map.put(x, x + 1);
//            return;
//        }
//        if (floorKey != null && map.get(floorKey) == x) {       // 6
//            map.put(floorKey, x + 1);
//            return;
//        }
//        if (floorKey != null && map.get(floorKey) < x) {
//            map.put(x, x + 1);
//            return;
//        }
//    }

    List<int[]> intervals = new ArrayList<>();
    public void attack(int x) {
        List<int[]> res = new ArrayList<>();
        int[] newInterval = new int[]{x, x + 1};
        int i = 0;
        // new interval : <x, x + 1>
        while (i < intervals.size() && intervals.get(i)[1] < newInterval[0]) {
            res.add(intervals.get(i));
            i++;
        }

        // [1, 3] [6, 9]  ->  [2, 5]
        while (i < intervals.size() && intervals.get(i)[0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals.get(i)[0]);
            newInterval[1] = Math.max(newInterval[1], intervals.get(i)[1]);
            i++;
        }
        res.add(newInterval);

        while (i < intervals.size()) {
            res.add(intervals.get(i));
            i++;
        }

        intervals = res;
    }


    public boolean GameOver() {
        return intervals.size() == 1 && intervals.get(0)[0] == 0 && intervals.get(0)[1] == 10;
    }


    public static void main(String[] args) {
        intervalGame t = new intervalGame();
        t.test();
    }

    private void test() {
        attack(3);
        attack(5);
        attack(6);
        attack(4);
        attack(0);
        attack(1);
        System.out.println(GameOver());
        attack(2);
        attack(9);
        attack(7);
        attack(8);
        System.out.println(GameOver());
    }

}
