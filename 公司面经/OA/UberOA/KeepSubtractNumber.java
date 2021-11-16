public class KeepSubtractNumber {

    public static void main(String[] args) {

    }

    // 第二题给一个array，{3,3,5,2,3}, 每次找开头第一个不是0的数字，然后从它开始往后每个数都减少它的值，直到遇到比它小的，例子中就是从头开始每个减3，直到2 break。然后在结果上加上3。重复操作直到无法操作，求输出值，这个例子就是3 + 2 + 1 = 6
    public static int subtract(int[] arr) {
        int res = 0, value = 0;
        for (int i = 0; i < arr.length; i++) {
            arr[i] -= value;
            if (arr[i] != 0) {
                value = arr[i];
                res += value;
                i--;
            }
        }
        return res;
    }
}
