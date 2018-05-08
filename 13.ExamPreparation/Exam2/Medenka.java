package ExamPreparation.Exam2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Medenka {

    private static StringBuilder output = new StringBuilder();
    private static Deque<Integer> indices = new ArrayDeque<>();
    private static String medenka;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        medenka = reader.readLine().replaceAll(" ", "");
        int startIndex = medenka.indexOf("1");
        generate(startIndex);
        System.out.print(output);
    }

    private static void generate(int startIndex) {
        int nextIndex = medenka.indexOf("1", startIndex + 1);
        if (nextIndex == -1){
            addMedenka();
            return;
        }
        for (int i = startIndex + 1; i <= nextIndex; i++) {
            indices.push(i);
            generate(nextIndex);
            indices.pop();
        }
    }

    private static void addMedenka() {
        StringBuilder sb = new StringBuilder(medenka);
        for (Integer index : indices) {
            sb.insert(index, "|");
        }
        output.append(sb).append(System.lineSeparator());
    }
}