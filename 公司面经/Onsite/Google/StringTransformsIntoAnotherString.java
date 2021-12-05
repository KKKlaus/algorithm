package Google;

import java.util.HashMap;
import java.util.HashSet;

public class StringTransformsIntoAnotherString {

    // 想成一个图
    // 1. 如果出度>1肯定不行， hashmap可以判断
    // 2. 然后最坏情况是形成环，但只要能找到一个tmp替换就行。比如“a -> c -> e -> a”，我们需要打断循环，使用临时变量缓存打断点，在这种情况下，就变成了两步变换：“a -> tmp”和“tmp -> c -> e -> a”。
    // 3. 然后要能有tmp就必须有unused character，而且必须是values()里： 可以思考如下：
    // "abcdefghijklmnopqrstuvwxyz"
    // "zzzzzzzzzzzzzzzzzzzzzzzzzz"
    // 以上key拉满26个没关系
    //
    // "abcdefghijklmnopqrstuvwxyz"
    // "bcdefghijklmnopqrstuvwxyza"
    // values拉满不行，因为每个unique value其实就确定了一种唯一映射，一旦形成环，没有tmp可以换了

    public boolean canConvert(String str1, String str2) {
        if (str1.equals(str2)) return true;
        HashMap<Character, Character> map = new HashMap<>();
        for (int i = 0; i < str1.length(); i++) {
            char c1 = str1.charAt(i), c2 = str2.charAt(i);
            if (!map.containsKey(c1)) {
                map.put(c1, c2);
            } else {
                if (map.get(c1) != c2) return false;
            }
        }
        return new HashSet<>(map.values()).size() != 26;
    }
}
