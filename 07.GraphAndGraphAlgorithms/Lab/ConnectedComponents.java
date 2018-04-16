package GraphAndGraphAlgorithms.Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ConnectedComponents {
    public static void main(String[] args) throws IOException {
        List<List<Integer>> graph = readGraph();
        List<Deque<Integer>> connectedComponents = getConnectedComponents(graph);
        for (Deque<Integer> connectedComponent : connectedComponents) {
            System.out.print("Connected component: ");
            while(!connectedComponent.isEmpty()) {
                System.out.print(connectedComponent.poll() + " ");
            }
            System.out.println();
        }
    }

    private static List<List<Integer>> readGraph() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<List<Integer>> graph = new ArrayList<>();
        int n = Integer.parseInt(reader.readLine());
        for (int i = 0; i < n; i++) {
            List<Integer> connectedComponents = new ArrayList<>();
            String line = reader.readLine();
            if (line.equals("")) {
                graph.add(connectedComponents);
                continue;
            }
            String[] nodes = line.split(" ");
            for (String node : nodes) {
                connectedComponents.add(Integer.parseInt(node));
            }
            graph.add(connectedComponents);
        }
        return graph;
    }

    public static List<Deque<Integer>> getConnectedComponents(List<List<Integer>> graph) {
        List<Deque<Integer>> connectedComponents = new ArrayList<>();
        boolean[] visited = new boolean[graph.size()];
        for (int i = 0; i < graph.size(); i++) {
            Deque<Integer> connectedComponent = new ArrayDeque<>();
            if (!visited[i]) {
                visited[i] = true;
                DFS(graph, visited,connectedComponent, graph.get(i));
                connectedComponent.offer(i);
            }
            if (!connectedComponent.isEmpty()) {
                connectedComponents.add(connectedComponent);
            }
        }
        return connectedComponents;
    }

    private static void DFS(List<List<Integer>> graph, boolean[] visited, Deque<Integer> connectedComponent, List<Integer> vertex) {
        for (Integer node : vertex) {
            if (!visited[node]) {
                visited[node] = true;
                DFS(graph, visited, connectedComponent, graph.get(node));
                connectedComponent.offer(node);
            }
        }
    }
}
