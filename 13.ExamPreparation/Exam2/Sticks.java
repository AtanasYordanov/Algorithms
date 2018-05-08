package ExamPreparation.Exam2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Sticks {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<Integer, List<Integer>> sticksUnder = new HashMap<>();
        int numberOfSticks = Integer.parseInt(reader.readLine());
        int[] sticksOnTop = new int[numberOfSticks];
        int placings = Integer.parseInt(reader.readLine());
        for (int i = 0; i < numberOfSticks; i++) {
            sticksUnder.put(i, new ArrayList<>());
        }
        for (int i = 0; i < placings; i++) {
            String[] tokens = reader.readLine().split(" ");
            int stick1 = Integer.parseInt(tokens[0]);
            int stick2 = Integer.parseInt(tokens[1]);
            sticksOnTop[stick2]++;
            sticksUnder.get(stick1).add(stick2);
        }
        StringBuilder sb = new StringBuilder();
        int removedSticksCount = 0;
        while (removedSticksCount < numberOfSticks) {
            int stick = -1;
            for (int i = 0; i < sticksOnTop.length; i++) {
                if (sticksOnTop[i] == 0 && i > stick) {
                    stick = i;
                }
            }
            if (stick == -1) {
                System.out.println("Cannot lift all sticks");
                break;
            }
            sticksOnTop[stick] = -1;
            sb.append(stick).append(" ");
            for (Integer child : sticksUnder.get(stick)) {
                sticksOnTop[child]--;
            }
            removedSticksCount++;
        }
        System.out.println(sb);
    }
}
