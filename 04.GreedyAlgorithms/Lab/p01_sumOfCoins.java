package GreedyAlgorithms.Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p01_sumOfCoins {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] elements = reader.readLine().substring(7).split(", ");
        int[] coins = new int[elements.length];
        for (int i = 0; i < coins.length; i++) {
            coins[i] = Integer.parseInt(elements[i]);
        }
        int targetSum = Integer.parseInt(reader.readLine().substring(5));
        Map<Integer, Integer> usedCoins = chooseCoins(coins, targetSum);
        System.out.printf("Number of coins to take: %d%n", usedCoins.values().stream()
                .mapToInt(Integer::valueOf).sum());
        for (Map.Entry<Integer, Integer> pair : usedCoins.entrySet()) {
            System.out.printf("%d coin(s) with value %d%n", pair.getValue(), pair.getKey());
        }
    }

    public static Map<Integer, Integer> chooseCoins(int[] elements, int targetSum) {
        SortedSet<Integer> coins = new TreeSet<>(Comparator.reverseOrder());
        for (int element : elements) {
            coins.add(element);
        }
        Map<Integer, Integer> usedCoins = new TreeMap<>(Comparator.reverseOrder());
        for (Integer coin : coins) {
            int count = targetSum / coin;
            if (count > 0) {
                usedCoins.putIfAbsent(coin, 0);
                usedCoins.put(coin, usedCoins.get(coin) + count);
                targetSum -= coin * count;
            }
        }
        if (targetSum != 0) {
            throw new IllegalArgumentException();
        }
        return usedCoins;
    }
}
