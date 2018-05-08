package ExamPreparation.Exam1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class TourDeSofia {

    private static  List<Integer>[] graph;
    private static boolean[] visited;
    private static int[] levels;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int junctions = Integer.parseInt(reader.readLine());
        int streets = Integer.parseInt(reader.readLine());
        int start = Integer.parseInt(reader.readLine());
        initializeGraph(junctions);
        addStreets(reader, streets);
        int junctionsReached = BFS(junctions, start);
        System.out.println(visited[start] ? levels[start] : junctionsReached);
    }

    private static int BFS(int junctions, int start) {
        int junctionsReached = 0;
        levels = new int[junctions];
        visited = new boolean[junctions];
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        while (!queue.isEmpty()) {
            int junction = queue.poll();
            if (junction == start && junctionsReached > 0) {
                break;
            }
            junctionsReached++;
            for (Integer child : graph[junction]) {
                if (!visited[child]) {
                    levels[child] = levels[junction] + 1;
                    visited[child] = true;
                    queue.offer(child);
                }
            }
        }
        return junctionsReached;
    }

    private static void addStreets(BufferedReader reader, int streets) throws IOException {
        for (int i = 0; i < streets; i++) {
            String[] tokens = reader.readLine().split(" ");
            int from = Integer.parseInt(tokens[0]);
            int to = Integer.parseInt(tokens[1]);
            graph[from].add(to);
        }
    }

    @SuppressWarnings("unchecked")
    private static void initializeGraph(int junctions) {
        graph = new ArrayList[junctions];
        for (int i = 0; i < junctions; i++) {
            graph[i] = new ArrayList<>();
        }
    }
}
