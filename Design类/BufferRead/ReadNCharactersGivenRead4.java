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
}
