package GreedyAlgorithms.Exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FractionalKnapsackProblem {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int capacity = Integer.parseInt(reader.readLine().substring(10));
        int n = Integer.parseInt(reader.readLine().substring(7));
        List<Item> items = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] tokens = reader.readLine().split(" -> ");
            double price = Double.parseDouble(tokens[0]);
            double weight = Double.parseDouble(tokens[1]);
            items.add(new Item(price, weight));
        }
        items.sort(Item::compareTo);
        double totalPrice = 0;
        StringBuilder sb = new StringBuilder();
        while (!items.isEmpty()) {
            Item item = items.remove(items.size() - 1);
            if (capacity >= item.getWeight()) {
                capacity -= item.getWeight();
                sb.append(String.format("Take 100%% of item with price %.2f and weight %.2f%n"
                        , item.getPrice(), item.getWeight()));
                totalPrice += item.getPrice();
            } else {
                double percentage = capacity * 100.0 / item.getWeight();
                sb.append(String.format("Take %.2f%% of item with price %.2f and weight %.2f%n"
                        , percentage, item.getPrice(), item.getWeight()));
                totalPrice += item.getPrice() * (percentage / 100);
                break;
            }
        }
        sb.append(String.format("Total price: %.2f%n", totalPrice));
        System.out.println(sb);
    }
}

class Item implements Comparable<Item> {
    private double price;
    private double weight;
    private double priceToWeight;

    public Item(double price, double weight) {
        this.price = price;
        this.weight = weight;
        this.priceToWeight = this.price / this.weight;
    }

    public double getWeight() {
        return weight;
    }

    public double getPrice() {
        return price;
    }

    public double getPriceToWeight() {
        return priceToWeight;
    }

    @Override
    public int compareTo(Item other) {
        if (this.priceToWeight > other.priceToWeight) {
            return 1;
        } else if (this.priceToWeight < other.priceToWeight) {
            return -1;
        } else {
            return 0;
        }
    }
}