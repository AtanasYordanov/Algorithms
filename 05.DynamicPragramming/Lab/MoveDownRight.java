package DynamicPragramming1.Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class MoveDownRight {

    private static int[][] matrix;
    private static int[][] memo;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int rows = Integer.parseInt(reader.readLine());
        int cols = Integer.parseInt(reader.readLine());
        matrix = new int[rows][cols];
        memo = new int[rows][cols];
        fillMatrix(reader, rows, cols);
        fillMemo(rows, cols);
        printPath(rows - 1, cols - 1);
    }

    private static void printPath(int row, int col) {
        Deque<String> output = new ArrayDeque<>();
        while (!(row == 0 && col == 0)) {
            output.push(String.format("[%d, %d] ", row, col));
            if (row == 0) {
                col--;
            } else if (col == 0 || memo[row - 1][col] > memo[row][col - 1]) {
                row--;
            } else {
                col--;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[0, 0] ");
        while (!output.isEmpty()) {
            sb.append(output.pop());
        }
        System.out.println(sb);
    }

    private static void fillMemo(int rows, int cols) {
        memo[0][0] = matrix[0][0];
        for (int row = 1; row < rows; row++) {
            memo[row][0] = memo[row - 1][0] + matrix[row][0];
        }
        for (int col = 1; col < cols; col++) {
            memo[0][col] = memo[0][col - 1] + matrix[0][col];
        }
        for (int row = 1; row < rows; row++) {
            for (int col = 1; col < cols; col++) {
                memo[row][col] = Math.max(memo[row - 1][col], memo[row][col - 1]) + matrix[row][col];
            }
        }
    }

    private static void fillMatrix(BufferedReader reader, int n, int m) throws IOException {
        for (int i = 0; i < n; i++) {
            String[] tokens = reader.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                matrix[i][j] = Integer.parseInt(tokens[j]);
            }
        }
    }
}
