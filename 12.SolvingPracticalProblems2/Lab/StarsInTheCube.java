package SolvingPracticalProblems2.Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class StarsInTheCube {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        char[][][] cube = new char[n][n][n];
        for (int i = 0; i < n; i++) {
            String[] tokens = reader.readLine().split(" \\| ");
            for (int j = 0; j < n; j++) {
                String[] cells  = tokens[j].split(" ");
                for (int k = 0; k < n; k++) {
                    cube[j][i][k] = cells[k].charAt(0);
                }
            }
        }
        int totalCount = 0;
        Map<Character, Integer> starsPerChar = new TreeMap<>();
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                for (int k = 1; k < n - 1; k++) {
                    char centerChar = cube[i][j][k];
                    if (cube[i + 1][j][k] == centerChar && cube[i - 1][j][k] == centerChar
                            && cube[i][j + 1][k] == centerChar && cube[i][j - 1][k] == centerChar
                            && cube[i][j][k + 1] == centerChar && cube[i][j][k - 1] == centerChar){
                        totalCount++;
                        starsPerChar.putIfAbsent(centerChar, 0);
                        starsPerChar.put(centerChar, starsPerChar.get(centerChar) + 1);
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(totalCount).append(System.lineSeparator());
        for (Map.Entry<Character, Integer> pair : starsPerChar.entrySet()) {
            sb.append(pair.getKey()).append(" -> ").append(pair.getValue()).append(System.lineSeparator());
        }
        System.out.print(sb);
    }
}
