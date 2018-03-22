package CombinatorialAlgorithms.Lab;

import java.util.HashSet;
import java.util.Set;

public class Permutations {

    private static String[] elements = new String[]{"A", "B", "C"};

    public static void main(String[] args) {
        generateWithoutRepetition(0);
    }

    private static void generateWithoutRepetition(int index) {
        if (index == elements.length) {
            System.out.println(String.join(" ", elements));
            return;
        }
        Set<String> used = new HashSet<>();
        for (int i = index; i < elements.length; i++) {
            if (!used.contains(elements[i])) {
                swap(index, i);
                generateWithoutRepetition(index + 1);
                swap(index, i);
                used.add(elements[i]);
            }
        }
    }

    private static void swap(int i, int j) {
        String temp = elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }
}
