package DynamicProgramming2.Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SubsetSum {

    private static int[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int targetSum = Integer.parseInt(reader.readLine());
        nums = parseInput(reader);
        Map<Integer, Integer> possibleSums = calcPossibleSums();
        if (possibleSums.containsKey(targetSum)) {
            for (Integer number : findSubset(targetSum, possibleSums)) {
                System.out.print(number + " ");
            }
        }
    }

    private static Map<Integer, Integer> calcPossibleSums() {
        Map<Integer, Integer> possibleSums = new HashMap<>();
        possibleSums.put(0, 0);
        for (int i = 0; i < nums.length; i++) {
            Map<Integer, Integer> tempSet = new HashMap<>();
            for (Map.Entry<Integer, Integer> sum : possibleSums.entrySet()) {
                int currentSum = sum.getKey() + nums[i];
                if (!possibleSums.containsKey(currentSum)) {
                    tempSet.put(currentSum, nums[i]);
                }
            }
            for (Map.Entry<Integer, Integer> tempPair : tempSet.entrySet()) {
                possibleSums.put(tempPair.getKey(), tempPair.getValue());
            }
        }
        return possibleSums;
    }

    private static Iterable<Integer> findSubset(int targetSum, Map<Integer, Integer> possibleSums) {
        List<Integer> elements = new ArrayList<>();
        while (targetSum != 0) {
            if (possibleSums.containsKey(targetSum)) {
                int currentValue = targetSum - possibleSums.get(targetSum);
                elements.add(possibleSums.get(targetSum));
                targetSum = currentValue;
            }
        }
        return elements;
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
