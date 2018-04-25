package GraphAdvancedAlgorithms1.Exercises.CableNetwork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CableNetwork {

    private static List<List<Edge>> graph = new ArrayList<>();
    private static Set<Integer> spanningTree = new HashSet<>();
    private static int budget;
    private static int totalCost;

    public static void main(String[] args) throws IOException {
        readGraph();
        PriorityQueue<Edge> queue = fillQueue();
        markConnections(queue);
        System.out.printf("Budget used: %d%n", totalCost);
    }

    private static void markConnections(PriorityQueue<Edge> queue) {
        while (!queue.isEmpty() && spanningTree.size() != graph.size()) {
            Edge edge = queue.poll();
            int firstVertex = edge.getFirstVertex();
            int secondVertex = edge.getSecondVertex();
            if (spanningTree.contains(edge.getFirstVertex()) && spanningTree.contains(edge.getSecondVertex())) {
                continue;
            }
            if (totalCost + edge.getCost() > budget){
                break;
            }
            totalCost += edge.getCost();
            if (spanningTree.contains(firstVertex)) {
                for (int i = 0; i < graph.get(secondVertex).size(); i++) {
                    if (!spanningTree.contains(firstVertex) || !spanningTree.contains(secondVertex)) {
                        queue.offer(graph.get(secondVertex).get(i));
                    }
                }
                spanningTree.add(secondVertex);
            } else if (spanningTree.contains(secondVertex)) {
                for (int i = 0; i < graph.get(firstVertex).size(); i++) {
                    if (!spanningTree.contains(firstVertex) || !spanningTree.contains(secondVertex)) {
                        queue.offer(graph.get(firstVertex).get(i));
                    }
                }
                spanningTree.add(firstVertex);
            }
        }
    }

    private static PriorityQueue<Edge> fillQueue() {
        PriorityQueue<Edge> queue = new PriorityQueue<>(Comparator.comparing(Edge::getCost));
        for (Integer vertex : spanningTree) {
            for (Edge edge : graph.get(vertex)) {
                if (!spanningTree.contains(edge.getFirstVertex()) || !spanningTree.contains(edge.getSecondVertex())) {
                    queue.offer(edge);
                }
            }
        }
        return queue;
    }

    private static void readGraph() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        budget = Integer.parseInt(reader.readLine().substring(8));
        int nodesCount = Integer.parseInt(reader.readLine().substring(7));
        int edgesCount = Integer.parseInt(reader.readLine().substring(7));
        for (int i = 0; i < nodesCount; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < edgesCount; i++) {
            String[] tokens = reader.readLine().split(" ");
            int from = Integer.parseInt(tokens[0]);
            int to = Integer.parseInt(tokens[1]);
            int cost = Integer.parseInt(tokens[2]);
            Edge edge = new Edge(from, to, cost);
            graph.get(from).add(edge);
            graph.get(to).add(edge);
            if (tokens.length == 4) {
                spanningTree.add(from);
                spanningTree.add(to);
            }
        }
    }
}

class Edge {
    private int firstVertex;
    private int secondVertex;
    private int cost;

    public Edge(int from, int to, int cost) {
        this.firstVertex = from;
        this.secondVertex = to;
        this.cost = cost;
    }

    public int getFirstVertex() {
        return firstVertex;
    }

    public int getSecondVertex() {
        return secondVertex;
    }

    public int getCost() {
        return cost;
    }
}
