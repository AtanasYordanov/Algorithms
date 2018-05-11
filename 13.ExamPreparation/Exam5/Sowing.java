package ExamPreparation.Exam5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sowing {

    private static char[] field;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int seeds = Integer.parseInt(reader.readLine().trim());
        field = reader.readLine().replaceAll(" ", "").toCharArray();
        int goodSpots = 0;
        for (char ch : field) {
            if (ch == '1') {
                goodSpots++;
            }
        }
        generate(0, seeds, goodSpots);
        System.out.print(sb);
    }

    private static void generate(int index, int seedsLeft, int goodSpotsLeft) {
        if (seedsLeft == 0) {
            sb.append(field).append(System.lineSeparator());
            return;
        }
        if (index >= field.length) {
            return;
        }
        if (field[index] == '0') {
            generate(index + 1, seedsLeft, goodSpotsLeft);
            return;
        }
        field[index] = '.';
        generate(index + 2, seedsLeft - 1, goodSpotsLeft - 1);
        field[index] = '1';
        if (goodSpotsLeft > seedsLeft) {
            generate(index + 1, seedsLeft, goodSpotsLeft - 1);
        }
    }
}