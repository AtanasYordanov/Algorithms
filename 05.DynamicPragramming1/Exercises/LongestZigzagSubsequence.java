package DynamicPragramming1.Exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class LongestZigzagSubsequence {

    private static int[] numbers;
    private static int[] upMemo;
    private static int[] downMemo;
    private static int[] prevUp;
    private static int[] prevDown;
    private static int endIndex = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split(" ");
        fillNumbers(input);
        upMemo = new int[input.length];
        downMemo = new int[input.length];
        prevUp = new int[input.length];
        prevDown = new int[input.length];

        int maxCount = generateSequences();
        printSequence(maxCount, endIndex);
    }

    private static int generateSequences() {
        int maxCount = 0;
        for (int index = 0; index < numbers.length; index++) {
            upMemo[index] = downMemo[index] = 1;
            for (int j = 0; j < index; j++) {
                if (numbers[index] < numbers[j]) {
                    if (upMemo[j] >= downMemo[index]) {
                        downMemo[index] = upMemo[j] + 1;
                        prevUp[index] = j;
                    }
                } else if (numbers[index] > numbers[j]) {
                    if (downMemo[j] >= upMemo[index]) {
                        upMemo[index] = downMemo[j] + 1;
                        prevDown[index] = j;
                    }
                }
            }
            if (maxCount < downMemo[index]) {
                maxCount = downMemo[index];
                endIndex = index;
            }
            if (maxCount < upMemo[index]) {
                maxCount = upMemo[index];
                endIndex = index;
            }
        }
        return maxCount;
    }

    private static void printSequence(int count, int index) {
        Deque<String> stack = new ArrayDeque<>();
        boolean goDown = prevDown[index] > prevUp[index];
        while (count > 0) {
            stack.push(numbers[index] + " ");
            if (goDown) {
                index = prevDown[index];
                goDown = false;
            } else {
                index = prevUp[index];
                goDown = true;
            }
            count--;
        }
        while (!stack.isEmpty()) {
            System.out.print(stack.pop());
        }
    }

    private static void fillNumbers(String[] input) {
        numbers = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            numbers[i] = Integer.parseInt(input[i]);
        }
    }
}
