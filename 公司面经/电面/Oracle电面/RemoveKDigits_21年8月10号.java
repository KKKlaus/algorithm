import java.util.Stack;

public class RemoveKDigits_21年8月10号 {


    // 用stack，全部入队，根据k的大小和前后关系出队，看第12行的while循环很关键
    public String removeKdigits(String num, int k) {
        int n = num.length();
        if (n == k) return "0";
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (k > 0 && !stack.isEmpty() && stack.peek() > num.charAt(i)) {
                stack.pop();
                k--;
            }
            stack.push(num.charAt(i));
        }
        while (k > 0) {
            stack.pop();
            k--;
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        sb = sb.reverse();

        while (sb.length() > 1 && sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }

        return sb.toString();
    }
}
