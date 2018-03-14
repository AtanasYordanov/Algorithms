package Recursion.Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class GenerationCombinations {
    private static int[] nums;
    private static int[] vector;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        nums = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = Integer.parseInt(reader.readLine());
        vector = new int[n];
        generateCombinations(0, 0);
    }

    private static void generateCombinations(int index, int start) {
        if (index == vector.length) {
            printVector(vector);
            return;
        }
        for (int i = start; i < nums.length; i++) {
            vector[index] = nums[i];
            generateCombinations(index + 1, i + 1);
        }
    }

    private static void printVector(int[] vector) {
        for (int i = 0; i < vector.length; i++) {
            System.out.print(vector[i] + " ");
        }
        System.out.println();
    }
}
