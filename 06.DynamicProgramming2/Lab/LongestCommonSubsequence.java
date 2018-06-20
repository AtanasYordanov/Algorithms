package DynamicProgramming2.Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LongestCommonSubsequence {

    private static String firstStr;
    private static String secondStr;
    private static int[][] matrix;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        firstStr = reader.readLine();
        secondStr = reader.readLine();
        matrix = new int[firstStr.length()][secondStr.length()];
        System.out.println(calcLCS(firstStr.length() - 1, secondStr.length() - 1));

    }

    private static int calcLCS(int x, int y) {
        if (x < 0 || y < 0) {
            return 0;
        }
        if (matrix[x][y] == 0) {
            matrix[x][y] = Math.max(calcLCS(x - 1, y), calcLCS(x, y - 1));
            if (firstStr.charAt(x) == secondStr.charAt(y)) {
                matrix[x][y] = Math.max(matrix[x][y], calcLCS(x - 1, y - 1) + 1);
            }
        }
        return matrix[x][y];
    }
}
