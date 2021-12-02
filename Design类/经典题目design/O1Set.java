// Pure Storage好题
public class O1Set {

    //  assume we save element 1 - (N-1)
    class MySet {
        // bucket : 0  3  5  1  2

        // map    : 0  3  4  1  0  2
        // index    0  1  2  3  4  5

        int[] map;
        int[] bucket;
        int counter;
        int N = 10;
        public MySet() {
            this.map = new int[N];
            this.bucket = new int[N];
            this.counter = 0;
        }

        public boolean add(int n) {
            if (this.contains(n)) return false;
            counter++;
            bucket[counter] = n;
            map[n] = counter;
            return true;
        }

        // e.g. remove(5)
        // bucket : 0  3  2  1  0

        // map    : 0  3  2  1  0  0
        // index    0  1  2  3  4  5
        public boolean remove(int n) {
            if (!contains(n)) return false;
            int index = map[n];
            if (index != counter) {
                int temp = bucket[counter];
                bucket[counter] = n;
                bucket[index] = temp;
                map[temp] = index;
            }
            map[n] = 0;
            bucket[counter] = 0;
            counter--;
            return true;
        }

        public void clear() {
            this.counter = 0;
        }

        public void iterate() {
            for (int i = 1; i <= counter; i++) {
                System.out.print(bucket[i] + " ");
            }
        }

        public boolean contains(int n) {
            return map[n] != 0 && map[n] <= counter;
        }

    }

    public void test() {
        MySet set = new MySet();
        System.out.println(set.add(3));
        System.out.println(set.add(5));
        System.out.println(set.add(2));
        System.out.println(set.contains(5));
        System.out.println(set.contains(4));
        System.out.println(set.remove(2));
        set.iterate();

        System.out.println("\n" +"--------Clear--------");
        set.clear();
        System.out.println(set.contains(2));
        System.out.print("iterate: ");
        set.iterate();
        System.out.println(set.add(2));
        System.out.println(set.add(5));
        System.out.println(set.add(8));
        set.iterate();
        set.remove(5);
        set.iterate();
    }

    public static void main(String[] args) {
        O1Set t = new O1Set();
        t.test();
    }
}
