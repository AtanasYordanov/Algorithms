package ExamPreparation.Exam6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Balls {

    private static StringBuilder sb = new StringBuilder();
    private static int[] output;
    private static int pockets;
    private static int balls;
    private static int capacity;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        pockets = Integer.parseInt(reader.readLine());
        balls = Integer.parseInt(reader.readLine());
        capacity = Integer.parseInt(reader.readLine());
        output = new int[pockets];
        generate(0, balls);
        System.out.print(sb);
    }

    private static void generate(int index, int ballsLeft) {
        if (index == pockets){
            if (ballsLeft == 0){
                appendVariation();
            }
            return;
        }
        for (int i = 1; i <= capacity; i++) {
            output[index] = i;
            generate(index + 1, ballsLeft - i);
        }
    }

    private static void appendVariation() {
        for (int num : output) {
            sb.append(num).append(", ");
        }
        sb.delete(sb.length()-2, sb.length());
        sb.append(System.lineSeparator());
    }
}
