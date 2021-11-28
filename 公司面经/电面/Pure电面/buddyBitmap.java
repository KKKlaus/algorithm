package Pure电面;

public class buddyBitmap {
    private static void setBits(int[][] bitmap, int start, int length) {
        if (bitmap.length == 0) {
            return;
        }
        int level = bitmap.length - 1;
        int end = Math.min(bitmap[level].length, start + length) - 1;
        while (level >= 0) {
            for (int i = start; i < end + 1; ++i) {
                bitmap[level][i] = 1;
            }
            --level;
            if (start % 2 == 1) {
                start = bitmap[level + 1][start - 1] == 0 ? start + 1 : start;
            }
            if (end % 2 == 0 && end + 1 < bitmap[level + 1].length) {
                end = bitmap[level + 1][end + 1] == 0 ? end - 1 : end;
            }
            start = start/2;
            end = end/2;
        }
    }
    private static void clearBits(int[][] bitmap, int start, int length) {
        if (bitmap.length == 0) {
            return;
        }
        int level = bitmap.length - 1;
        int end = Math.min(bitmap[level].length, start + length) -1;
        while (level >= 0) {
            for (int i = start; i < end + 1; ++i) {
                bitmap[level][i] = 0;
            }
            start = start/2;
            end = end/2;
            --level;
        }
    }

    public static void main(String[] args) {
//        int[][] bitmap = new int[][] {{0}, {0, 1}, {0,1,1,1}, {0,1,1,1,1,1,1,1}};
        // Clear
        int[][] bitmap = new int[][] {{0}, {0, 1}, {1,0,1,1}, {1,1,1,0,1}};
        clearBits(bitmap, 0, 1);
        for (int i = 0; i < bitmap.length; ++i) {
            for (int j  = 0; j < bitmap[i].length; ++j) {
                System.out.print(bitmap[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        // set
        System.out.println();
        bitmap = new int[][] {{0}, {0, 0}, {0,0,0,0}, {0,0,0,0,0,0,0,0}};
        setBits(bitmap, 4, 8);
        for (int i = 0; i < bitmap.length; ++i) {
            for (int j  = 0; j < bitmap[i].length; ++j) {
                System.out.print(bitmap[i][j] + " ");
            }
            System.out.println();
        }

    }
}