package CombinatorialAlgorithms.Exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class VariationsWithRepetition {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] elements = reader.readLine().split(" ");
        int slots = Integer.parseInt(reader.readLine());
        iterative(elements, slots);
    }

    private static void iterative(String[] elements, int slots) {
        int k = slots;
        int[] vector = new int[k];
        while (true) {
            print(elements, vector);
            int index = k - 1;
            while (index >= 0 && vector[index] == elements.length - 1) {
                index--;
            }
            if (index < 0) {
                break;
            }
            vector[index]++;
            for (int i = index + 1; i < vector.length; i++) {
                vector[i] = 0;
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
