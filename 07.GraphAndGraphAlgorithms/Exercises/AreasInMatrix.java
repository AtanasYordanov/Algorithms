package GraphAndGraphAlgorithms.Exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;


public class AreasInMatrix {

    private static char[][] matrix;
    private static boolean[][] visited;
    private static Map<Character, Integer> areas = new TreeMap<>();

    public static void main(String[] args) throws IOException {
        initializeMatrices();
        markAreas();
        printResult();
    }

    private static void initializeMatrices() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        matrix = new char[n][];
        for (int i = 0; i < n; i++) {
            matrix[i] = reader.readLine().toCharArray();
        }
        visited = new boolean[n][matrix[0].length];
    }

    private static void markAreas() {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (!visited[row][col]){
                    char currentChar = matrix[row][col];
                    DFS(row, col, currentChar);
                    areas.putIfAbsent(currentChar, 0);
                    areas.put(currentChar, areas.get(currentChar) + 1);
                }
            }
        }
    }

    private static void printResult() {
        int totalAreas = areas.values().stream().mapToInt(Integer::valueOf).sum();
        System.out.printf("Areas: %d%n", totalAreas);
        for (Map.Entry<Character, Integer> pair : areas.entrySet()) {
            System.out.printf("Letter '%c' -> %d%n", pair.getKey(), pair.getValue());
        }
    }

    private static void DFS(int row, int col, char ch) {
        if (!isInBounds(row, col)|| visited[row][col] || matrix[row][col] != ch){
            return;
        }
        visited[row][col] = true;
        DFS(row + 1, col, ch);
        DFS(row, col + 1, ch);
        DFS(row - 1, col, ch);
        DFS(row, col - 1, ch);
    }

    private static boolean isInBounds(int row, int col) {
        return row >= 0 && col >= 0 && row < matrix.length && col < matrix[0].length;
    }
}
