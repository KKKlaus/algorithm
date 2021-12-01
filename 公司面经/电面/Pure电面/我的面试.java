package Pure电面;

public class 我的面试 {

//    class PureLib {
//        // numIds: amount of unique ids we want to generate
//        // returns: An array of unique ids
//        // available to use
//        // located at a centralized server,
//        // guarantees global uniqueness
//        // 1 sec / call on average
//        public int[] getIds(int numIds) {
//
//        }
//    }
//
//    class PureAPI {
//        // returns: a unique id
//
//        int count = 0;
//        Queue<Integer> queue = new LinkedList<>();
//        public void enQueue() {
//            PureLib lib = new PureLib();
//            int[] idList = lib.getIds(1000);
//            for (int i = 0; i < 1000; i++) {
//                queue.offer(idList[i]);
//            }
//        }
//
//
//        // Date timestamp;
//
//        // 1000 calls / sec on average
//        public int getOneId() {
//            // TODO
//            // if (timestamp == null || Date.now() - timestamp >= 1s) {
//            //     preCall();
//            //     timestamp = Date.now();
//            // }
//            if (queue.size() == 0) {
//                enQueue();
//            }
//            return queue.poll();
//        }
//
//        // 0s : queue : 1000 ids = 1,2,3,4 ... 1000  -> id = 1
//        // 0.001s :  id = 2
//        // ....
//        // 1s : queue : 1000ids =  1001 ... 2000 -> id = 1001
//
//        // Consumer - producer
//        // 2 queues
//    }













    /*#include <cstdint>

disk drive has number of sectors
possible sector ids: Data, Free, Garbage

0         5
F G D F G D
D D . . . .

// returns 'D' or 'F' or 'G' on index
char id(uint64_t index);
void swap(uint64_t index1, uint64_t index2);

uint64_t num_sectors = 2^50;

void defrag() {
}
*/


/*
F :
G :
D : 0

D D F F G G

(2)

p

*/
//
//import java.io.*;
//import java.util.*;
//
//import java.text.*;
//import java.math.*;
//import java.util.regex.*;
//
//    public class Solution {
//
//        public static void main(String[] args) {
//        }
//
//        public static char id(int idx) {
//            return 'D';
//        }
//
//        public static char swap(int idx0, int idx1) {
//        }

/*

F G D F G D
^         ^
  *

F D D F G G
^       ^
  *



D F D F G G
  ^     ^
    *

D D F F G G
    ^   ^
      *

D D F F G G
    ^   ^
        *


F G D
^   ^
*

F G D
^   ^
  *


F D G
^ ^
  *

D F G
^ ^
  *


F G D D
F D D G
D F D G
D D F G

F G D D
D G F D
D D F G



F G D D
^
      *


F D D G
^
      *

D D F F F G
  ^
      *

G F D D
^
      *


D F D G
  ^
    *

D D F G
    ^
  *




d=20
g=18
F F F F F F F F F F G D F F F F F F F F F F G G G G G D F G G G G G G G G D D D D D F G D D D D D D D D


D D G F

F G D D
^
      *




*/

//        public static void defrag2(int num_sectors, int nd, int ng) {
//
//        }
//
//        public static void defrag(int num_sectors) {
//            int pd = 0, pg = num_sectors - 1;
//            int cur = 0;
//            while (cur <= pg) {
//                if (id(cur) == 'D') {
//                    swap(cur, pd);
//                    cur++;
//                    pd++;
//                } else if (id(cur) == 'G') {
//                    swap(cur, pg);
//                    pg--;
//                } else {
//                    cur++;
//                }
//            }
//        }
//    }
}
