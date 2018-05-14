package Exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class XelNaga {

    private static Map<Integer, Integer> uniqueCount = new TreeMap<>();
    private static int totalCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] tokens = reader.readLine().split(" ");
        for (int i = 0; i < tokens.length - 1; i++) {
            int num = Integer.parseInt(tokens[i]);
            if (num == 0) {
                totalCount++;
                continue;
            }
            uniqueCount.putIfAbsent(num, 0);
            uniqueCount.put(num, uniqueCount.get(num) + 1);
        }
        for (Map.Entry<Integer, Integer> pair : uniqueCount.entrySet()) {
            int n = pair.getValue() / (pair.getKey() + 1);
            if (pair.getValue() % (pair.getKey() + 1) != 0) {
                n++;
            }
            totalCount += (pair.getKey() + 1) * n;
        }
        System.out.println(totalCount);
    }
}
