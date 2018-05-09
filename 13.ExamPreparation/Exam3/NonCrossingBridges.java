package ExamPreparation.Exam3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NonCrossingBridges {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] sequence = reader.readLine().split(" ");
        boolean[] used = new boolean[sequence.length];
        int bridgesFound = markBridges(sequence, used);
        printResult(sequence, used, bridgesFound);
    }

    private static int markBridges(String[] sequence, boolean[] used) {
        int bridgesFound = 0;
        int lastBridgeIndex = 0;
        for (int i = 1; i < sequence.length; i++) {
            for (int j = lastBridgeIndex; j < i; j++) {
                if (sequence[i].equals(sequence[j])) {
                    bridgesFound++;
                    used[i] = true;
                    used[j] = true;
                    lastBridgeIndex = i;
                }
            }
        }
        return bridgesFound;
    }

    private static void printResult(String[] sequence, boolean[] used, int bridgesFound) {
        StringBuilder sb = new StringBuilder();
        if (bridgesFound == 0) {
            sb.append("No bridges found");
        } else if (bridgesFound == 1) {
            sb.append(bridgesFound).append(" bridge found");
        } else {
            sb.append(bridgesFound).append(" bridges found");
        }
        sb.append(System.lineSeparator());
        for (int i = 0; i < sequence.length; i++) {
            if (used[i]) {
                sb.append(sequence[i]).append(" ");
            } else {
                sb.append("X ");
            }
        }
        System.out.println(sb);
    }
}
