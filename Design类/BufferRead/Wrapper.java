// 感觉可以理解为ReadNCharactersGivenReadM
/**
 *  Lyft电面题
 * The readM API is defined in the parent class Reader4.
 *     int readM(char[] bufM);
 */
public class Wrapper {
    // Given api每次返回M个结果。 stateful的，需要记忆上一次的结果。
    int M = 1000;
    int readM(char[] bufM) { return M;}; // return 大部分时间是M 最后一次小于M,跟read4一个道理


    // 返回N个的结果。
    int bufpt = 0;
    int bufct = 0;
    char[] temp = new char[M];
    public int read(char[] buf, int N) {
        int cur = 0;
        while (cur < N) {
            if (bufpt == 0) {
                bufct = readM(temp);
            }
            while (cur < N && bufpt < bufct) {
                buf[cur++] = temp[bufpt++];
            }
            if (bufpt == bufct) bufpt = 0;
            if (bufct < M) break;
        }
        return cur;
    }


    int readM_2() {return M; }
    //假设没有buf这种东西
    int pointer = 0;
    int count = 0;
    public int readV2(int N) {
        int cur = 0;
        while (cur < N) {
            if (pointer == 0) {
                count = readM_2();
            }
            while (cur < N && pointer < count) {
                cur++;
                pointer++;
            }
            if (pointer == count) pointer = 0;
            // if (count < M) break;
        }
        return cur;
    }


    // test case
    // M = 100 ,
    // N = 10 : count = 100, pointer = 10 = cur;
    //          return cur

    // N = 110 : cur = 90, pointer = 100 = count;
    //           pointer = 0;
    // next While : count = 100 -> cur = 110, pointer = 20;
}
