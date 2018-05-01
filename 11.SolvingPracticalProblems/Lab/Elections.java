package SolvingPracticalProblems.Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Elections {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(reader.readLine());
        int n = Integer.parseInt(reader.readLine());
        int[] numbers = new int[n];
        BigInteger maxSum = BigInteger.ZERO;
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(reader.readLine());
            maxSum = maxSum.add(BigInteger.valueOf(numbers[i]));
        }
        BigInteger[] sums = new BigInteger[maxSum.intValue() + 1];
        sums[0] = BigInteger.ONE;
        for (int number : numbers) {
            for (int i = sums.length - 1; i >= 0; i--) {
                if (sums[i] != null && sums[i].intValue() != 0) {
                    BigInteger current = sums[i + number] == null ? BigInteger.ZERO : sums[i + number];
                    sums[i + number] = current.add(sums[i]);
                }
            }
        }
        BigInteger count = BigInteger.ZERO;
        for (int i = k; i < sums.length; i++) {
            BigInteger current = sums[i] == null ? BigInteger.ZERO : sums[i];
            count = count.add(current);
        }
        System.out.println(count);
    }
}
