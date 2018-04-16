package GraphAndGraphAlgorithms.Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TopologicalSortDFS {
    public static void main(String[] args) throws IOException {
        Map<String, List<String>> graph = readGraph();
        Collection<String> output = topSort(graph);
        StringBuilder sb = new StringBuilder();
        printResult(output, sb);
    }

    private static void printResult(Collection<String> output, StringBuilder sb) {
        sb.append("Topological sorting:").append(System.lineSeparator());
        for (String str : output) {
            sb.append(str).append(", ");
        }
        sb.delete(sb.length() - 2, sb.length());
        System.out.println(sb);
    }

    public static List<String> topSort(Map<String, List<String>> graph) {
        LinkedList<String> sortedVertices = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        Set<String> cycleVisited = new HashSet<>();
        for (String node : graph.keySet()) {
            topSortDFS(graph, node, visited, cycleVisited, sortedVertices);
        }
        return sortedVertices;
    }

    private static void topSortDFS(Map<String, List<String>> graph, String vertex, Set<String> visited, Set<String> cycleVisited, LinkedList<String> sortedVertices) {
        if (cycleVisited.contains(vertex)) {
            throw new IllegalArgumentException();
        }
        if (!visited.contains(vertex)) {
            visited.add(vertex);
            cycleVisited.add(vertex);
            for (String child : graph.get(vertex)) {
                topSortDFS(graph, child, visited, cycleVisited, sortedVertices);
            }
            cycleVisited.remove(vertex);
            sortedVertices.addFirst(vertex);
        }
    }

    private static Map<String, List<String>> readGraph() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, List<String>> graph = new LinkedHashMap<>();
        int n = Integer.parseInt(reader.readLine());
        for (int i = 0; i < n; i++) {
            String[] tokens = reader.readLine().split(" -> | ->|, ");
            String vertex = tokens[0].replaceAll("\"", "");
            graph.putIfAbsent(vertex, new ArrayList<>());
            for (int j = 1; j < tokens.length; j++) {
                if (tokens[j].equals("")) {
                    break;
                }
                String edge = tokens[j].replaceAll("\"", "");
                graph.get(vertex).add(edge);
            }
        }
        return graph;
    }
}
