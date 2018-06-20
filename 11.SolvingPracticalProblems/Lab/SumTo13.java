package SolvingPracticalProblems.Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SumTo13 {

    private static int[] nums;
    private static boolean canBeSummed = false;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        nums = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::valueOf).toArray();

        trySum(0, 0);
        System.out.println(canBeSummed ? "Yes" : "No");
    }

    private static void trySum(int index, int sum) {
        if (index == 3) {
            if (sum == 13) {
                canBeSummed = true;
            }
            return;
        }
        int tempSum = sum + nums[index];
        trySum(index + 1, tempSum);
        tempSum = sum - nums[index];
        trySum(index + 1, tempSum);
    }
}
