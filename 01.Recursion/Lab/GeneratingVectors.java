package Recursion.Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GeneratingVectors {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int[] vector = new int[n];
        generateVectors(0, vector);
    }

    private static void generateVectors(int index, int[] vector) {
        if (index == vector.length) {
            printVector(vector);
            return;
        }
        for (int i = 0; i <= 1; i++) {
            vector[index] = i;
            generateVectors(index + 1, vector);
        }
    }

    private static void printVector(int[] vector) {
        for (int i = 0; i < vector.length; i++) {
            System.out.print(vector[i]);
        }
        System.out.println();
    }
}
