package DynamicPragramming1.Exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class SumWithLimitedAmountOfCoins {

    private static int[] coins;
    private static int count;
    private static int neededSum;
    private static int[] vector;
    private static Set<String> combinations = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        coins = parseInput(reader);
        neededSum = Integer.parseInt(reader.readLine());
        for (int i = 1; i <= coins.length; i++) {
            vector = new int[i];
            generateCombinations(0, 0);
        }
        System.out.println(count);
    }

    private static void generateCombinations(int index, int start) {
        checkSum(index);
        if (index >= vector.length) {
            return;
        }
        for (int i = start; i < coins.length; i++) {
            vector[index] = i;
            generateCombinations(index + 1, i + 1);
        }
    }

    private static void checkSum(int endIndex) {
        int sum = 0;
        for (int i = 0; i < endIndex; i++) {
            sum += coins[vector[i]];
        }
        String combination = buildString(endIndex);
        if (sum == neededSum && !combinations.contains(combination)) {
            count++;
            combinations.add(combination);
        }
    }

    private static String buildString(int endIndex) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < endIndex; i++) {
            sb.append(coins[vector[i]]).append(" ");
        }
        return sb.toString();
    }

    private static int[] parseInput(BufferedReader reader) throws IOException {
        String[] input = reader.readLine().split(" ");
        int[] coins = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            coins[i] = Integer.parseInt(input[i]);
        }
        return coins;
    }
}
