package Recursion.Exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CombinationsWithoutRepetition {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int k = Integer.parseInt(reader.readLine());
        generateCombinations(new int[k], n, 0, 1);
    }

    private static void generateCombinations(int[] output, int n, int index, int start) {
        if (index == output.length) {
            printOutput(output);
            return;
        }
        for (int i = start; i <= n; i++) {
            output[index] = i;
            generateCombinations(output, n, index + 1, i + 1);
        }
    }

    private static void printOutput(int[] output) {
        for (int number : output) {
            System.out.print(number + " ");
        }
        System.out.println();
    }
}
