package ExamPreparation.Exam4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LineInverter {

    private static boolean[][] square;
    private static int inversions = 0;
    private static int fails = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        initializeSquare(reader, n);
        generateInversions();
        System.out.println(inversions);
    }

    private static void generateInversions() {
        while (true) {
            int line = -1;
            int maxWhiteCount = 0;
            for (int row = 0; row < square.length; row++) {
                int currentWhiteCount = 0;
                for (int col = 0; col < square.length; col++) {
                    if (!square[row][col]) {
                        currentWhiteCount++;
                    }
                    if (currentWhiteCount > maxWhiteCount) {
                        maxWhiteCount = currentWhiteCount;
                        line = row;
                    }
                }
            }
            boolean vertical = false;
            for (int col = 0; col < square.length; col++) {
                int currentWhiteCount = 0;
                for (int row = 0; row < square.length; row++) {
                    if (!square[row][col]) {
                        currentWhiteCount++;
                    }
                    if (currentWhiteCount > maxWhiteCount) {
                        maxWhiteCount = currentWhiteCount;
                        line = col;
                        vertical = true;
                    }
                }
            }
            if (maxWhiteCount == 0) {
                break;
            }
            if (isImpossible(maxWhiteCount)) break;
            inversions++;
            invertLine(line, vertical);
        }
    }

    private static boolean isImpossible(int maxWhiteCount) {
        if (square.length % 2 == 0 && maxWhiteCount == square.length / 2) {
            fails++;
            if (fails > 1) {
                inversions = -1;
                return true;
            }
        } else if (square.length % 2 == 1 && maxWhiteCount <= square.length / 2) {
            inversions = -1;
            return true;
        } else {
            fails = 0;
        }
        return false;
    }

    private static void invertLine(int line, boolean vertical) {
        if (vertical) {
            for (int i = 0; i < square.length; i++) {
                square[i][line] = !square[i][line];
            }
        } else {
            for (int i = 0; i < square.length; i++) {
                square[line][i] = !square[line][i];
            }
        }
    }

    private static void initializeSquare(BufferedReader reader, int n) throws IOException {
        square = new boolean[n][n];
        for (int row = 0; row < n; row++) {
            String line = reader.readLine();
            for (int col = 0; col < n; col++) {
                square[row][col] = line.charAt(col) == 'B';
            }
        }
    }
}
