package SubString;

public class MinimumWindowSubstring {

    // 方法一： sliding window
    public String minWindow(String s, String t) {
        String res = "";
        int[] map = new int[128];
        for (char c : t.toCharArray()) {
            map[c]++;
        }
        int count = t.length(), end = 0, start = 0, minLen = Integer.MAX_VALUE;
        while (end < s.length()) {
            char c1 = s.charAt(end);
            if (map[c1] > 0) count--;
            map[c1]--;
            end++;
            while (count == 0) {
                if (minLen > end - start) {
                    minLen = end - start + 1;
                    res = s.substring(start, end);
                }
                char c2 = s.charAt(start);
                map[c2]++;
                if (map[c2] > 0) count++;
                start++;
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : res;
    }




    //方法2：  二分答案
    String res = "";
    public String minWindow_2(String s, String t) {
        int start = t.length(), end = s.length();
        int[] tarr = new int[128];
        for (char tc : t.toCharArray()) {
            tarr[tc - '0']++;
        }
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            System.out.print(" len= " + mid);
            if (isValid(s, mid, tarr)) {
                end = mid;
            } else {
                start = mid;
            }
        }

        if (isValid(s, start, tarr)) return res;
        if (isValid(s, end, tarr)) return res;
        return res;
    }

    private boolean isValid(String s, int len, int[] tarr) {
        int[] sarr = new int[128];
        for (int i = 0; i < s.length(); i++) {
            char scur = s.charAt(i);
            sarr[scur - '0']++;
            if (i >= len) {
                char delete = s.charAt(i - len);
                sarr[delete - '0']--;
            }
            if(helper(sarr, tarr)) {
                System.out.print("i=" + i + " len=" + len);
                if (i - len + 1 >= 0) res = s.substring(i-len + 1, i+1);
                return true;
            }
        }
        return false;
    }

    private boolean helper(int[] sarr, int[] tarr) {
        for (int i = 0; i < 128; i++) {
            if (tarr[i] != 0) {
                if (tarr[i] > sarr[i]) return false;
            }
        }
        return true;
    }
}
