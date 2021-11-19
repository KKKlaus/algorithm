public class ConstructKPalindromeStrings {

    public boolean canConstruct(String s, int k) {
        int[] map = new int[26];
        for (char c : s.toCharArray()) {
            map[c - 'a']++;
        }
        int odd = 0;
        for (int i = 0; i < 26; i++) {
            if (map[i] % 2 == 1) odd++;
        }
        return odd <=k && k <= s.length();
    }
}
