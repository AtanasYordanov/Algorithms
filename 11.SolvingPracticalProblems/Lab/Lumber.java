package SolvingPracticalProblems.Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Lumber {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] tokens = reader.readLine().split(" ");
        int n = Integer.parseInt(tokens[0]);
        int m = Integer.parseInt(tokens[1]);
        Map<Integer, Log> logsById = new HashMap<>();
        Map<Log, List<Log>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            tokens = reader.readLine().split(" ");
            int x1 = Integer.parseInt(tokens[0]);
            int y1 = Integer.parseInt(tokens[1]);
            int x2 = Integer.parseInt(tokens[2]);
            int y2 = Integer.parseInt(tokens[3]);
            Log newLog = new Log(i, x1, y1, x2, y2);
            graph.put(newLog, new ArrayList<>());
            for (Log log : logsById.values()) {
                if (newLog.touches(log)) {
                    graph.get(log).add(newLog);
                    graph.get(newLog).add(log);
                }
            }
            logsById.put(i, newLog);
        }
        int[] logsComponents = findConnectedComponents(logsById.values(), graph);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            tokens = reader.readLine().split(" ");
            Log log1 = logsById.get(Integer.parseInt(tokens[0]) - 1);
            Log log2 = logsById.get(Integer.parseInt(tokens[1]) - 1);
            if (areConnected(log1, log2, logsComponents)) {
                sb.append("YES").append(System.lineSeparator());
            } else {
                sb.append("NO").append(System.lineSeparator());
            }
        }
        System.out.println(sb);
    }

    private static int[] findConnectedComponents(Collection<Log> values, Map<Log, List<Log>> graph) {
        int[] logsComponents= new int[graph.size()];
        int component = 0;
        Set<Log> visited = new HashSet<>();
        for (Log log : values) {
            if (!visited.contains(log)){
                component++;
                Deque<Log> queue = new ArrayDeque<>();
                queue.offer(log);
                while (!queue.isEmpty()) {
                    Log currentLog = queue.poll();
                    if (logsComponents[currentLog.getId()] == 0){
                        logsComponents[currentLog.getId()] = component;
                        for (Log child : graph.get(currentLog)) {
                            queue.offer(child);
                        }
                    }
                }
                visited.add(log);
            }
        }
        return logsComponents;
    }

    private static boolean areConnected(Log log1, Log log2, int[] logsComponents) {
        return logsComponents[log1.getId()] == logsComponents[log2.getId()];
    }
}

class Log {
    private int id;
    private int x1;
    private int y1;
    private int x2;
    private int y2;

    Log(int id, int x1, int y1, int x2, int y2) {
        this.id = id;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public int getId() {
        return id;
    }

    public boolean touches(Log other) {
        return this.x1 <= other.x2 && other.x1 <= this.x2 &&
                this.y1 >= other.y2 && other.y1 >= this.y2;
    }
}
