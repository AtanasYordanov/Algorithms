package Exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Protoss {

    private static List<Integer>[] graph;
    private static int maxConnections = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        buildGraph(reader, n);
        for (int i = 0; i < n; i++) {
            findMaxConnections(i);
        }
        System.out.println(maxConnections);
    }

    @SuppressWarnings("unchecked")
    private static void buildGraph(BufferedReader reader, int n) throws IOException {
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            String line = reader.readLine();
            for (int j = 0; j < n; j++) {
                if (line.charAt(j) == 'Y') {
                    graph[i].add(j);
                }
            }
        }
    }

    private static void findMaxConnections(int vertex) {
        int connections = 0;
        boolean[] visited = new boolean[graph.length];
        visited[vertex] = true;
        for (Integer child : graph[vertex]) {
            if (!visited[child]) {
                connections++;
                visited[child] = true;
            }
            for (Integer grandChild : graph[child]) {
                if (!visited[grandChild]) {
                    connections++;
                    visited[grandChild] = true;
                }
            }
        }
        if (connections > maxConnections) {
            maxConnections = connections;
        }
    }
}
