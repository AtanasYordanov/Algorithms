package ProblemSolvingMethodology.Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Blocks {

    private static Set<String> variations = new TreeSet<>();
    private static Set<String> rotations = new HashSet<>();
    private static char[] elements;
    private static char[] block = new char[4];
    private static Set<Character> used = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        elements = new char[n];
        for (int i = 0; i < n; i++) {
            elements[i] = (char)(i + 'A');
        }
        generateRecursive(0);
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Number of blocks: %d%n", variations.size()));
        for (String variation : variations) {
            sb.append(variation).append(System.lineSeparator());
        }
        System.out.print(sb);
    }
    private static void generateRecursive(int index) {
        if (index >= 4) {
            if (!rotations.contains(String.valueOf(block))) {
                variations.add(String.valueOf(block));
                addAllRotations();
            }
            return;
        }
        for (char element : elements) {
            if (!used.contains(element)) {
                used.add(element);
                block[index] = element;
                generateRecursive(index + 1);
                used.remove(element);
            }
        }
    }

    private static void addAllRotations() {
        for (int i = 0; i < 4; i++) {
            char temp = block[0];
            block[0] = block[1];
            block[1] = block[2];
            block[2] = block[3];
            block[3] = temp;
            rotations.add(String.valueOf(block));
        }
    }
}
