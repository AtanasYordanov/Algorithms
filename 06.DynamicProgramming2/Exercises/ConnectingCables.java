package DynamicProgramming2.Exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ConnectingCables {

    private static int[][] matrix;
    private static int[] cables;
    private static int[] orderedCables;

    public static void main(String[] args) throws IOException {
        cables = parseInput();
        orderedCables = orderCables();
        matrix = new int[cables.length + 1][cables.length + 1];
        int maxPairs = calculatePairs(cables.length - 1, cables.length - 1);
        System.out.printf("Maximum pairs connected: %d%n", maxPairs);
    }

    private static int calculatePairs(int x, int y) {
        if (x < 0 || y < 0) {
            return 0;
        }
        if (matrix[x][y] == 0) {
            matrix[x][y] = Math.max(calculatePairs(x - 1, y), calculatePairs(x, y - 1));
            if (cables[x] == orderedCables[y]) {
                matrix[x][y] = calculatePairs(x - 1, y - 1) + 1;
            }
        }
        return matrix[x][y];
    }

    private static int[] orderCables() {
        int[] orderedCables = Arrays.copyOf(cables, cables.length);
        Arrays.sort(orderedCables);
        return orderedCables;
    }

    private static int[] parseInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split(" ");
        int[] cables = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            cables[i] = Integer.parseInt(input[i]);
        }
        return cables;
    }
}
