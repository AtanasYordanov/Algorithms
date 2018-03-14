package Recursion.Exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

public class ConnectedAreasInAMatrix {

    private static char[][] matrix;
    private static boolean[][] visited;
    private static Map<String, Integer> areas = new LinkedHashMap<>();

    public static void main(String[] args) throws IOException {
        initializeMatrix();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (isValid(i, j)) {
                    String start = i + ", " + j;
                    areas.put(start, 0);
                    markArea(i, j, start);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Total areas found: ").append(areas.size()).append(System.lineSeparator());
        int[] count = new int[1];

        areas.entrySet().stream()
                .sorted((a1, a2) -> {
                    int comp = Integer.compare(a2.getValue(), a1.getValue());
                    if (comp == 0) {
                        comp = Character.compare(a1.getKey().charAt(0), a2.getKey().charAt(0));
                        if (comp == 0) {
                            comp = Character.compare(a1.getKey().charAt(3), a2.getKey().charAt(3));
                        }
                    }
                    return comp;
                })
                .forEach(pair -> {
                    count[0]++;
                    sb.append(String.format("Area #%d at (%s), size: %d%n",
                            count[0], pair.getKey(), pair.getValue()));
                });
        System.out.println(sb);
    }

    private static void markArea(int row, int col, String start) {
        if (!isValid(row, col)) {
            return;
        }
        areas.put(start, areas.get(start) + 1);

        visited[row][col] = true;

        markArea(row, col + 1, start);
        markArea(row + 1, col, start);
        markArea(row, col - 1, start);
        markArea(row - 1, col, start);
    }

    private static boolean isValid(int row, int col) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[row].length
                && matrix[row][col] != '*' && !visited[row][col];
    }

    private static void initializeMatrix() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int rows = Integer.parseInt(reader.readLine());
        int cols = Integer.parseInt(reader.readLine());
        matrix = new char[rows][cols];
        visited = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            char[] input = reader.readLine().toCharArray();
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = input[j];
            }
        }
    }
}
