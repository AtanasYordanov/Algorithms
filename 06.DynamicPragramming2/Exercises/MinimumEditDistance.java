package DynamicProgramming2.Exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class MinimumEditDistance {
    private static String s1;
    private static String s2;
    private static int[][] matrix;
    private static int[] s1Map;
    private static int[] s2Map;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int replaceCost = Integer.parseInt(reader.readLine().substring(15));
        int insertCost = Integer.parseInt(reader.readLine().substring(14));
        int deleteCost = Integer.parseInt(reader.readLine().substring(14));
        s1 = reader.readLine().substring(5);
        s2 = reader.readLine().substring(5);
        matrix = new int[s1.length() + 1][s2.length() + 1];
        s1Map = initializeMap(s1);
        s2Map = initializeMap(s2);
        calcLCS(s1.length() - 1, s2.length() - 1);
        adjustMappings();
        Deque<String> result = solve(replaceCost, insertCost, deleteCost);
        printResult(result);
    }

    private static void printResult(Deque<String> result) {
        while (!result.isEmpty()) {
            System.out.println(result.pop());
        }
    }

    private static Deque<String> solve(int replaceCost, int insertCost, int deleteCost) {
        Deque<String> result = new ArrayDeque<>();
        int editDistance = 0;
        int s1Index = s1.length() - 1;
        int s2Index = s2.length() - 1;
        while (s1Index >= 0 || s2Index >= 0) {
            if (s1Index < 0) {
                result.push(String.format("INSERT(%d, %c)", s2Index, s2.charAt(s2Index--)));
                editDistance += insertCost;
                continue;
            }
            if (s2Index < 0) {
                result.push(String.format("DELETE(%d)", s1Index--));
                editDistance += deleteCost;
                continue;
            }
            if (s1Map[s1Index] == -1 && s2Map[s2Index] == -1) {
                if (replaceCost <= deleteCost + insertCost) {
                    result.push(String.format("REPLACE(%d, %c)", s1Index--, s2.charAt(s2Index--)));
                    editDistance += replaceCost;
                } else {
                    result.push(String.format("INSERT(%d, %c)", s1Index, s2.charAt(s2Index--)));
                    result.push(String.format("DELETE(%d)", s1Index--));
                    editDistance += insertCost + deleteCost;
                }
            } else if (s1Map[s1Index] == -1 && s2Map[s2Index] != -1) {
                result.push(String.format("DELETE(%d)", s1Index--));
                editDistance += deleteCost;
            } else if (s1Map[s1Index] != -1 && s2Map[s2Index] == -1) {
                result.push(String.format("INSERT(%d, %c)", s2Index, s2.charAt(s2Index--)));
                editDistance += insertCost;
            } else {
                s1Index--;
                s2Index--;
            }
        }
        result.push(String.format("Minimum edit distance: %d", editDistance));
        return result;
    }

    private static int[] initializeMap(String str) {
        int[] result = new int[str.length()];
        for (int i = 0; i < result.length; i++) {
            result[i] = -1;
        }
        return result;
    }

    private static int calcLCS(int x, int y) {
        if (x < 0 || y < 0) {
            return 0;
        }
        if (matrix[x][y] == 0) {
            matrix[x][y] = Math.max(calcLCS(x - 1, y), calcLCS(x, y - 1));
            if (s1.charAt(x) == s2.charAt(y)) {
                if (Math.abs(x - y) < Math.abs(x - s1Map[x]) || s1Map[x] == -1) {
                    s1Map[x] = y;
                }
                matrix[x][y] = Math.max(matrix[x][y], calcLCS(x - 1, y - 1) + 1);
            }
        }
        return matrix[x][y];
    }

    private static void adjustMappings() {
        for (int i = 0; i < s1Map.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (s1Map[i] == s1Map[j]) {
                    s1Map[j] = -1;
                }
            }
            if (s1Map[i] != -1) {
                s2Map[s1Map[i]] = i;
            }
        }
    }
}
