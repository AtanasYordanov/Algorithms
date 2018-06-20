package DynamicProgramming2.Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Knapsack {

    private static List<Item> items = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int capacity = Integer.parseInt(reader.readLine());
        for (String line = reader.readLine(); !line.equals("end"); line = reader.readLine()) {
            String[] tokens = line.split(" ");
            String name = tokens[0];
            int weight = Integer.parseInt(tokens[1]);
            int value = Integer.parseInt(tokens[2]);
            Item item = new Item(name, weight, value);
            items.add(item);
        }
        List<Item> takenItems = fillKnapsack(capacity);
        takenItems.sort(Comparator.comparing(Item::getName));

        int totalWeight = calcWeight(takenItems);
        int totalValue = calcValue(takenItems);

        System.out.printf("Total Weight: %d%n", totalWeight);
        System.out.printf("Total Value: %d%n", totalValue);
        for (Item takenItem : takenItems) {
            System.out.println(takenItem.getName());
        }
    }

    private static int calcValue(List<Item> items) {
        int sum = 0;
        for (Item item : items) {
            sum += item.getValue();
        }
        return sum;
    }

    private static int calcWeight(List<Item> items) {
        int sum = 0;
        for (Item item : items) {
            sum += item.getWeight();
        }
        return sum;
    }

    private static List<Item> fillKnapsack(int capacity) {
        int[][] values = new int[items.size() + 1][capacity + 1];
        boolean[][] isIncluded = new boolean[items.size() + 1][capacity + 1];

        for (int itemIndex = 0; itemIndex < items.size(); itemIndex++) {
            int row = itemIndex + 1;
            Item item = items.get(itemIndex);
            for (int currentCapacity = 1; currentCapacity <= capacity; currentCapacity++) {
                int excludedValue = values[row - 1][currentCapacity];
                int includedValue = 0;
                if (item.getWeight() <= currentCapacity) {
                    includedValue = item.getValue() + values[row - 1][currentCapacity - item.getWeight()];
                }
                if (includedValue > excludedValue) {
                    values[row][currentCapacity] = includedValue;
                    isIncluded[row][currentCapacity] = true;
                } else {
                    values[row][currentCapacity] = excludedValue;
                }
            }
        }
        List<Item> takenItems = new ArrayList<>();
        int tempCapacity = capacity;
        for (int matrixIndex = items.size(); matrixIndex >= 1; matrixIndex--) {
            if (isIncluded[matrixIndex][tempCapacity]) {
                Item item = items.get(matrixIndex - 1);
                takenItems.add(item);
                tempCapacity -= item.getWeight();
            }
        }
        return takenItems;
    }

    private static class Item {
        private String name;
        private int weight;
        private int value;

        Item(String name, int weight, int value) {
            this.name = name;
            this.weight = weight;
            this.value = value;
        }

        String getName() {
            return name;
        }

        int getWeight() {
            return weight;
        }

        int getValue() {
            return value;
        }
    }
}
