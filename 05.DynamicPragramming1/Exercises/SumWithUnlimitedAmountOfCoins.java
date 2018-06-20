package DynamicPragramming1.Exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SumWithUnlimitedAmountOfCoins {

    private static int[] coins;
    private static int count = 0;
    private static int targetSum;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        coins = parseInput(reader);
        targetSum = Integer.parseInt(reader.readLine());
        generateCombinations(0, coins.length - 1);
        System.out.println(count);
    }

    private static void generateCombinations(int sum, int index) {
        if (sum == targetSum) {
            count++;
            return;
        }
        if (sum > targetSum || index < 0) {
            return;
        }
        for (int i = 0; i <= targetSum / coins[index]; i++) {
            generateCombinations(sum + (coins[index] * i), index - 1);
        }
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
