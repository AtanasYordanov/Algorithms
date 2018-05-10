package ExamPreparation.Exam4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Bridges {

    private static int[] north;
    private static int[] south;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        north = parseInput(reader);
        south = parseInput(reader);
        int maxPairs = calculatePairs();
        System.out.println(maxPairs);
    }

    private static int calculatePairs() {
        int[][] matrix = new int[north.length + 1][south.length + 1];
        for (int row = 1; row <= north.length; row++) {
            for (int col = 1; col <= south.length; col++) {
                matrix[row][col] = Math.max(matrix[row - 1][col], matrix[row][col - 1]);
                if (north[row - 1] == south[col - 1]) {
                    matrix[row][col]++;
                }
            }
        }
        return matrix[north.length][south.length];
    }

    private static int[] parseInput(BufferedReader reader) throws IOException {
        String[] input = reader.readLine().split(" ");
        int[] sequence = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            sequence[i] = Integer.parseInt(input[i]);
        }
        return sequence;
    }
}
