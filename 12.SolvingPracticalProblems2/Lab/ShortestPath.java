package ProblemSolvingMethodology2.Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ShortestPath {

    private static char[] path;
    private static char[] str;
    private static int count = 0;
    private static List<Integer> indices = new ArrayList<>();
    private static StringBuilder output = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        path = reader.readLine().toCharArray();
        for (int i = 0; i < path.length; i++) {
            if (path[i] == '*') {
                indices.add(i);
            }
        }
        str = new char[indices.size()];
        generatePaths(0);
        System.out.println(count);
        System.out.println(output);
    }

    private static void generatePaths(int index) {
        if (index == indices.size()) {
            count++;
            appendPath();
            return;
        }
        str[index] = 'L';
        generatePaths(index + 1);
        str[index] = 'R';
        generatePaths(index + 1);
        str[index] = 'S';
        generatePaths(index + 1);
    }

    private static void appendPath() {
        for (int i = 0; i < indices.size(); i++) {
            int index = indices.get(i);
            path[index] = str[i];
        }
        output.append(path).append(System.lineSeparator());
    }
}
