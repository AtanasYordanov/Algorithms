package ExamPreparation.Exam5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Island {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] tokens = reader.readLine().split(" ");
        int[] columns = new int[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            int size = Integer.parseInt(tokens[i]);
            columns[i] = size;
        }
        System.out.println(getBestArea(columns));
    }

    private static int getBestArea(int[] columns) {
        int[] counts = new int[columns.length];
        int maxArea = 0;
        for (int i = 0; i < columns.length; i++) {
            int columnSize = columns[i];
            int counter = 1;
            boolean lookForward = true;
            for (int j = i - 1; j >= 0; j--) {
                if (columns[j] == columnSize) {
                    counter = counts[j];
                    lookForward = false;
                    break;
                } else if (columns[j] > columnSize) {
                    counter++;
                } else {
                    break;
                }
            }
            if (lookForward) {
                for (int j = i + 1; j < columns.length; j++) {
                    if (columns[j] >= columnSize) {
                        counter++;
                    } else {
                        break;
                    }
                }
            }
            int area = columnSize * counter;
            counts[i] = counter;
            if (area > maxArea) {
                maxArea = area;
            }
        }
        return maxArea;
    }
}
