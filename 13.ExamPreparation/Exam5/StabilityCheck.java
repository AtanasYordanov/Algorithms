package ExamPreparation.Exam5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StabilityCheck {

    private static int[][] field;
    private static long[][] memo;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int buildingSize = Integer.parseInt(reader.readLine());
        int fieldSize = Integer.parseInt(reader.readLine());
        field = new int[fieldSize][fieldSize];
        memo = new long[fieldSize - buildingSize + 1][fieldSize];
        for (int row = 0; row < fieldSize; row++) {
            String[] tokens = reader.readLine().split(" ");
            for (int col = 0; col < fieldSize; col++) {
                field[row][col] = Integer.parseInt(tokens[col]);
            }
        }
        for (int row = 0; row < memo.length; row++) {
            for (int col = 0; col < memo[0].length; col++) {
                long sum = 0L;
                for (int r = row; r < row + buildingSize; r++) {
                    sum += field[r][col];
                }
                memo[row][col] = sum;
            }
        }
        long maxSum = Long.MIN_VALUE;
        for (int row = 0; row < memo.length; row++) {
            for (int col = 0; col <= memo[0].length - buildingSize; col++) {
                long sum = 0L;
                for (int c = col; c < col + buildingSize; c++) {
                    sum += memo[row][c];
                }
                if (sum > maxSum) {
                    maxSum = sum;
                }
            }
        }
        System.out.println(maxSum);
    }
}