package ExamPreparation.Exam3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class SequencesOfLimitedSum {

    private static int maxSum;
    private static Deque<Integer> sequence = new ArrayDeque<>();
    private static StringBuilder output = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        maxSum = Integer.parseInt(reader.readLine());
        generate(0);
        System.out.print(output);
    }

    private static void generate(int sum) {
        for (int i = 1; i <= maxSum; i++) {
            if (sum + i > maxSum) {
                break;
            }
            sequence.addLast(i);
            appendSequence();
            generate(sum + i);
            sequence.removeLast();
        }
    }

    private static void appendSequence() {
        for (Integer num : sequence) {
            output.append(num).append(" ");
        }
        output.append(System.lineSeparator());
    }
}
