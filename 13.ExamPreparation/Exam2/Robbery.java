package ExamPreparation.Exam2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Robbery {

    private static Long[][] graph;
    private static boolean[] isWatched;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] tokens = reader.readLine().split(" ");
        graph = new Long[tokens.length][tokens.length];
        isWatched = new boolean[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            isWatched[i] = tokens[i].charAt(tokens[i].length() - 1) == 'w';
        }
        long energy = Long.parseLong(reader.readLine());
        int waitCost = Integer.parseInt(reader.readLine());
        int startVertex = Integer.parseInt(reader.readLine());
        int endVertex = Integer.parseInt(reader.readLine());
        int connections = Integer.parseInt(reader.readLine());
        for (int i = 0; i < connections; i++) {
            tokens = reader.readLine().split(" ");
            int from = Integer.parseInt(tokens[0]);
            int to = Integer.parseInt(tokens[1]);
            long cost = Long.parseLong(tokens[2]);
            graph[from][to] = cost;
        }
        long requiredEnergy = dijkstra(startVertex, endVertex, waitCost);

        if (energy >= requiredEnergy) {
            System.out.println(energy - requiredEnergy);
        } else {
            System.out.printf("Busted - need %d more energy%n", requiredEnergy - energy);
        }
    }

    private static long dijkstra(int start, int destination, int waitCost) {
        long[] cost = new long[graph.length];
        boolean[] isOdd = new boolean[graph.length];
        for (int i = 0; i < cost.length; i++) {
            cost[i] = Long.MAX_VALUE;
        }
        cost[start] = 0L;
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparing(i -> cost[i]));
        queue.offer(start);
        isOdd[start] = true;
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            for (int i = 0; i < graph.length; i++) {
                Long costToChild = graph[vertex][i];
                if (costToChild != null) {
                    boolean shouldWait = (isOdd[vertex] && !isWatched[i]) || (!isOdd[vertex] && isWatched[i]);
                    long totalCost = cost[vertex] + costToChild;
                    if (shouldWait){
                        totalCost += waitCost;
                    }
                    if (cost[i] > totalCost) {
                        isOdd[i] = shouldWait == isOdd[vertex];
                        cost[i] = totalCost;
                        queue.offer(i);
                    }
                }
            }
        }
        return cost[destination];
    }
}
