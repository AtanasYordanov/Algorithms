package DynamicPragramming1.Exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BinomialCoefficients {

    private static long[][] memo;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int k = Integer.parseInt(reader.readLine());
        memo = new long[n + 1][k + 1];
        System.out.println(findBinom(n, k));
    }

    private static long findBinom(int n, int k) {
        if (k == 0 || n == k) {
            return 1;
        }
        if (memo[n][k] != 0) {
            return memo[n][k];
        }
        long result = findBinom(n - 1, k - 1) + findBinom(n - 1, k);
        memo[n][k] = result;
        return result;
    }
}
