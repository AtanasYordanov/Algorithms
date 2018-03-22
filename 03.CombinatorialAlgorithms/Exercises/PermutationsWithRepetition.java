package CombinatorialAlgorithms.Exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class PermutationsWithRepetition {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] elements = reader.readLine().split(" ");
        generateRecursive(elements, 0);
    }

    private static void generateIterative(String[] elements) {
        int length = elements.length;
        int[] controlArray = new int[length + 1];
        for (int i = 0; i < controlArray.length; i++) {
            controlArray[i] = i;
        }
        System.out.println(String.join(" ", elements));
        int i = 1;
        while (i < length) {
            controlArray[i]--;
            int j;
            if (i % 2 == 1) {
                j = controlArray[i];
            } else {
                j = 0;
            }
            swap(elements, j, i);
            System.out.println(String.join(" ", elements));
            i = 1;
            while (controlArray[i] == 0) {
                controlArray[i] = i;
                i++;
            }
        }
    }

    private static void generateRecursive(String[] elements, int index) {
        if (index == elements.length) {
            System.out.println(String.join(" ", elements));
            return;
        }
        Set<String> used = new HashSet<>();
        for (int i = index; i < elements.length; i++) {
            if (!used.contains(elements[i])) {
                swap(elements, index, i);
                generateRecursive(elements, index + 1);
                swap(elements, index, i);
                used.add(elements[i]);
            }
        }
    }

    private static void swap(String[] elements, int i, int j) {
        String temp = elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }
}
