import java.util.Stack;

public class DecodeString_21年9月9号 {

    public String decodeString(String s) {
        Stack<Integer> countStack = new Stack<>();
        Stack<StringBuilder> stringStack = new Stack<>();
        int index = 0, count = 0;
        StringBuilder cur = new StringBuilder();
        while (index < s.length()) {
            char c = s.charAt(index);
            if (Character.isDigit(c)) {
                count = count * 10 + (c - '0');
            } else if (c == '[') {
                countStack.push(count);
                stringStack.push(cur);
                cur = new StringBuilder();
                count = 0;
            } else if (c == ']') {
                StringBuilder temp = cur;
                cur = stringStack.pop();
                int times = countStack.pop();
                while (times > 0) {
                    cur.append(temp);
                    times--;
                }
            } else {
                cur.append(c);
            }
            index++;
        }
        return cur.toString();
    }
}
