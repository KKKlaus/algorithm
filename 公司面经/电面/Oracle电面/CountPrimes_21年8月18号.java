public class CountPrimes_21年8月18号 {

    // 时间复杂度 O(NloglogN)
    // 里面for循环复杂度为n/2 + n/3 + n/5 + ....n / 最后一个质数 = loglogN
    public int countPrimes(int n) {
        boolean[] notPrime = new boolean[n];
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (notPrime[i] == false) {
                count++;
                for (int j = 2; i * j < n; j++) {
                    notPrime[i * j] = true;
                }
            }
        }
        return count;
    }
}
