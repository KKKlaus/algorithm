package Amazon;

public class Bitpalindrome {

    public static void main(String[] args) {
        System.out.println(isPalindrome(21));
    }

    public static boolean isPalindrome(int num) {
        int maxDigit = 0;
        for (int i = 31; i >= 0; i--) {
            if (((num >> i) & 1) == 1) {
                maxDigit = i;
                break;
            }
        }
        int start = 0, end = maxDigit;
        while (start < end) {
            int startBit = (num >> start) & 1;
            int endBit = (num >> end) & 1;
            if (startBit != endBit) return false;
            start++;
            end--;
        }
        return true;
    }
}
