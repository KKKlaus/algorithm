package LinkedIn;

import java.sql.Time;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class DelayQueueDesign {

    // 方法3： DelayQueue
    class DelayQueueImp {

        public void DelayTaskSchedule() {
            DelayQueue<Task> queue = new DelayQueue<>();
            for (int i = 0; i < 5; i++) {
                new Thread().start();
                new Thread().start();
            }
        }

        class Producer implements Runnable {

            private Random rand;
            private DelayQueue<Task> q;

            public Producer(DelayQueue<Task> q) {
                this.q = q;
                rand = new Random();
            }

            @Override
            public void run() {
                while (true) {
                    try {
                        int delay = rand.nextInt(10000);
                        Task task = new Task(UUID.randomUUID().toString(), delay);
                        q.offer(task);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        class Consumer implements Runnable {
            private PriorityBlockingQueue<Task> q;
            public Consumer(PriorityBlockingQueue<Task> q) {
                this.q = q;
            }

            @Override
            public void run() {
                while (true) {
                    try {
                        Task task = q.take();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }





    // 基本方法1和2
    class PQAndPollingORTimeGap {

        public void DelayTaskSchedule() {
            Comparator<Task> sort = Comparator.comparing(Task::getStartTime);
            PriorityBlockingQueue<Task> queue = new PriorityBlockingQueue<Task>(1000, sort);
            for (int i = 0; i < 5; i++) {
                new Thread(new Producer(queue)).start();
                new Thread(new Consumer(queue)).start();
            }
        }


        class Producer implements Runnable {

            private Random rand;
            private PriorityBlockingQueue<Task> q;

            public Producer(PriorityBlockingQueue<Task>q) {
                this.q = q;
                rand = new Random();
            }

            @Override
            public void run() {
                while (true) {
                    try {
                        // 方法1： pq + polling
//                        Task task = new Task(UUID.randomUUID().toString());
//                        q.offer(task);
//                        Thread.sleep(1000);
                        // 方法2： 基于1 pq + 时间差
                        int delay = rand.nextInt(10000);
                        Task task = new Task(UUID.randomUUID().toString(), delay);
                        q.offer(task);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        class Consumer implements Runnable {
            private PriorityBlockingQueue<Task> q;
            public Consumer(PriorityBlockingQueue<Task> q) {
                this.q = q;
            }

            @Override
            public void run() {
                while (true) {
                    try {
                        // 方法1： pq + polling
//                        Thread.sleep(5);
//                        Task task = q.poll();
//                        task.execute();
                        // 方法2： 基于1 pq + 时间差
                        Task task = q.peek();
                        long delay = task.getDelay(TimeUnit.MILLISECONDS);
                        if (delay < 0) {
                            q.poll();
                            task.execute();
                        } else {
                            Thread.sleep(delay);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    class Task implements Delayed {

        private String name;
        private long startTime;

        public Task(String name, long delay) {
            this.name = name;
            this.startTime = System.currentTimeMillis() + delay;
        }
        public Task(String name) {
            this.name = name;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            long diff = startTime - System.currentTimeMillis();
            return unit.convert(diff, TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            return (int)(this.startTime - ((Task) o).startTime);
        }

        public void execute() {
            // do something
        }

        private long getStartTime() {
            return this.startTime;
        }
    }
}
