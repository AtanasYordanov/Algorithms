package Recursion.Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class FindAllPathsInLabyrinth {

    private static char[][] lab;

    public static void main(String[] args) throws IOException {
        initializeLabyrith();
        String path = "";
        generatePath(0, 0, path);
    }

    private static void generatePath(int row, int col, String path) {
        if (!isValid(row, col)) {
            return;
        }
        if (lab[row][col] == 'e') {
            System.out.println(path);
            return;
        }
        lab[row][col] = 'v';

        generatePath(row, col + 1, path + "R");
        generatePath(row + 1, col, path + "D");
        generatePath(row, col - 1, path + "L");
        generatePath(row - 1, col, path + "U");

        lab[row][col] = '-';
    }

    private static boolean isValid(int row, int col) {
        return row >= 0 && row < lab.length && col >= 0 && col < lab[row].length
                && lab[row][col] != '*' && lab[row][col] != 'v';
    }

    private static void initializeLabyrith() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int rows = Integer.parseInt(reader.readLine());
        int cols = Integer.parseInt(reader.readLine());
        lab = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            char[] input = reader.readLine().toCharArray();
            for (int j = 0; j < cols; j++) {
                lab[i][j] = input[j];
            }
        }
    }
}
