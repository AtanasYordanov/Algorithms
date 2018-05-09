package ExamPreparation.Exam3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MessageSharing {

    private static Map<String, List<String>> graph = new HashMap<>();
    private static Map<String, Integer> peopleSteps = new HashMap<>();
    private static Set<String> unreachablePeople = new TreeSet<>();
    private static Set<String> lastPeople = new TreeSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] tokens = reader.readLine().split(": |, ");
        addPeople(tokens);
        tokens = reader.readLine().split(": |, ");
        addConnections(tokens);
        tokens = reader.readLine().split(": |, ");
        Deque<String> queue = new ArrayDeque<>();
        enqueueFirstPeople(tokens, queue);
        int maxSteps = distributeMessage(queue);
        fillCollections(maxSteps);
        printResult(maxSteps);
    }

    private static void fillCollections(int maxSteps) {
        boolean isReceivedByAll = true;
        for (Map.Entry<String, Integer> pair : peopleSteps.entrySet()) {
            if (pair.getValue() == -1) {
                unreachablePeople.add(pair.getKey());
                isReceivedByAll = false;
            } else if (isReceivedByAll && pair.getValue() == maxSteps) {
                lastPeople.add(pair.getKey());
            }
        }
    }

    private static int distributeMessage(Deque<String> queue) {
        int maxSteps = 0;
        while (!queue.isEmpty()) {
            String current = queue.poll();
            for (String child : graph.get(current)) {
                if (peopleSteps.get(child) == -1) {
                    queue.offer(child);
                    int step = peopleSteps.get(current) + 1;
                    if (step > maxSteps) {
                        maxSteps = step;
                    }
                    peopleSteps.put(child, step);
                }
            }
        }
        return maxSteps;
    }

    private static void enqueueFirstPeople(String[] tokens, Deque<String> queue) {
        for (int i = 1; i < tokens.length; i++) {
            queue.offer(tokens[i]);
            peopleSteps.put(tokens[i], 0);
        }
    }

    private static void printResult(int maxSteps) {
        StringBuilder sb = new StringBuilder();
        if (unreachablePeople.size() > 0) {
            sb.append("Cannot reach: ");
            for (String person : unreachablePeople) {
                sb.append(person).append(", ");
            }
        } else {
            sb.append(String.format("All people reached in %d steps", maxSteps));
            sb.append(System.lineSeparator());
            sb.append("People at last step: ");
            for (String person : lastPeople) {
                sb.append(person).append(", ");
            }
        }
        sb.delete(sb.length() - 2, sb.length());
        System.out.println(sb);
    }

    private static void addConnections(String[] tokens) {
        for (int i = 1; i < tokens.length; i++) {
            String[] people = tokens[i].split(" - ");
            String firstPerson = people[0];
            String secondPerson = people[1];
            graph.get(firstPerson).add(secondPerson);
            graph.get(secondPerson).add(firstPerson);
        }
    }

    private static void addPeople(String[] tokens) {
        for (int i = 1; i < tokens.length; i++) {
            String person = tokens[i];
            graph.put(person, new ArrayList<>());
            peopleSteps.put(person, -1);
        }
    }
}
