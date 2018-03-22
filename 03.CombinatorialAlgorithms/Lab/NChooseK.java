package CombinatorialAlgorithms.Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NChooseK {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int k = Integer.parseInt(reader.readLine());
        System.out.println(binom(n, k));
    }

    private static long binom(int n, int k) {
        if (k > n) {
            return 0;
        }
        if (k == 0) {
            return 1;
        }
        if (k == n) {
            return 1;
        }
        return binom(n - 1, k - 1) + binom(n - 1, k);
    }
}
