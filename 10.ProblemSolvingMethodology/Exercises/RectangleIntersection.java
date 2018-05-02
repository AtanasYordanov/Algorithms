package ProblemSolvingMethodology.Exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RectangleIntersection {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int[][] plane = new int[2000][2000];
        for (int i = 0; i < n; i++) {
            String[] inputTokens = reader.readLine().split(" ");
            int x1 = Integer.parseInt(inputTokens[0]) + 1000;
            int x2 = Integer.parseInt(inputTokens[1]) + 1000;
            int y1 = Integer.parseInt(inputTokens[2]) + 1000;
            int y2 = Integer.parseInt(inputTokens[3]) + 1000;

            for (int j = x1; j < x2; j++) {
                for (int k = y1; k < y2; k++) {
                    plane[j][k]++;
                }
            }
        }
        int totalIntersectingArea = 0;
        for (int j = 0; j < plane.length; j++) {
            for (int k = 0; k < plane.length; k++) {
                if (plane[j][k] > 1) {
                    totalIntersectingArea++;
                }
            }
        }
        System.out.println(totalIntersectingArea);
    }
}