package Exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Zerg {

    private static BigInteger[][] matrix;
    private static boolean[][] enemyMatrix;
    private static int targetX;
    private static int targetY;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] tokens = reader.readLine().split(" ");
        int n = Integer.parseInt(tokens[0]);
        int m = Integer.parseInt(tokens[1]);
        enemyMatrix = new boolean[n][m];
        matrix = new BigInteger[n][m];
        tokens = reader.readLine().split(" ");
        targetX = Integer.parseInt(tokens[0]);
        targetY = Integer.parseInt(tokens[1]);
        int enemies = Integer.parseInt(reader.readLine());
        buildEnemyMatrix(reader, enemies);
        buildDpMatrix();
        System.out.println(matrix[targetX][targetY]);
    }

    private static void buildDpMatrix() {
        for (int i = 0; i <= targetX; i++) {
            if (enemyMatrix[i][0]) {
                break;
            }
            matrix[i][0] = BigInteger.ONE;
        }
        for (int i = 0; i <= targetY; i++) {
            if (enemyMatrix[0][i]) {
                break;
            }
            matrix[0][i] = BigInteger.ONE;
        }
        for (int i = 1; i <= targetX; i++) {
            for (int j = 1; j <= targetY; j++) {
                matrix[i][j] = BigInteger.ZERO;
                if (!enemyMatrix[i][j]) {
                    if (matrix[i - 1][j] != null) {
                        matrix[i][j] = matrix[i][j].add(matrix[i - 1][j]);
                    }
                    if (matrix[i][j - 1] != null) {
                        matrix[i][j] = matrix[i][j].add(matrix[i][j - 1]);
                    }
                }
            }
        }
    }

    private static void buildEnemyMatrix(BufferedReader reader, int enemies) throws IOException {
        String[] tokens;
        for (int i = 0; i < enemies; i++) {
            tokens = reader.readLine().split(" ");
            int x = Integer.parseInt(tokens[0]);
            int y = Integer.parseInt(tokens[1]);
            enemyMatrix[x][y] = true;
        }
    }
}
