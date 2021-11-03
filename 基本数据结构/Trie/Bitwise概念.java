public class Bitwise概念 {

    public static void main(String[] args) {
        //Bitwise 相关概念
        System.out.println(5 << 2); // 5 = 101,左移两位-> 10100 = 20   同理可得>>
        // 负数的二进制是补码：原码 取反-> 反码 +1 -> 补码
        System.out.println(-1 >> 3); // 前面补1
        System.out.println(-1 >>> 3); // 跟>>一样，只不过最好把前面的空位全部补0

        for(int i = 5; i >= 0; i--) {
            System.out.println((25 >> i) & 1);
        }
    }
}
