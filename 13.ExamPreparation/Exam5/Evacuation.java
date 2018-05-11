package ExamPreparation.Exam5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Evacuation {

    private static List<Integer>[] edges;
    private static List<Integer>[] distancesToEdge;
    private static int[] exitRooms;
    private static int[] bestTimes;
    private static int maxTime;

    public static void main(String[] args) throws IOException {
        readInput();
        modifiedDijkstra();
        List<Integer> unsafe = new ArrayList<>();
        int last = 0;
        for (int i = 0; i < bestTimes.length; i++) {
            int time = bestTimes[i];
            if (time > bestTimes[last]) {
                last = i;
            }
            if (time > maxTime) {
                unsafe.add(i);
            }
        }
        System.out.println(buildString(unsafe, last));
    }

    private static String buildString(List<Integer> unsafe, int last) {
        StringBuilder sb = new StringBuilder();
        if (unsafe.isEmpty()) {
            int seconds = bestTimes[last];
            int hours = seconds / 3600;
            seconds = seconds % 3600;
            int minutes = seconds / 60;
            seconds = seconds % 60;
            sb.append("Safe").append(System.lineSeparator());
            sb.append(last).append(" (");
            appendWithPadding(sb, hours).append(":");
            appendWithPadding(sb, minutes).append(":");
            appendWithPadding(sb, seconds).append(")");
        } else {
            sb.append("Unsafe").append(System.lineSeparator());
            for (Integer room : unsafe) {
                int seconds = bestTimes[room];
                int hours = seconds / 3600;
                seconds = seconds % 3600;
                int minutes = seconds / 60;
                seconds = seconds % 60;
                if (bestTimes[room] < Integer.MAX_VALUE) {
                    sb.append(room).append(" (");
                    appendWithPadding(sb, hours).append(":");
                    appendWithPadding(sb, minutes).append(":");
                    appendWithPadding(sb, seconds).append(")");
                } else {
                    sb.append(room).append(" (unreachable)");
                }
                sb.append(", ");
            }
            sb.delete(sb.length() - 2, sb.length());
        }
        return sb.toString();
    }

    private static StringBuilder appendWithPadding(StringBuilder sb, int hours) {
        if (hours == 0) {
            sb.append("00");
        } else if (hours < 10) {
            sb.append("0").append(hours);
        } else {
            sb.append(hours);
        }
        return sb;
    }

    private static void modifiedDijkstra() {
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparing(i -> bestTimes[i]));
        for (int room : exitRooms) {
            bestTimes[room] = 0;
            queue.offer(room);
        }
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            for (int i = 0; i < edges[vertex].size(); i++) {
                int child = edges[vertex].get(i);
                int timeToChild = distancesToEdge[vertex].get(i);
                int totalTime = bestTimes[vertex] + timeToChild;
                if (bestTimes[child] > totalTime) {
                    bestTimes[child] = totalTime;
                    queue.offer(child);
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    private static void readInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int roomsCount = Integer.parseInt(reader.readLine());
        String[] tokens = reader.readLine().split(" ");
        exitRooms = new int[tokens.length];
        bestTimes = new int[roomsCount];
        for (int i = 0; i < roomsCount; i++) {
            bestTimes[i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < tokens.length; i++) {
            exitRooms[i] = Integer.parseInt(tokens[i]);
        }
        int edgesCount = Integer.parseInt(reader.readLine());
        edges = new ArrayList[roomsCount];
        distancesToEdge = new ArrayList[roomsCount];
        for (int i = 0; i < edges.length; i++) {
            edges[i] = new ArrayList<>();
            distancesToEdge[i] = new ArrayList<>();
        }
        for (int i = 0; i < edgesCount; i++) {
            tokens = reader.readLine().split(" ");
            int v1 = Integer.parseInt(tokens[0]);
            int v2 = Integer.parseInt(tokens[1]);
            int minutes = Integer.parseInt(tokens[2].substring(0, 2));
            int seconds = Integer.parseInt(tokens[2].substring(3));
            int totalSeconds = minutes * 60 + seconds;
            edges[v1].add(v2);
            edges[v2].add(v1);
            distancesToEdge[v2].add(totalSeconds);
            distancesToEdge[v1].add(totalSeconds);

        }
        String line = reader.readLine();
        int minutes = Integer.parseInt(line.substring(0, 2));
        int seconds = Integer.parseInt(line.substring(3));
        maxTime = minutes * 60 + seconds;
    }
}