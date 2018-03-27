package DynamicPragramming1.Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class RodCutting {

    private static int[] prices;
    private static int[] memo;
    private static int[] index;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        prices = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        memo = new int[prices.length];
        index = new int[prices.length];
        int length = Integer.parseInt(reader.readLine());
        printSolution(length);
    }

    private static int cutRod(int length) {
        if (memo[length] != 0) {
            return memo[length];
        }
        if (length == 0) {
            return 0;
        }
        int bestPrice = prices[length];
        int wholePart = length;
        for (int i = 1; i < length; i++) {
            int price = prices[i] + cutRod(length - i);
            if (price > bestPrice) {
                wholePart = i;
                bestPrice = price;
            }
        }
        index[length] = wholePart;
        memo[length] = bestPrice;
        return bestPrice;
    }

    private static void printSolution(int length) {
        System.out.println(cutRod(length));
        while (length != 0) {
            System.out.print(index[length] + " ");
            length -= index[length];
        }
    }
}
