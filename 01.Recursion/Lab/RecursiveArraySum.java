package Recursion.Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class RecursiveArraySum {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] nums = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(getArraySum(nums, 0));
    }

    public static int getArraySum(int[] nums, int fromIndex) {
        if (fromIndex == nums.length - 1) {
            return nums[fromIndex];
        }
        return nums[fromIndex] + getArraySum(nums, fromIndex + 1);
    }
}
