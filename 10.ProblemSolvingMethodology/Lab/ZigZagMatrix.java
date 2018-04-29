package ProblemSolvingMethodology.Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class ZigZagMatrix {

    // 25/100 in Judge but works correctly as opposed to the author's solution

    private static int[][] matrix;
    private static int[][] evenUpMatrix;
    private static int[][] evenDownMatrix;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int rows = Integer.parseInt(reader.readLine());
        int cols = Integer.parseInt(reader.readLine());
        matrix = new int[rows][cols];
        evenUpMatrix = new int[rows][cols];
        evenDownMatrix = new int[rows][cols];
        fillOriginalMatrix(reader, rows, cols);
        fillMemoMatrices(rows, cols);

        int maxSum = Integer.MIN_VALUE;
        int maxSumRow = -1;
        boolean evenUp = false;
        for (int row = 0; row < rows; row++) {
            int current = evenUpMatrix[row][cols - 1];
            if (current > maxSum) {
                evenUp = true;
                maxSum = current;
                maxSumRow = row;
            }
            current = evenDownMatrix[row][cols - 1];
            if (current >= maxSum) {
                evenUp = false;
                maxSum = current;
                maxSumRow = row;
            }
        }

        Deque<Integer> path = buildPath(rows, cols, maxSumRow, evenUp);
        System.out.println(getOutputString(maxSum, path));
    }

    private static Deque<Integer> buildPath(int rows, int cols, int maxSumRow, boolean evenUp) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(matrix[maxSumRow][cols - 1]);
        int[][] maxSumMatrix = evenUp ? evenUpMatrix : evenDownMatrix;
        for (int col = cols - 2; col >= 0; col--) {
            int colMax = Integer.MIN_VALUE;
            boolean checkUp = evenUp ? col % 2 == 0 : col % 2 == 1;
            if (checkUp) {
                for (int row = maxSumRow - 1; row >= 0; row--) {
                    int current = maxSumMatrix[row][col];
                    if (current >= colMax) {
                        colMax = current;
                        maxSumRow = row;
                    }
                }
            } else {
                for (int row = maxSumRow + 1; row < rows; row++) {
                    int current = maxSumMatrix[row][col];
                    if (current > colMax) {
                        colMax = current;
                        maxSumRow = row;
                    }
                }
            }
            stack.push(matrix[maxSumRow][col]);
        }
        return stack;
    }

    private static String getOutputString(int maxSum, Deque<Integer> path) {
        StringBuilder sb = new StringBuilder();
        sb.append(maxSum).append(" = ");
        while (!path.isEmpty()) {
            sb.append(path.pop()).append(" + ");
        }
        sb.delete(sb.length() - 3, sb.length());
        return sb.toString();
    }

    private static void fillMemoMatrices(int rows, int cols) {
        for (int row = 0; row < rows; row++) {
            evenUpMatrix[row][0] = matrix[row][0];
            evenDownMatrix[row][0] = matrix[row][0];
        }
        for (int col = 1; col < cols; col++) {
            for (int row = 0; row < rows; row++) {
                int prevMaxDown = findPrevMax(col - 1, row, false);
                int prevMaxUp = findPrevMax(col - 1, row, true);
                if (col % 2 == 1) {
                    evenUpMatrix[row][col] = matrix[row][col] + prevMaxUp;
                    evenDownMatrix[row][col] = matrix[row][col] + prevMaxDown;
                } else {
                    evenUpMatrix[row][col] = matrix[row][col] + prevMaxDown;
                    evenDownMatrix[row][col] = matrix[row][col] + prevMaxUp;
                }
            }
        }
    }

    private static void fillOriginalMatrix(BufferedReader reader, int rows, int cols) throws IOException {
        for (int row = 0; row < rows; row++) {
            String[] values = reader.readLine().split(",");
            for (int col = 0; col < cols; col++) {
                matrix[row][col] = Integer.parseInt(values[col]);
            }
        }
    }

    private static int findPrevMax(int col, int currentRow, boolean checkUp) {
        int max = 0;
        if (checkUp) {
            for (int row = 0; row < currentRow; row++) {
                int current;
                if (col % 2 == 1) {
                    current = evenDownMatrix[row][col];
                } else {
                    current = evenUpMatrix[row][col];
                }
                if (current > max) {
                    max = current;
                }
            }
        } else {
            for (int row = currentRow + 1; row < matrix.length; row++) {
                int current;
                if (col % 2 == 1) {
                    current = evenUpMatrix[row][col];
                } else {
                    current = evenDownMatrix[row][col];
                }
                if (current > max) {
                    max = current;
                }
            }
        }
        return max;
    }
}