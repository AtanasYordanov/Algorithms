package GreedyAlgorithms.Exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class KnightsTour {

    private static final int[] x = new int[]{1, -1, 1, -1, 2, 2, -2, -2};
    private static final int[] y = new int[]{2, 2, -2, -2, 1, -1, 1, -1};
    private static int[][] board;
    private static int knightRow = 0;
    private static int knightCol = 0;
    private static int n;
    private static int nextRow = -1;
    private static int nextCol = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(reader.readLine());
        board = new int[n][n];
        board[knightRow][knightCol] = 1;
        int steps = 2;
        while (steps <= n * n) {
            int minCount = 8;
            for (int i = 0; i < 8; i++) {
                int row = knightRow + x[i];
                int col = knightCol + y[i];
                int count = calcPossibleSteps(row, col);
                if (minCount > count) {
                    minCount = count;
                    nextRow = row;
                    nextCol = col;
                }
            }
            knightRow = nextRow;
            knightCol = nextCol;
            board[knightRow][knightCol] = steps;
            steps++;
        }
        printBoard(board);
    }

    private static int calcPossibleSteps(int row, int col) {
        if (!isInBounds(row, col) || board[row][col] != 0) {
            return 8;
        }
        int count = 0;
        for (int i = 0; i < 8; i++) {
            if (isInBounds(row + x[i], col + y[i]) && board[row + x[i]][col + y[i]] == 0) {
                count++;
            }
        }
        return count;
    }

    private static void printBoard(int[][] board) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(String.format("%4s", board[i][j]));
            }
            sb.append(System.lineSeparator());
        }
        System.out.println(sb.toString());
    }

    public static boolean isInBounds(int row, int col) {
        return row >= 0 && row < n && col >= 0 && col < n;
    }
}
