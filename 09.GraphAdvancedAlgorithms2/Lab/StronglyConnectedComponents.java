package GraphAdvancedAlgorithms2.Lab;

import java.util.*;

public class StronglyConnectedComponents {

    private static int size;
    private static boolean[] visited;
    private static List<List<Integer>> stronglyConnectedComponents;
    private static List<Integer>[] graph;
    private static Deque<Integer> stack;
    private static List<Integer>[] reversedGraph;

    public static List<List<Integer>> findStronglyConnectedComponents(List<Integer>[] targetGraph) {
        stronglyConnectedComponents = new ArrayList<>();
        graph = targetGraph;
        size = graph.length;
        visited = new boolean[size];
        stack = new ArrayDeque<>();
        for (int i = 0; i < size; i++) {
            if (!visited[i]) {
                DFS(i);
            }
        }
        visited = new boolean[size];
        reversedGraph = buildReversedGraph();
        while (!stack.isEmpty()) {
            int vertex = stack.pop();
            if (!visited[vertex]) {
                stronglyConnectedComponents.add(new ArrayList<>());
                reverseDFS(vertex);
            }
        }
        return stronglyConnectedComponents;
    }

    @SuppressWarnings("unchecked")
    private static List<Integer>[] buildReversedGraph() {
        List<Integer>[] reversedGraph = new ArrayList[size];
        for (int i = 0; i < size; i++) {
            reversedGraph[i] = new ArrayList<>();
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < graph[i].size(); j++) {
                reversedGraph[graph[i].get(j)].add(i);
            }
        }
        return reversedGraph;
    }

    private static void reverseDFS(int vertex) {
        if (!visited[vertex]) {
            visited[vertex] = true;
            stronglyConnectedComponents.get(stronglyConnectedComponents.size() - 1).add(vertex);
            for (Integer child : reversedGraph[vertex]) {
                reverseDFS(child);
            }
        }
    }

    private static void DFS(int vertex) {
        if (!visited[vertex]) {
            visited[vertex] = true;
            for (Integer child : graph[vertex]) {
                DFS(child);
            }
            stack.push(vertex);
        }
    }
}
