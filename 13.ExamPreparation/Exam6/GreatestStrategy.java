package ExamPreparation.Exam6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GreatestStrategy {

    private static Set<Integer>[] graph;
    private static int maxValue = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] tokens = reader.readLine().split(" ");
        int areas = Integer.parseInt(tokens[0]);
        int connections = Integer.parseInt(tokens[1]);
        int start = Integer.parseInt(tokens[2]);
        initializeGraph(reader, areas, connections);
        DFS(start);
        System.out.println(maxValue);
    }

    private static void initializeGraph(BufferedReader reader, int areas, int connections) throws IOException {
        String[] tokens;
        graph = new HashSet[areas + 1];
        for (int i = 1; i <= areas; i++) {
            graph[i] = new HashSet<>();
        }
        for (int i = 0; i < connections; i++) {
            tokens = reader.readLine().split(" ");
            int to = Integer.parseInt(tokens[0]);
            int from = Integer.parseInt(tokens[1]);
            graph[from].add(to);
        }
    }

    private static int DFS(int node) {
        int allChildren = 1;
        List<Integer> childrenToRemove = new ArrayList<>();
        for (Integer child : graph[node]) {
            int children = DFS(child);
            allChildren += children;
            if (children == 0) {
                childrenToRemove.add(child);
            }
        }
        for (Integer child : childrenToRemove) {
            graph[node].remove(child);
        }
        if (allChildren % 2 == 0) {
            int value = findValueDFS(node);
            if (value > maxValue) {
                maxValue = value;
            }
            allChildren = 0;
        }
        return allChildren;
    }

    private static int findValueDFS(int node) {
        int value = node;
        for (Integer child : graph[node]) {
            value += findValueDFS(child);
        }
        return value;
    }
}
