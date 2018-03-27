package DynamicPragramming1.Exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DividingPresents {

    private static int[] presents;
    private static Map<Integer, List<Integer>> subsets = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int totalSum = parseInput(reader);
        generateAllSubsets();
        int middle = totalSum / 2;
        int closestSum = getClosestSum(middle);
        printResult(totalSum, closestSum);
    }

    private static void printResult(int totalSum, int closestSum) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Difference: %d%n", (totalSum - closestSum) - closestSum));
        Deque<Integer> stack = new ArrayDeque<>();
        addPresents(stack, closestSum);
        int bobTotalValue = totalSum - closestSum;
        sb.append(String.format("Alan:%d Bob:%d%n", closestSum, bobTotalValue));
        sb.append("Alan takes: ");
        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }
        sb.append(System.lineSeparator());
        sb.append("Bob takes the rest.");
        System.out.println(sb);
    }

    private static int getClosestSum(int middle) {
        int closestSum = Integer.MAX_VALUE;
        for (Integer integer : subsets.keySet()) {
            if (integer <= middle) {
                closestSum = integer;
            }
        }
        return closestSum;
    }

    private static void addPresents(Deque<Integer> stack, int key) {
        for (Integer integer : subsets.get(key)) {
            if (subsets.get(integer).size() > 1) {
                addPresents(stack, integer);
            } else {
                stack.push(integer);
            }
        }
    }

    private static void generateAllSubsets() {
        for (int present : presents) {
            Map<Integer, List<Integer>> tempMap = new TreeMap<>();
            for (Integer integer : subsets.keySet()) {
                int key = integer + present;
                if (subsets.containsKey(key)) {
                    continue;
                }
                tempMap.put(key, new ArrayList<>());
                tempMap.get(key).add(integer);
                tempMap.get(key).add(present);
            }
            for (Map.Entry<Integer, List<Integer>> pair : tempMap.entrySet()) {
                if (!subsets.containsKey(pair.getKey())) {
                    subsets.put(pair.getKey(), new ArrayList<>());
                    for (Integer integer : pair.getValue()) {
                        subsets.get(pair.getKey()).add(integer);
                    }
                }
            }
            if (!tempMap.containsKey(present)) {
                subsets.put(present, new ArrayList<>());
                subsets.get(present).add(present);
            }
        }
    }

    private static int parseInput(BufferedReader reader) throws IOException {
        int sum = 0;
        String[] input = reader.readLine().split(" ");
        presents = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            int number = Integer.parseInt(input[i]);
            presents[i] = number;
            sum += number;
        }
        return sum;
    }
}
