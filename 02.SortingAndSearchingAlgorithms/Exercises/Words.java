package SortingAndSearchingAlgorithms.Exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Words {

    private static int count;
    private static char[] elements;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        elements = reader.readLine().toCharArray();
        if (tryOptimize()) return;
        generateRecursive(0);
        System.out.println(count);
    }

    private static boolean tryOptimize() {
        Set<Character> uniqueElements = new HashSet<>();
        for (Character ch : elements) {
            uniqueElements.add(ch);
        }
        if (elements.length == uniqueElements.size()){
            System.out.println(factorial(elements.length));
            return true;
        }
        return false;
    }

    private static void generateRecursive(int index) {
        if (index == elements.length) {
            if (isValid()) {
                count++;
            }
            return;
        }
        Set<Character> used = new HashSet<>();
        for (int i = index; i < elements.length; i++) {
            if (!used.contains(elements[i])) {
                swap(index, i);
                generateRecursive(index + 1);
                swap(index, i);
                used.add(elements[i]);
            }
        }
    }

    private static int factorial(Integer num) {
        int result = 1;
        for (int i = 2; i <= num; i++) {
            result *= i;
        }
        return result;
    }

    private static boolean isValid() {
        for (int i = 1; i < elements.length; i++) {
            if (elements[i] == elements[i - 1]) {
                return false;
            }
        }
        return true;
    }

    private static void swap(int i, int j) {
        char temp = elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }
}