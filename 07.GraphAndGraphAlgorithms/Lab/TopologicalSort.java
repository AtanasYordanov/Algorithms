package GraphAndGraphAlgorithms.Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TopologicalSort {
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

    private static Map<String, Integer> getPredecessorCount(Map<String, List<String>> graph) {
        Map<String, Integer> predecessorCount = new LinkedHashMap<>();
        for (Map.Entry<String, List<String>> pair : graph.entrySet()) {
            predecessorCount.putIfAbsent(pair.getKey(), 0);
            for (String child : pair.getValue()) {
                predecessorCount.putIfAbsent(child, 0);
                predecessorCount.put(child, predecessorCount.get(child) + 1);
            }
        }
        return predecessorCount;
    }

    private static Collection<String> topSort(Map<String, List<String>> graph) {
        Map<String, Integer> predecessorCount = getPredecessorCount(graph);
        List<String> list = new ArrayList<>();
        Optional<String> vertex = predecessorCount.entrySet().stream()
                .filter(v -> v.getValue() == 0)
                .map(Map.Entry::getKey)
                .findFirst();
        while (vertex.isPresent()) {
            for (String child : graph.get(vertex.get())) {
                predecessorCount.put(child, predecessorCount.get(child) - 1);
            }
            graph.remove(vertex.get());
            predecessorCount.remove(vertex.get());
            list.add(vertex.get());

            vertex = predecessorCount.entrySet().stream()
                    .filter(v -> v.getValue() == 0)
                    .map(Map.Entry::getKey)
                    .findFirst();
        }
        if (graph.size() > 0) {
            throw new IllegalArgumentException();
        }
        return list;
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
