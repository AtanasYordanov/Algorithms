package Recursion.Lab;

public class EightQueensPuzzle {

    private static char[][] board = new char[8][8];

    public static void main(String[] args) {
        initializaBoard();
        generate(0);
    }

    private static void generate(int row) {
        if (row >= 8) {
            printBoard();
            return;
        }
        for (int col = 0; col < 8; col++) {
            if (!isValid(row, col)) {
                board[row][col] = '*';
                generate(row + 1);
                board[row][col] = '-';
            }
        }
    }

    private static boolean isValid(int row, int col) {
        for (int i = 0; i < 8; i++) {
            if (isAttacked(i, col) || isAttacked(row + i, col + i) || isAttacked(row + i, col - i)
                    || isAttacked(row - i, col + i) || isAttacked(row - i, col - i)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isAttacked(int row, int col) {
        return row <= 7 && row >= 0 && col <= 7 && col >= 0 && board[row][col] == '*';
    }

    private static void initializaBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = '-';
            }
        }
    }

    private static void printBoard() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                sb.append(board[i][j]).append(" ");
            }
            sb.append(System.lineSeparator());
        }
        System.out.println(sb);
    }
}
