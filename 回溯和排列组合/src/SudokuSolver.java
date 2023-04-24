public class SudokuSolver {

    // The time complexity should be 9 ^ m (m represents the number of blanks to be filled in),
    public void solveSudoku(char[][] board) {
        solve(board);
    }

    private boolean solve(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != '.') continue;
                for (char c = '1'; c <= '9'; c++) {
                    if(isValid(board, i, j, c)) {
                        board[i][j] = c;
                        if (solve(board)) return true;
                        else board[i][j] = '.';
                    }
                }
                return false;// 这一行
            }
        }
        return true; // 所有各自都是valid的数字
    }

    // 如何求boxRow和boxcol很关键
    private boolean isValid(char[][] board, int row, int col, char c) {
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == c) return false;
            if (board[row][i] == c) return false;
            int boxRow = (row / 3) * 3 + i / 3;
            int boxCol = (col / 3) * 3 + i % 3;
            if (board[boxRow][boxCol] == c) return false;
        }
        return true;
    }
}
