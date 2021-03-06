package CombinatorialAlgorithms.Exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CombinationsWithRepetition {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] elements = reader.readLine().split(" ");
        int slots = Integer.parseInt(reader.readLine());
        generateIterative(elements, slots);
    }

    private static void generateIterative(String[] elements, int slots) {
        int[] vector = new int[slots];
        for (int i = 0; i < slots; i++) {
            vector[i] = 0;
        }
        while (true) {
            int index = slots - 1;
            print(elements, vector);
            while (index >= 0 && vector[index] == elements.length - 1) {
                index--;
            }
            if (index < 0) {
                break;
            }
            vector[index]++;
            for (int i = index + 1; i < slots; i++) {
                vector[i] = vector[index];
            }
        }
    }

    private static void print(String[] elements, int[] vector) {
        for (int index : vector) {
            System.out.print(elements[index] + " ");
        }
        System.out.println();
    }
}
