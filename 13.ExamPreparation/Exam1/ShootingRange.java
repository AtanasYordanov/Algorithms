package ExamPreparation.Exam1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ShootingRange {

    private static int targetSum;
    private static int[] targets;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] tokens = reader.readLine().split(" ");
        targets = new int[tokens.length];
        for (int i = 0; i < targets.length; i++) {
            targets[i] = Integer.parseInt(tokens[i]);
        }
        targetSum = Integer.parseInt(reader.readLine());
        generateSums(0);
    }

    private static void generateSums(int index) {
        int sum = getSum(index);
        if (sum == targetSum) {
            printSequence(index);
            return;
        }
        if (index == targets.length || sum > targetSum) {
            return;
        }
        Set<Integer> used = new HashSet<>();
        for (int i = index; i < targets.length; i++) {
            if (!used.contains(targets[i])) {
                swap(index, i);
                generateSums(index + 1);
                swap(index, i);
                used.add(targets[i]);
            }
        }
    }

    private static void printSequence(int index) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < index; i++) {
            sb.append(targets[i]).append(" ");
        }
        System.out.println(sb.toString().trim());
    }

    private static int getSum(int index) {
        int sum = 0;
        int multiplier = 1;
        for (int i = 0; i < index; i++) {
            sum += targets[i] * multiplier++;
        }
        return sum;
    }

    private static void swap(int i, int j) {
        int temp = targets[i];
        targets[i] = targets[j];
        targets[j] = temp;
    }
}
