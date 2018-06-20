package SolvingPracticalProblems2.Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Guitar {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] tokens = reader.readLine().split(", ");
        int[] intervals = new int[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            intervals[i] = Integer.parseInt(tokens[i]);
        }
        int initialVolume = Integer.parseInt(reader.readLine());
        int max = Integer.parseInt(reader.readLine());
        //solveSubsetSum(intervals, initialVolume, max);
        System.out.println(solveDP(intervals, initialVolume, max));
    }

    private static int solveDP(int[] intervals, int initialVolume, int maxVolumeAllowed) {
        boolean[][] matrix = new boolean[intervals.length + 1][maxVolumeAllowed + 1];
        matrix[0][initialVolume] = true;
        for (int row = 1; row < matrix.length; row++) {
            int interval = intervals[row - 1];
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row - 1][col]) {
                    if (col - interval >= 0) {
                        matrix[row][col - interval] = true;
                    }
                    if (col + interval <= maxVolumeAllowed) {
                        matrix[row][col + interval] = true;
                    }
                }
            }
        }
        int bestSum = -1;
        for (int col = matrix[0].length - 1; col >= 0; col--) {
            if (matrix[intervals.length][col]) {
                bestSum = col;
                break;
            }
        }
        return bestSum;
    }

    private static void solveSubsetSum(int[] intervals, int initialVolume, int max) {
        Set<Integer> previousStepSums = new HashSet<>();
        previousStepSums.add(initialVolume);
        for (int interval : intervals) {
            Set<Integer> aux = new HashSet<>();
            boolean possible = false;
            for (Integer sum : previousStepSums) {
                int sum1 = sum + interval;
                int sum2 = sum - interval;
                if (sum1 <= max) {
                    aux.add(sum1);
                    possible = true;
                }
                if (sum2 >= 0) {
                    aux.add(sum2);
                    possible = true;
                }
            }
            previousStepSums = aux;
            if (!possible) {
                break;
            }
        }
        int bestSum = -1;
        for (Integer sum : previousStepSums) {
            if (sum > bestSum) {
                bestSum = sum;
            }
        }
        System.out.println(bestSum);
    }
}
