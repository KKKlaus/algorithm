public class JumpGame2 {

    // 方法1： 贪心
    // 注意i < nums.length - 1,要不然有可能在终点step再加1
    public int jump(int[] nums) {
        int max = 0, step = 0, end = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            max = Math.max(max, i + nums[i]);
            if (i == end) {
                step++;
                end = max;
            }
        }
        return step;
    }


    // 方法2：类bfs，但是在进队列时只需要最左边的就行
    // 因为假设上个点是尽量少步的，如果他能到右边的某个点，就必能到最左边的那个店
    public int jump2(int[] nums) {
        int pos = nums.length - 1;
        int step = 0;
        while (pos != 0) {
            for (int i = 0; i < pos; i++) {
                if (i + nums[i] >= pos) {
                    pos = i;
                    step++;
                }
            }
        }
        return step;
    }
}
