package SolvingPracticalProblems2.Exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Renewal {

    private static int[][] graph;
    private static int[][] buildCosts;
    private static int[][] destroyCosts;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        graph = new int[n][n];
        buildCosts = new int[n][n];
        destroyCosts = new int[n][n];
        initializeGraphs(reader, n);
        Set<Edge> edges = getEdges();
        int totalCost = kruskal(graph.length, edges);
        System.out.println(totalCost);
    }

    private static int kruskal(int numberOfVertices, Set<Edge> edges) {
        int totalCost = 0;
        int[] parent = new int[numberOfVertices];
        for (int i = 0; i < numberOfVertices; i++) {
            parent[i] = i;
        }
        for (Edge edge : edges) {
            int v1 = edge.getFrom();
            int v2 = edge.getTo();
            int cost = edge.getCost();
            int startVertexRoot = findRoot(v1, parent);
            int endVertexRoot = findRoot(v2, parent);
            if (startVertexRoot != endVertexRoot) {
                parent[startVertexRoot] = endVertexRoot;
                if (cost > 0) {
                    totalCost += cost;
                }
            } else if (cost < 0) {
                totalCost -= cost;
            }
        }
        return totalCost;
    }

    private static int findRoot(int vertex, int[] parent) {
        int root = vertex;
        while (parent[root] != root) {
            root = parent[root];
        }
        while (vertex != root) {
            int oldParent = parent[vertex];
            parent[vertex] = root;
            vertex = oldParent;
        }
        return root;
    }

    private static void initializeGraphs(BufferedReader reader, int n) throws IOException {
        for (int i = 0; i < n; i++) {
            String[] tokens = reader.readLine().split("");
            for (int j = 0; j < n; j++) {
                graph[i][j] = Integer.parseInt(tokens[j]);
            }
        }
        for (int i = 0; i < n; i++) {
            String[] tokens = reader.readLine().split("");
            for (int j = 0; j < n; j++) {
                int cost = convertChar(tokens[j]);
                buildCosts[i][j] = cost;
            }
        }
        for (int i = 0; i < n; i++) {
            String[] tokens = reader.readLine().split("");
            for (int j = 0; j < n; j++) {
                int cost = convertChar(tokens[j]);
                destroyCosts[i][j] = cost;
            }
        }
    }

    private static int convertChar(String token) {
        char ch = token.charAt(0);
        int cost;
        if (ch >= 'A' && ch <= 'Z') {
            cost = ch - 'A';
        } else {
            cost = ch - 'a' + 26;
        }
        return cost;
    }

    private static Set<Edge> getEdges() {
        Set<Edge> edges = new TreeSet<>();
        for (int row = 0; row < graph.length - 1; row++) {
            for (int col = row + 1; col < graph.length; col++) {
                int cost;
                if (graph[row][col] == 0) {
                    cost = buildCosts[row][col];
                } else {
                    cost = -destroyCosts[row][col];
                }
                Edge edge = new Edge(row, col, cost);
                edges.add(edge);
            }
        }
        return edges;
    }

    private static class Edge implements Comparable<Edge> {

        private int from;
        private int to;
        private int cost;

        Edge(int startNode, int endNode, int weight) {
            this.from = startNode;
            this.to = endNode;
            this.cost = weight;
        }

        public int getFrom() {
            return this.from;
        }

        public int getTo() {
            return this.to;
        }

        public int getCost() {
            return cost;
        }

        @Override
        public int compareTo(Edge o) {
            int comp = Integer.compare(this.cost, o.cost);
            if (comp == 0) {
                comp = Integer.compare(this.from, o.from);
                if (comp == 0) {
                    comp = Integer.compare(this.to, o.to);
                }
            }
            return comp;
        }
    }
}
