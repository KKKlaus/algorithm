package Lyft电面;

import java.util.ArrayList;
import java.util.List;

public class ReadNCharactersGivenRead4 {
    //Think that you have 4 chars "a, b, c, d" in the file, and you want to call your function twice like this:
    //
    //read(buf, 1); // should return 'a'
    //read(buf, 3); // should return 'b, c, d'
    //All the 4 chars will be consumed in the first call. So the tricky part of this question is how can you preserve the remaining 'b, c, d' to the second call.


    // 问题1：LC157

//    int bufpt = 0;
//    int bufct = 0;
//    char[] temp = new char[4];
//    public int read(char[] buf, int n) {
//        int cur = 0;
//        while (cur < n) {
//            if (bufpt == 0) {
//                bufct = read4(temp);
//            }
//            while (cur < n && bufpt < bufct) {
//                buf[cur++] = temp[bufpt++];
//            }
//            if (bufpt == bufct) bufpt = 0;
//            if (bufct < 4) break;
//        }
//        return cur;
//    }


    // 问题2 ： LC158
//    int bufpt = 0;
//    int bufct = 0;
//    char[] temp = new char[4];
//    public int read(char[] buf, int n) {
//        int cur = 0;
//        while (cur < n) {
//            if (bufpt == 0) {
//                bufct = read4(temp);
//            }
//            while (cur < n && bufpt < bufct) {
//                buf[cur++] = temp[bufpt++];
//            }
//            if (bufpt == bufct) bufpt = 0;
//            if (bufct < 4) break;
//        }
//        return cur;
//    }

    int size = 100;
    class Stream {
        public int[] read(int m) {
            return new int[]{1,2,3};
        }
    }

    public int[] readN(int n) {
        Stream stream = new Stream();
        if (size < n){
            n = size;
        }

        return stream.read(n);
    }

    class multiStream {
        List<Stream> streamList;

        multiStream() {
            streamList = new ArrayList<>();
        }

        // stream.read放多少取多少
        public int[] read(int n, Stream s) {
            return s.read(n);
        }

        // 如果像read4那道题一样 n 总是< size. stream.read()会多读
        int buf_pointer = 0;
        int buf_count = 0;
        int[] buffer = new int[size];
        public int[] read_v2(int n, Stream s) {
            int[] res = new int[n];
            int cur = 0;
            while (cur < n) {
                if (buf_pointer == 0) {
                    buffer = s.read(n);
                    buf_count = buffer.length;
                }

                while (cur < n && buf_pointer < buf_count) {
                    res[cur++] = buffer[buf_pointer++];
                }
                if (buf_pointer == buf_count) buf_pointer = 0;
                if (buf_count < size) break;
            }
            return res;
        }

        public void add(Stream s) {
            streamList.add(s);
        }

        public void delete(Stream s) {
            streamList.remove(s);
        }
    }



    // 第二类题：

//    API: public List<Integer> fetch_by_page() {}
//
//
//    What if the fetcher is stateful? Every time you call fetch, it will continue with the previous offset. If it reaches the max_results, no records will be returned anymore unless you recreate another fetcher instance.
    // page size is a fixed number, let's say 10.

//    int offset = 0;
//    List<Integer> buffer = new ArrayList<>();
//    public List<Integer> fetcher.fetch(nums_of_results)() {
//        int cur = 0;
//        List<Integer> res = new ArrayList<>();
//        while (cur < nums_of_results) {
//            if (offset = 0) {
//                buffer = fetch_by_page();
//            }
//            while (cur < nums_of_results && offset < buffer.size()) {
//                res.add(buffer.get(offset));
//                cur++;
//                offset++;
//            }
//            if (offset == buffer.size()) {
//                offset = 0;
//            }
//            if (buffer < 10) {
//                break;
//            }
//        }
//        return res;
//    }
//
//    IntervalFunction intervalFn =
//            IntervalFunction.ofExponentialRandomBackoff(INITIAL_INTERVAL, MULTIPLIER, RANDOMIZATION_FACTOR);

}
