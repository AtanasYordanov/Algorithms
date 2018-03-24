package GreedyAlgorithms.Exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BestLecturesSchedule {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine().substring(10));
        List<Lecture> lectures = new ArrayList<>();
        addLectures(reader, n, lectures);
        lectures.sort(Comparator.naturalOrder());
        List<Lecture> chosenLectures = new ArrayList<>();
        addChosenLectures(lectures, chosenLectures);
        printResult(chosenLectures);
    }

    private static void printResult(List<Lecture> chosenLectures) {
        System.out.printf("Lectures (%d):%n", chosenLectures.size());
        for (Lecture lecture : chosenLectures) {
            System.out.printf("%d-%d -> %s%n", lecture.getStartTime(),
                    lecture.getFinishTime(), lecture.getCourseName());
        }
    }

    private static void addChosenLectures(List<Lecture> lectures, List<Lecture> chosenLectures) {
        Lecture lastLecture = lectures.get(0);
        chosenLectures.add(lastLecture);
        for (int i = 1; i < lectures.size(); i++) {
            Lecture currentLecture = lectures.get(i);
            if (currentLecture.getStartTime() > lastLecture.getFinishTime()) {
                chosenLectures.add(currentLecture);
                lastLecture = currentLecture;
            }
        }
    }

    private static void addLectures(BufferedReader reader, int n, List<Lecture> lectures) throws IOException {
        for (int i = 0; i < n; i++) {
            String[] tokens = reader.readLine().split(" - |: ");
            String name = tokens[0];
            int start = Integer.parseInt(tokens[1]);
            int finish = Integer.parseInt(tokens[2]);
            lectures.add(new Lecture(name, start, finish));
        }
    }
}

class Lecture implements Comparable<Lecture> {
    private String courseName;
    private int startTime;
    private int finishTime;

    public Lecture(String courseName, int startTime, int finishTime) {
        this.courseName = courseName;
        this.startTime = startTime;
        this.finishTime = finishTime;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getFinishTime() {
        return finishTime;
    }

    @Override
    public int compareTo(Lecture other) {
        return Integer.compare(this.finishTime, other.finishTime);
    }
}
