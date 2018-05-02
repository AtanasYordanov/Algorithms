package SolvingPracticalProblems.Exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Towns {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int[] towns = new int[n];
        for (int i = 0; i < n; i++) {
            towns[i] = Integer.parseInt(reader.readLine().split(" ")[0]);
        }
        int[] lis = findLIS(towns);
        reverseArray(towns);
        int[] lds = findLIS(towns);
        reverseArray(lds);
        int maxSequence = 0;
        for (int i = 0; i < n; i++) {
            int sequence = lis[i] + lds[i];
            if (sequence > maxSequence) {
                maxSequence = sequence;
            }
        }
        System.out.println(maxSequence - 1);
    }

    private static void reverseArray(int[] lis) {
        for (int i = 0; i < lis.length / 2; i++) {
            int temp = lis[i];
            lis[i] = lis[lis.length - 1 - i];
            lis[lis.length - 1 - i] = temp;
        }
    }

    private static int[] findLIS(int[] towns) {
        int[] len = new int[towns.length];
        len[0] = 1;
        int maxLength = 0;
        for (int p = 1; p < towns.length; p++) {
            for (int j = p - 1; j >= 0; j--) {
                if (towns[j] < towns[p] && len[p] <= len[j] + 1) {
                    len[p] = len[j] + 1;
                    if (len[p] > maxLength) {
                        maxLength = len[p];
                    }
                }
            }
            if (len[p] == 0) {
                len[p] = 1;
            }
        }
        return len;
    }
}
