package Lyft电面;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class iterator {

    class IntersectionIterator implements Iterator {
        private Integer nextVal = null;
        Iterator it1;
        Iterator it2;

        public IntersectionIterator(Iterator it1, Iterator it2) {
            this.it1 = it1;
            this.it2 = it2;
            adjust();
        }

        @Override
        public boolean hasNext() {
            if(nextVal == null) return false;
            return true;
        }

        @Override
        public Integer next() {
            Integer toRet = nextVal;
            adjust();
            return toRet;
        }

        private void adjust() {
            nextVal = null;
            Integer val1 = it1.hasNext() ? (Integer) it1.next() : null;
            Integer val2 = it2.hasNext() ? (Integer) it2.next() : null;
            while(true) {
                if(val1 == null || val2 == null) break;
                if(val1 == val2) {
                    nextVal = val1;
                    break;
                }else if(val1 < val2) {
                    if(it1.hasNext()) {
                        val1 = (Integer) it1.next();
                    }else break;
                }else{
                    if(it2.hasNext()) {
                        val2 = (Integer) it2.next();
                    }else break;
                }
            }
        }
    }

//    public int iter1_next() { return 1; }
//
//    public int iter2_next() { return 1; }
//
//    public boolean iter1_hasNext() { return true; }
//
//    public boolean iter2_hasNext() { return true; }
//
//
//
//    // p1 :  1
//    // p2 :  1
//    public int next() {
//        int p1, p2;
//
//        if (iter1_hasNext()) p1 = iter1_next();
//        else throw new RuntimeException("Next doesn't exist");
//        if (iter2_hasNext()) p2 = iter2_next();
//        else throw new RuntimeException("Next doesn't exist");
//
//        while (p1 != p2) {
//            if (!iter1_hasNext() || !iter2_hasNext()) return -1;
//            if (p1 > p2) {
//                p2 = iter2_next();
//            } else {
//                p1 = iter1_next();
//            }
//        }
//        return p1;
//    }
//
//    Queue<Integer> queue = new LinkedList<>();
//    public boolean hasNext() {
//        // 构造器的时候存到queue里
//        return queue.isEmpty();
//    }
}
