package Recursion.Exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ReverseArray {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] numbers = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        reverse(numbers, 0);
        for (int number : numbers) {
            System.out.print(number + " ");
        }
    }

    private static void reverse(int[] numbers, int index) {
        if (index == numbers.length / 2) {
            return;
        }
        int temp = numbers[index];
        numbers[index] = numbers[numbers.length - 1 - index];
        numbers[numbers.length - 1 - index] = temp;
        reverse(numbers, index + 1);
    }
}
