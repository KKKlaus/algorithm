public class MedianOfTwoSortedArrays {

    //方法非常巧妙: 见https://www.youtube.com/watch?v=LPFhl65R7ww&t=1246s
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;
        if (n1 > n2) return findMedianSortedArrays(nums2, nums1);
        int start = 0, end = n1;
        while (start <= end) {
            int partitionX = start + (end - start) / 2;
            int partitionY = (n1 + n2 + 1) / 2 - partitionX;

            int xLeft = partitionX == 0 ? Integer.MIN_VALUE : nums1[partitionX  - 1];
            int xRight = partitionX == n1 ? Integer.MAX_VALUE : nums1[partitionX];
            int yLeft = partitionY == 0 ? Integer.MIN_VALUE : nums2[partitionY - 1];
            int yRight = partitionY == n2 ? Integer.MAX_VALUE : nums2[partitionY];

            if (xLeft <= yRight && yLeft <= xRight) {
                if ((n1 + n2) % 2 == 0) {
                    return (double) (Math.max(xLeft, yLeft) + Math.min(xRight, yRight)) / 2;
                } else {
                    return (double) Math.max(xLeft, yLeft);
                }
            } else if (xLeft > yRight) {
                end = partitionX - 1;
            } else {
                start = partitionX + 1;
            }
        }

        return -1;
    }
}
