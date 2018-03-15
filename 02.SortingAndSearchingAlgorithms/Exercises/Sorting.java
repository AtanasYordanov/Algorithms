package SortingAndSearchingAlgorithms.Exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Sorting {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> numbers = parseInput(reader);
        Merge ms = new Merge();
        ms.sort(numbers);
        printList(numbers);
    }

    private static void printList(List<Integer> numbers) {
        StringBuilder sb = new StringBuilder();
        for (Integer number : numbers) {
            sb.append(number).append(" ");
        }
        System.out.println(sb);
    }

    private static List<Integer> parseInput(BufferedReader reader) throws IOException {
        String[] input = reader.readLine().split(" ");
        List<Integer> numbers = new ArrayList<>();
        for (String str : input) {
            numbers.add(Integer.parseInt(str));
        }
        return numbers;
    }
}

class Merge {
    private int[] aux;

    public void sort(List<Integer> list) {
        this.aux = new int[list.size()];
        this.sort(list, 0, list.size() - 1);
    }

    private void merge(List<Integer> list, int left, int mid, int right) {
        if (isLess(list.get(mid), list.get(mid + 1))) {
            return;
        }
        for (int index = left; index < right + 1; index++) {
            this.aux[index] = list.get(index);
        }
        int i = left;
        int j = mid + 1;
        for (int k = left; k <= right; k++) {
            if (i > mid) {
                list.set(k, this.aux[j++]);
            } else if (j > right) {
                list.set(k, this.aux[i++]);
            } else if (isLess(aux[i], aux[j])) {
                list.set(k, this.aux[i++]);
            } else {
                list.set(k, this.aux[j++]);
            }
        }
    }

    private static boolean isLess(int first, int second) {
        return first < second;
    }

    private void sort(List<Integer> list, int left, int right) {
        if (left >= right) {
            return;
        }
        int middle = left + (right - left) / 2;
        sort(list, left, middle);
        sort(list, middle + 1, right);
        merge(list, left, middle, right);
    }
}