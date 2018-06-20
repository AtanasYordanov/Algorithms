package DynamicPragramming1.Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class LongestIncreasingSubsequence {

    private static int[] nums;
    private static int[] memo;
    private static int[] next;
    private static int bestLength;
    private static int bestStart;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        nums = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        memo = new int[nums.length];
        next = new int[nums.length];
        solveIterative();
        //solveRecursive(0);
        //print(bestStart);
    }

    private static void print(int index) {
        while (next[index] != index) {
            System.out.print(nums[index] + " ");
            index = next[index];
        }
        System.out.println(nums[index]);
    }

    private static int solveRecursive(int index) {
        if (memo[index] != 0) {
            return memo[index];
        }
        int maxLength = 1;
        next[index] = index;
        for (int i = index + 1; i < nums.length; i++) {
            if (nums[index] < nums[i]) {
                int length = solveRecursive(i);
                if (length >= maxLength) {
                    maxLength = length + 1;
                    next[index] = i;
                }
            } else {
                solveRecursive(i);
            }
        }
        if (maxLength >= bestLength) {
            bestLength = maxLength;
            bestStart = index;
        }
        memo[index] = maxLength;
        return maxLength;
    }

    private static void solveIterative() {
        int[] len = new int[nums.length];
        int[] prev = new int[nums.length];
        len[0] = 1;
        prev[0] = -1;
        int maxLength = 0;
        int maxLengthIndex = 0;
        for (int p = 1; p < nums.length; p++) {
            int left = -1;
            for (int j = p - 1; j >= 0; j--) {
                if (nums[j] < nums[p] && len[p] <= len[j] + 1) {
                    len[p] = len[j] + 1;
                    left = j;
                    if (len[p] > maxLength) {
                        maxLength = len[p];
                        maxLengthIndex = p;
                    }
                }
            }
            if (len[p] == 0) {
                len[p] = 1;
            }
            prev[p] = left;
        }
        List<Integer> numList = new ArrayList<>();
        int index = maxLengthIndex;
        while (index > -1) {
            numList.add(nums[index]);
            index = prev[index];
        }
        for (int i = numList.size() - 1; i >= 0; i--) {
            System.out.print(numList.get(i) + " ");
        }
    }
}
