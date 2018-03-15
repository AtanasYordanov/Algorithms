package SortingAndSearchingAlgorithms.Exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Needles {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] tokens = reader.readLine().split(" ");
        int c = Integer.parseInt(tokens[0]);
        tokens = reader.readLine().split(" ");
        int[] array = parseInput(tokens);
        tokens = reader.readLine().split(" ");
        int[] nums = parseInput(tokens);

        StringBuilder sb = new StringBuilder();
        for (int num : nums) {
            sb.append(modifiedBinarySearch(array, num, 0, c - 1)).append(" ");
        }
        System.out.println(sb);
    }

    private static int[] parseInput(String[] tokens) {
        int[] array = new int[tokens.length];
        for (int i = 0; i < array.length; i++) {
            array[i] = Integer.parseInt(tokens[i]);
        }
        return array;
    }

    private static int modifiedBinarySearch(int[] nums, int key, int lo, int hi) {
        if (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            int compNum = nums[mid];
            int index = mid;
            while (index > 0 && compNum == 0) {
                index--;
                compNum = nums[index];
            }
            if (key < compNum) {
                return modifiedBinarySearch(nums, key, lo, index);
            } else if (key > compNum) {
                return modifiedBinarySearch(nums, key, mid + 1, hi);
            } else {
                while (index >= 0 && (nums[index] == key || nums[index] == 0)) {
                    index--;
                }
                return index + 1;
            }
        }
        if (key <= nums[lo] || nums[lo] == 0) {
            while (lo >= 0 && (key <= nums[lo] || nums[lo] == 0)) {
                lo--;
            }
            return lo + 1;
        } else {
            return lo + 1;
        }
    }
}
