package GreedyAlgorithms.Exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ProcessorScheduling {

    private static Comparator<Process> processComparator = Comparator.comparing(Process::getDeadline)
                    .thenComparing(Process::getValue, Comparator.reverseOrder());
    private static Set<Process> tasks = new TreeSet<>(processComparator);
    private static List<Process> bestTasks = new ArrayList<>();
    private static Map<Integer, List<Process>> deadlines = new HashMap<>();
    private static int sum;
    private static int maxValue;
    private static int maxDeadline;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine().substring(7));
        addProcesses(reader, n);
        getMaximumValue(maxDeadline, maxDeadline);
        printResult();
    }

    private static void getMaximumValue(int processesLeft, int step) {
        if (step < 1) {
            return;
        }
        for (int i = 1; i <= Math.min(deadlines.get(step).size(), processesLeft); i++) {
            int currentSum = 0;
            for (int j = 1; j <= i; j++) {
                Process process = deadlines.get(step).get(deadlines.get(step).size() - j);
                currentSum += process.getValue();
                tasks.add(process);
            }
            sum += currentSum;
            getMaximumValue(processesLeft - i, step - 1);
            if (sum > maxValue) {
                maxValue = sum;
                bestTasks.clear();
                bestTasks.addAll(tasks);
            }
            sum -= currentSum;
            for (int j = 1; j <= i; j++) {
                tasks.remove(deadlines.get(step).get(deadlines.get(step).size() - j));
            }
        }
    }

    private static void addProcesses(BufferedReader reader, int n) throws IOException {
        for (int i = 1; i <= n; i++) {
            String[] input = reader.readLine().split(" - ");
            int value = Integer.parseInt(input[0]);
            int deadline = Integer.parseInt(input[1]);
            Process process = new Process(i, value, deadline);
            maxDeadline = deadline > maxDeadline ? deadline : maxDeadline;
            deadlines.putIfAbsent(deadline, new ArrayList<>());
            deadlines.get(deadline).add(process);
            deadlines.get(deadline).sort(Comparator.naturalOrder());
        }
    }

    private static void printResult() {
        String schedule = "";
        for (int i = 0; i < bestTasks.size(); i++) {
            if (i != 0) {
                schedule += " -> ";
            }
            schedule += bestTasks.get(i).getIndex();
        }
        System.out.printf("Optimal schedule: %s%n", schedule);
        System.out.printf("Total value: %d", maxValue);
    }
}

class Process implements Comparable<Process> {
    private int index;
    private int value;
    private int deadline;

    public Process(int index, int value, int deadline) {
        this.index = index;
        this.value = value;
        this.deadline = deadline;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getValue() {
        return value;
    }

    public int getDeadline() {
        return deadline;
    }

    @Override
    public int compareTo(Process other) {
        return Integer.compare(this.value, other.value);
    }
}
