import java.util.*;

// 如有不懂，参考youtube视频https://www.youtube.com/watch?v=l2rCz7skAlk
public class LongestIncreasingSubsequence {

    // 方法1：非常经典的dp : O(N*N)
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        int ans = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }

    // [10,9,2,5,3,7,101,18]
    // list = smallest ending number of a subsequence
    // 10
    // 9       序列9比10好，因为9更容易接其他树形成最长序列
    // 2
    // 2 5     如果比最后一个大，就加到后面去
    // 2 3     5 比 3 好
    // 2 3 7
    // 2 3 7 101
    // 2 3 7 18
    // 可以看到这个list/arr是一个递增的，每次可以用binary search去找到插入的位置去替换
    // O(NlogN)
    public static int lengthOfLIS_2(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            if (list.size() == 0 || num > list.get(list.size() - 1)) {
                list.add(num);
            } else {
                int pos = binarySearch(list, num);
                list.set(pos, num);
            }
        }
        return list.size();
    }

    // start + 1 < end的模板不太适用这里
    private static int binarySearch(List<Integer> list, int num) {
        int start = 0, end = list.size() - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (list.get(mid) == num) return mid;
            else if (list.get(mid) < num) {
                start = mid + 1;
            } else {
                end = mid;      // 如果大于有可能就是这个值，所以不需要-1
            }
        }
        return start;
    }

    public static void main(String[] args) {
        LongestIncreasingSubsequence t = new LongestIncreasingSubsequence();
        t.test();
    }

    private void test() {
//        int[] arr = new int[]{10,9,2,5,3,7,101,18};
        int[] arr = new int[]{0,1,0,3,2,3};
//        int[] arr = new int[]{3, 4, 1};
        printLIS(arr);
    }



    // 打印： 很难想到
    // 以上方法的list结果并不是最终的序列比如{3, 4, 1}，最长生序子串是3，4，但上面list返回的是1，4
    // 所以如果要打印的话需要通过node
    public List<Node> printLIS(int[] nums) {
        List<Node> list = new ArrayList<>();
        for (int num : nums) {
            Node node = new Node(num);
            int pos = binarySearch_Node(list, num);  // binarySearch里面加了第一句
            if(pos != 0) {
                node.prev = list.get(pos - 1);
            }

            if (pos == list.size()) {
                list.add(node);
            } else {
                list.set(pos, node);
            }

        }
        // print
        Node start = list.get(list.size() - 1);
        StringBuilder res = new StringBuilder();
        while (start != null) {
            res.append(start.val).append(" ");
            start = start.prev;
        }
        System.out.println(res.reverse());
        return list;
    }

    private static int binarySearch_Node(List<Node> list, int num) {
        // 这里加了一句，因为这个binary search在大于最右边的数时，在第52行只要求输出个数的binary search中是不做这个搜索的(主函数里直接判断且在list最后加上了)
        // 而这里我们需要pos所以做了额外判断，毕竟binary search只搜数组里面的，这里很容易出错
        if (list.size() != 0 && num > list.get(list.size() - 1).val) return list.size();
        int start = 0, end = list.size() - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (list.get(mid).val == num) return mid;
            else if (list.get(mid).val < num) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return start;
    }












    // 最长降序序列 longest decrease sequence
     public static int lengthOfLDS(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            if (list.size() == 0 || num < list.get(list.size() - 1)) {
                list.add(num);
            } else {
                int pos = binarySearch_decrese(list, num);
                list.set(pos, num);
            }
        }
        return list.size();
    }

    private static int binarySearch_decrese(List<Integer> list, int num) {
        int start = 0, end = list.size() - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (list.get(mid) == num) return mid;
            else if (list.get(mid) > num) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return start;
    }


    class Node {
        Node prev;
        int val;
        Node() {}
        Node(int val) {
            this.val = val;
        }
    }
}
