package ExamPreparation.Exam4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class GroupPermutations {

    private static String[] elements;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        Map<Character, Integer> occurences = getOccurrences();
        initializeElements(occurences);
        generatePermutations(0);
        System.out.print(sb);
    }

    private static Map<Character, Integer> getOccurrences() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<Character, Integer> occurences = new LinkedHashMap<>();
        char[] input = reader.readLine().toCharArray();
        for (char ch : input) {
            occurences.putIfAbsent(ch, 0);
            occurences.put(ch, occurences.get(ch) + 1);
        }
        return occurences;
    }

    private static void initializeElements(Map<Character, Integer> occurences) {
        elements = new String[occurences.size()];
        int index = 0;
        for (Map.Entry<Character, Integer> pair : occurences.entrySet()) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < pair.getValue(); i++) {
                sb.append(pair.getKey());
            }
            elements[index++] = sb.toString();
        }
    }

    private static void generatePermutations(int index) {
        if (index == elements.length) {
            sb.append(String.join("", elements)).append(System.lineSeparator());
            return;
        }
        Set<String> used = new HashSet<>();
        for (int i = index; i < elements.length; i++) {
            if (!used.contains(elements[i])) {
                swap(index, i);
                generatePermutations(index + 1);
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