package GreedyAlgorithms.Exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EgyptianFractions {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split("/");
        int numerator = Integer.parseInt(input[0]);
        int denominator = Integer.parseInt(input[1]);

        double targetSum = numerator * 1.0 / denominator;
        if (targetSum >= 1){
            System.out.println("Error (fraction is equal to or greater than 1)");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%d/%d = ", numerator, denominator));
        double currentSum = 0d;
        int currentDenominator = 2;
        while (targetSum - currentSum > 0.00000001 ) {
            if (currentSum + 1.0 / currentDenominator <= targetSum) {
                currentSum += 1.0 / currentDenominator;
                sb.append(1).append("/").append(currentDenominator).append(" + ");
            }
            currentDenominator++;
        }
        sb.delete(sb.length() - 3, sb.length());
        System.out.println(sb);
    }
}
