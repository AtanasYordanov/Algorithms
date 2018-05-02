package SolvingPracticalProblems.Exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ShopKeeper {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split(" ");
        Set<String> products = new HashSet<>();
        Collections.addAll(products, input);
        String[] orders = reader.readLine().split(" ");
        int changesCount = 0;
        if (!products.contains(orders[0])) {
            System.out.println("impossible");
            return;
        }
        Map<String, Deque<Integer>> ordersIndices = new HashMap<>();
        PriorityQueue<Integer> ordersQueue = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        for (int i = 0; i < orders.length; i++) {
            String order = orders[i];
            ordersIndices.putIfAbsent(order, new ArrayDeque<>());
            ordersIndices.get(orders[i]).add(i);
        }
        for (String product : products) {
            int index;
            if (!ordersIndices.containsKey(product)) {
                index = 12001 + Integer.parseInt(product);
                ordersIndices.put(product, new ArrayDeque<>());
            } else {
                index = ordersIndices.get(product).removeFirst();
            }
            ordersQueue.offer(index);
        }
        for (int i = 0; i < orders.length - 1; i++) {
            while (i < orders.length - 1 && products.contains(orders[i + 1])) {
                if (products.contains(orders[i])) {
                    updateQueue(orders, ordersIndices, ordersQueue, i);
                }
                i++;
            }
            if (products.contains(orders[i])) {
                updateQueue(orders, ordersIndices, ordersQueue, i);
            }
            if (i >= orders.length - 1) {
                break;
            }
            String order = orders[i + 1];
            String productToReplace;
            int index = ordersQueue.poll();
            if (index < 12001) {
                productToReplace = orders[index];
            } else {
                productToReplace = String.valueOf(index - 12001);
            }
            if (ordersIndices.get(order).isEmpty()) {
                index = 12001 + Integer.parseInt(productToReplace);
            } else {
                index = ordersIndices.get(order).removeFirst();
            }
            ordersQueue.offer(index);
            products.remove(productToReplace);
            products.add(order);
            changesCount++;
        }
        System.out.println(changesCount);
    }

    private static void updateQueue(String[] orders, Map<String, Deque<Integer>> ordersIndices,
                                    PriorityQueue<Integer> ordersQueue, int i) {
        if (ordersQueue.remove(i)) {
            String product = orders[i];
            int index;
            if (ordersIndices.get(product).isEmpty()) {
                index = 12001 + Integer.parseInt(product);
            } else {
                index = ordersIndices.get(product).removeFirst();
            }
            ordersQueue.offer(index);
        }
    }
}
