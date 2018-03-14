package Recursion.Exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NestedLoopsToRecursion {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        generateCombinations(new int[n], 0);
    }

    private static void generateCombinations(int[] output, int index) {
        if (index == output.length) {
            printOutput(output);
            return;
        }
        for (int i = 1; i <= output.length; i++) {
            output[index] = i;
            generateCombinations(output, index + 1);
        }
    }

    private static void printOutput(int[] output) {
        for (int number : output) {
            System.out.print(number + " ");
        }
        System.out.println();
    }
}
