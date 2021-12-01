package Pure电面;

import java.util.Queue;
import java.util.concurrent.locks.Lock;


// 1. 多线程不加锁 -> isFired 放后面 会导致register有一个(或多个)永远留在队列里面
//                     放前面 会导致inorder不按照顺序call
// 2. 多线程加锁 -> isFired 放前面 仍然会导致不按照顺序call
//                     放后面能有效解决， 或者两个Queue，在交换的时候lock
// call()可能有lock所有后面会在call前面unlock

public class EventFire {

        class Callback {
            public String name;
            public Callback(String s) {
                this.name = s;
            }

            public void call() {
                System.out.println("Calling");
            }
        }

        class Event {
            private Queue<Callback> cbQueue;
            boolean fired;
            Lock lock;
            public Event() {
                fired = false;
            }

            // Register Callback
            public void register_cb(Callback cb) {
                lock.lock();
                if (!fired) {
                    cbQueue.offer(cb);
                    lock.unlock();
                } else {
                    if (cbQueue.isEmpty()) {
                        lock.unlock();
                        cb.call();
                    }
                    else {

                    }
//                    lock.unlock();
//                    cb.call();
                }
            }
//
            //  d
            public void fire() {
                lock.lock();
                fired = true;
                while (!cbQueue.isEmpty()) {
                    Callback cb = cbQueue.poll();
                    lock.unlock();
                    cb.call();
                    lock.lock();
                }

                lock.unlock();
            }
        }


    // 版本2：lock with no deadlock
    class Event2 {
        private Queue<Callback> cbQueue;
        boolean fired;

    }
}
