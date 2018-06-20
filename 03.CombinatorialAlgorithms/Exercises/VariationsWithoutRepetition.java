package CombinatorialAlgorithms.Exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;
import java.util.Set;

public class VariationsWithoutRepetition {

    private static Set<String> used = new LinkedHashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] elements = reader.readLine().split(" ");
        int slots = Integer.parseInt(reader.readLine());
        generateRecursive(elements, slots, 0);
    }

    private static void generateRecursive(String[] elements, int slots, int index) {
        if (index >= slots) {
            printRecursive(used);
            return;
        }
        for (String element : elements) {
            if (!used.contains(element)) {
                used.add(element);
                generateRecursive(elements, slots, index + 1);
                used.remove(element);
            }
        }
    }

    private static void generateIterative(String[] elements, int slots) {
        int[] vector = new int[slots];
        while (true) {
            printIterative(elements, vector);
            int index = slots - 1;
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

    private static void printIterative(String[] elements, int[] vector) {
        StringBuilder sb = new StringBuilder();
        for (int index : vector) {
            sb.append(elements[index]).append(" ");
        }
        System.out.println(sb);
    }

    private static void printRecursive(Set<String> elements) {
        StringBuilder sb = new StringBuilder();
        for (String symbol : elements) {
            sb.append(symbol).append(" ");
        }
        System.out.println(sb);
    }
}
