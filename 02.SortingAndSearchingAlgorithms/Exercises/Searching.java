package SortingAndSearchingAlgorithms.Exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Searching {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> numbers = parseInput(reader);
        int num = Integer.parseInt(reader.readLine());
        System.out.println(binarySearch(numbers, num, 0, numbers.size() - 1));
    }

    private static List<Integer> parseInput(BufferedReader reader) throws IOException {
        String[] input = reader.readLine().split(" ");
        List<Integer> numbers = new ArrayList<>();
        for (String str : input) {
            numbers.add(Integer.parseInt(str));
        }
        return numbers;
    }

    private static int binarySearch(List<Integer> nums, int key, int lo, int hi) {
        if (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key < nums.get(mid)) {
                return binarySearch(nums, key, lo, mid);
            } else if (key > nums.get(mid)) {
                return binarySearch(nums, key, mid + 1, hi);
            } else {
                return mid;
            }
        }
        return -1;
    }
}
