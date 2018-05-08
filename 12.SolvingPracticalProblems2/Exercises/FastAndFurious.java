package SolvingPracticalProblems2.Exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class FastAndFurious {

    private static double[][] graph;
    private static Map<String, List<Record>> records = new HashMap<>();
    private static Map<String, Integer> locationIndices = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        fillGraph(reader);

        Set<String> speeders = new HashSet<>();

        String input;
        while (!"End".equals(input = reader.readLine())) {
            String[] tokens = input.split(" ");
            String licensePlate = tokens[1];
            if (speeders.contains(licensePlate)) {
                continue;
            }
            String location = tokens[0];
            String time = tokens[2];
            int destinationIndex = locationIndices.get(location);
            int totalSeconds = getSeconds(time);
            if (records.containsKey(licensePlate)) {
                for (Record record : records.get(licensePlate)) {
                    boolean isSpeeding = checkIfCarIsSpeeding(destinationIndex, totalSeconds, record);
                    if (isSpeeding) {
                        speeders.add(licensePlate);
                        break;
                    }
                }
            }
            records.putIfAbsent(licensePlate, new ArrayList<>());
            records.get(licensePlate).add(new Record(destinationIndex, totalSeconds));
        }

        StringBuilder sb = new StringBuilder();
        speeders.stream()
                .sorted(Comparator.naturalOrder())
                .forEach(s -> sb.append(s).append(System.lineSeparator()));
        System.out.print(sb);
    }

    private static void fillGraph(BufferedReader reader) throws IOException {
        List<String[]> lines = new ArrayList<>();
        String input = reader.readLine();
        int locationIndex = 0;
        while (!"Records:".equals(input = reader.readLine())) {
            String[] tokens = input.split(" ");
            lines.add(tokens);
            String location1 = tokens[0];
            String location2 = tokens[1];
            if (!locationIndices.containsKey(location1)) {
                locationIndices.put(location1, locationIndex++);
            }
            if (!locationIndices.containsKey(location2)) {
                locationIndices.put(location2, locationIndex++);
            }
        }
        graph = new double[locationIndices.size()][locationIndices.size()];
        for (String[] tokens : lines) {
            String location1 = tokens[0];
            String location2 = tokens[1];
            double distance = Double.parseDouble(tokens[2]);
            double maxSpeedAllowed = Double.parseDouble(tokens[3]);
            double minTime = (distance / maxSpeedAllowed) * 3600;
            int firstIndex = locationIndices.get(location1);
            int secondIndex = locationIndices.get(location2);
            graph[firstIndex][secondIndex] = minTime;
            graph[secondIndex][firstIndex] = minTime;
        }
    }

    private static boolean checkIfCarIsSpeeding(int destinationIndex, int totalSeconds, Record record) {
        int prevSeconds = record.getTime();
        int timeTravelled = Math.abs(totalSeconds - prevSeconds);
        int startIndex = record.getTownIndex();
        double timeAllowed = dijkstra(startIndex, destinationIndex);
        return timeTravelled < timeAllowed;
    }

    private static double dijkstra(int start, int destination) {
        double[] time = new double[graph.length];
        for (int i = 0; i < time.length; i++) {
            time[i] = Double.MAX_VALUE;
        }
        time[start] = 0D;
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparing(i -> time[i]));
        queue.offer(start);
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            for (int i = 0; i < graph.length; i++) {
                double distanceToChild = graph[vertex][i];
                double totalDistance = time[vertex] + distanceToChild;
                if (distanceToChild != 0D && time[i] > totalDistance) {
                    time[i] = totalDistance;
                    queue.remove(i);
                    queue.offer(i);

                }
            }
        }
        return time[destination] != Double.MAX_VALUE ? time[destination] : 0D;
    }

    private static int getSeconds(String time) {
        String[] tokens = time.split(":");
        int hours = Integer.parseInt(tokens[0]);
        int minutes = Integer.parseInt(tokens[1]);
        int seconds = Integer.parseInt(tokens[2]);
        return hours * 3600 + minutes * 60 + seconds;
    }

    private static class Record {
        private int townIndex;
        private int time;

        Record(int townIndex, int time) {
            this.townIndex = townIndex;
            this.time = time;
        }

        int getTownIndex() {
            return townIndex;
        }

        int getTime() {
            return time;
        }
    }
}